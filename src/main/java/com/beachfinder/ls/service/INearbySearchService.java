package com.beachfinder.ls.service;

import java.util.List;

import com.beachfinder.ls.web.dto.BeachDTO;

public interface INearbySearchService {
	List <BeachDTO> findNearbyPlaces(String latitude, String longitude);
	
	List<String> getCities(String strCountry);

	String getLatLongFromPlaceName(String strPlaceName);

}
