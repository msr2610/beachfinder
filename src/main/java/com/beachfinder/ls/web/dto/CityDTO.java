package com.beachfinder.ls.web.dto;

public class CityDTO {
	
	private String name;
	
	
	@Override
	public String toString() {
		return "BeachDTO [name=" + name +  "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
