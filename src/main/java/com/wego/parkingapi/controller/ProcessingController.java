package com.wego.parkingapi.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.wego.parkingapi.model.*;
import com.wego.parkingapi.string.*;
/**
 * ProcessingController :  management dispatch function from API 
 *
 * Bugs: none known
 * Issues: none known
 * @author       Huynh Xuan An
 * @version      1.0
 */
public class ProcessingController {
	
	// Create entity management for working with database, using JPA
	EntityManagerFactory factory =Persistence.createEntityManagerFactory(Configuration.persistenceUnitName); 
	EntityManager entityManager = factory.createEntityManager();		
	
	 /**
     * Write car park availability from API endpoint to database
     */	
	public  void updateCarparkAvailability() throws  IOException, ParseException {	
		
		String rawCarParkresult = getCarParkDataset(Configuration.carParkAvailabilityUrl);
		ArrayList<CarParkAvailability> carParkArray = createCarParkRepo(rawCarParkresult);
		
		//Scan array list data and update data to db
		for(CarParkAvailability carPark : carParkArray)	{
			  entityManager.getTransaction().begin();
			  //Need to re-setting field value, because can not set at construction time, see function declare for more detail
			  carPark.recalculateLots();			
			  entityManager.merge(carPark);
			  entityManager.getTransaction().commit(); 
		}		  
		entityManager.close();
		factory.close();
	}

	/**
     * Mapping JSON data to car park availability model
     * return: ArrayList<CarParkAvailability>
     */
	private ArrayList<CarParkAvailability> createCarParkRepo(String rawCarParkresult)
			throws ParseException {
		
		JSONParser parse = new JSONParser();
		JSONObject jobj = (JSONObject) parse.parse(rawCarParkresult);
		//Access first level of JSON key
		JSONArray jsonArrLv1 =   (JSONArray) jobj.get(Configuration.jsonKeyLv1);
		//Access second level of JSON key
		JSONArray jsonArrLv2 =  (JSONArray)((JSONObject)jsonArrLv1.get(0)).get(Configuration.jsonKeyLv2);
		String json = new Gson().toJson(jsonArrLv2);
		
		//use Gson library to mapping data to model
		Type carParkArrayListType = new TypeToken<ArrayList<CarParkAvailability>>() {}.getType();		  
	 	Gson gson = new Gson();
		ArrayList<CarParkAvailability> carParkArray = gson.fromJson(json, carParkArrayListType);
		
		return carParkArray;
	}
	
	/**
     * Get car park available information from API in JSON format
     * return: String in JSON format
     */
	private String getCarParkDataset(String apiEndpointUrl) throws MalformedURLException, IOException {
		String rawCarParkResult = "";
		URL url = new URL(apiEndpointUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responsecode = conn.getResponseCode();
		if (responsecode != 200)
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		else {
			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNext()) {
				rawCarParkResult += sc.nextLine();
			}
			sc.close();
		}
		return rawCarParkResult;
	}
}
