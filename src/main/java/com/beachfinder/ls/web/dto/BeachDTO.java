package com.beachfinder.ls.web.dto;

public class BeachDTO {
	
	private String name;
	private String lat;
	private String longitude;
	
	@Override
	public String toString() {
		return "BeachDTO [name=" + name + ", lat=" + lat + ", longitude=" + longitude + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
}
