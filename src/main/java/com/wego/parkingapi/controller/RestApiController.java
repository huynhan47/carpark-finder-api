/**
 * Controller for Car Park Finder API.
 *
 * Bugs: none known
 *
 * @author       Huynh Xuan An
 * @version      1.0
 */
package com.wego.parkingapi.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wego.parkingapi.model.CarParkAvailableResult;

@RestController
@RequestMapping("/carparks")
public class RestApiController {

	 /**
     * Update car park availability and return status code
     */
	@GetMapping(value = "/update")
	public HttpStatus updateCarparkAvailability()
	{
		ProcessingController processor = new ProcessingController();
		try {
			processor.updateCarparkAvailability();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.OK;
	}
	 /**
     * Find nearest car park availability base on latitude, longitude input
     */
	@GetMapping(value = "/nearest")
	@ResponseBody
	public List<CarParkAvailableResult> findNearestCarpark(
			@RequestParam(required = true) double latitude
			,@RequestParam(required = true) double longitude
			,@RequestParam(required = true) int page
			,@RequestParam(required = true) int  per_page
			) {
		List<CarParkAvailableResult> result;
		ProcessingController processor = new ProcessingController();	
		result = processor.getNearestCarparkInfo(latitude,longitude,page,per_page);	
		return result;
	}
	
	
}
