package com.wego.parkingapi.model;

import java.util.ArrayList;
import java.util.HashMap;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import com.google.gson.Gson;
import com.wego.parkingapi.string.*;
/**
 * Model for car park available update.
 *
 * Bugs: none known
 * Issues: none known
 * @author       Huynh Xuan An
 * @version      1.0
 */
@Entity
@SqlResultSetMapping(
	    name =Configuration.carParkResultSetMappingName,	 
	    classes = {
	        @ConstructorResult(
        		  targetClass = CarParkAvailableResult.class,
	            columns = {
	                @ColumnResult(name = "address")
	                , @ColumnResult(name = "latitude",type = Double.class)	 
	                , @ColumnResult(name = "longitude",type = Double.class)	 
	                , @ColumnResult(name = "total_lots", type = Integer.class)	 
	                , @ColumnResult(name = "available_lots",type = Integer.class)	 
	            }	          
	        )
	    }
	)

@Table (name = "car_park_availability")
public class CarParkAvailability {
	private String car_park_no;
    private ArrayList<Object> carpark_info;
    private Integer  total_lots = 0;
    private Integer  available_lots = 0;	

	public CarParkAvailability() {
		super();
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("unchecked")
	 /**
     * Set total_lots, available_lots field base on carpark_info field
     */
	public void recalculateLots()
	{
		//each car park info node is a lot in JSON format, that store 1 or many lot_type with particular total lot and available lot for each lot type
		//scan car park info set and sum total lot and available lot for each lot type
		for(Object obj : carpark_info) {
			String info = obj.toString();
			HashMap<String, Object> map = new Gson().fromJson(info, HashMap.class);		
			
			total_lots += (int) Double.parseDouble( map.get("total_lots").toString());		
			available_lots += (int) Double.parseDouble(map.get("lots_available").toString());
		}		
	}

	@Override
    public String toString() {
		recalculateLots();
        return "CarPark [car_park_no=" + car_park_no + ", available_lots=" +available_lots + "]";
    }
	
	@Id
	@Column (name ="car_park_no")
	public String getCar_park_no() {
		return car_park_no;
	}
	public void setCar_park_no(String car_park_no) {
		this.car_park_no = car_park_no;
	}
	@Column (name ="total_lots")
	public Integer getTotal_lots() {
		return total_lots;
	}
	public void setTotal_lots(Integer total_lots) {
		this.total_lots = total_lots;
	}
	@Column (name ="available_lots")
	public Integer getAvailable_lots() {
		return available_lots;
	}
	public void setAvailable_lots(Integer available_lots) {
		this.available_lots = available_lots;
	}	
}
