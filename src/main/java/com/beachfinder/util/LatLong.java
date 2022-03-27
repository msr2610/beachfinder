package com.beachfinder.util;

public class LatLong {

	
	@Override
	public String toString() {
		return "LatLong [lat=" + lat + ", longitude=" + longitude + "]";
	}
	String lat;
	String longitude;
	
	public LatLong(String lat, String longitude) {
		super();
		this.lat = lat;
		this.longitude = longitude;
	}
	public LatLong() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * @return the longitude
	 */
	public String getLong() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLong(String longitude) {
		this.longitude = longitude;
	}
	

}
