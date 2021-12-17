/**
 * Model for car park available result set.
 *
 * Bugs: none known
 * Issues: none known
 * @author       Huynh Xuan An
 * @version      1.0
 */

package com.wego.parkingapi.model;

public class CarParkAvailableResult {
	private String address;
	private double latitude;
	private double longitude;
	private int total_lots;
	private int available_lots;
	
	public CarParkAvailableResult(String address, double latitude, double longitude, int totalLots, int availableLots) {
		super();
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.total_lots = totalLots;
		this.available_lots = availableLots;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getTotal_lots() {
		return total_lots;
	}

	public void setTotal_lots(int total_lots) {
		this.total_lots = total_lots;
	}

	public int getAvailable_lots() {
		return available_lots;
	}

	public void setAvailable_lots(int available_lots) {
		this.available_lots = available_lots;
	}
	
}
