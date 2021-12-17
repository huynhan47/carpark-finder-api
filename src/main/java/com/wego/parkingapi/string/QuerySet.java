package com.wego.parkingapi.string;
/**
 * Configuration:  for declare configuration query
 *
 * Bugs: none known
 * Issues: none known
 * @author       Huynh Xuan An
 * @version      1.0
 */
public class QuerySet {
	public static final String getCarParkAvailabilityQuery="SELECT cpi.address as 'address',cpi.latitude as 'latitude',cpi.longitude as 'longitude', cpa.total_lots as 'total_lots', cpa.available_lots as 'available_lots'\r\n"
			+ "FROM parking.car_park_information cpi, car_park_availability cpa\r\n"
			+ "WHERE cpi.car_park_no = cpa.car_park_no\r\n"
			+ "AND cpa.available_lots > 0\r\n"
			+ "ORDER BY GETDISTANCE(:latitude,:longitude,cpi.latitude, cpi.longitude)  asc\r\n"
			+ "LIMIT :limit\r\n"
			+ "OFFSET :offset";
}
