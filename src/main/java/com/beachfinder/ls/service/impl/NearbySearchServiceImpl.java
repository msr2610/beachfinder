package com.beachfinder.ls.service.impl;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

//import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectReader;
import com.beachfinder.ls.service.INearbySearchService;
import com.beachfinder.ls.web.dto.BeachDTO;
import com.beachfinder.util.*;


@Service
@PropertySource("classpath:UV.properties")
public class NearbySearchServiceImpl implements INearbySearchService {
	
	private static final Logger LOG = LoggerFactory.getLogger(NearbySearchServiceImpl.class);
	
	@Value("${UV.nearbyPlaceAPIRequest}")
	private String strNearbyPlacesAPIRequestURL;
	
	
	@Value("${UV.geocodeAPIURL}")
	private String strGeocodeAPIURL;
	
	@Override
	public String getLatLongFromPlaceName (String strPlaceName) {
		
		LOG.debug("Nearby Place Finder Service Impl >> Finding lat and long -- place name = {}", strPlaceName);
		
		LatLong latlong = new LatLong();
		
		Map<String, String> uriVariables = new HashMap<String, String>();
	  	uriVariables.put("address", strPlaceName);
	  	
	  	RestTemplate restTemplate = new RestTemplate();
	  	  
	  	  
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
   
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
  	  
        ResponseEntity<String> response = restTemplate.exchange(strGeocodeAPIURL, 
        		HttpMethod.GET, requestEntity, String.class, uriVariables);
    
        String json = response.toString();
        
        
        if (json != null && !json.isEmpty())
        {
        	ObjectMapper objectMapper = new ObjectMapper();
        	try {
        		json = json.substring(5);
        		json = json.substring(0, json.indexOf(",[Content-Type:"));
        		
        		JsonNode rootNode = objectMapper.readTree(json);
        		
        		JsonNode locationNode = rootNode.findValue("location");
        		
        		latlong.setLat(locationNode.get("lat").asText());
        		latlong.setLong(locationNode.get("lng").asText());
        		
        		
        	} // end try
        	catch (IOException ioException) {
        		LOG.debug("Beach Finder Service Impl >> getCities >> got exception =  {}",ioException.toString());
        	} // end catch
        	
        } // if
        
        return latlong.toString();
		
	} // getLatLongFromPlaceName
	
	
	@Override
	public ArrayList <BeachDTO> findNearbyPlaces(String strLatitude, String strLongitude) {
		
		ArrayList <BeachDTO> arrayListBeachDTO = new ArrayList<BeachDTO>();

		LOG.debug("Nearby Place Finder Service Impl >> Finding nearby beaches -- lat = {}, long =  {}", strLatitude, strLongitude);
    	
		
		String strUrl = strNearbyPlacesAPIRequestURL;
		
		String strLocation = strLatitude + "," + strLongitude;
		
		  Map<String, String> uriVariables = new HashMap<String, String>();
	  	  uriVariables.put("location", strLocation);
	  	  
	  	  RestTemplate restTemplate = new RestTemplate();
	  	  
	  	  	  
	        HttpHeaders headers = new HttpHeaders();
	        
	        headers.setContentType(MediaType.APPLICATION_JSON);
       
	        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
	  	  
	        ResponseEntity<String> response = restTemplate.exchange(strUrl, 
	        		HttpMethod.GET, requestEntity, String.class, uriVariables);
	        
	        String json = response.toString();
	        if (json != null && !json.isEmpty())
	        {
	        	ObjectMapper objectMapper = new ObjectMapper();
	        	try {
	        		json = json.substring(5);
	        		json = json.substring(0, json.indexOf(",[Content-Type"));
	        		
	        		JsonNode rootNode = objectMapper.readTree(json);
	        		
	        		LOG.debug("Nearby Place Finder - JSON is = {}", json);
	        		
	        		JsonNode results = rootNode.path("results");
	        		
	        		Iterator<JsonNode> iterator = results.elements();
	        		while (iterator.hasNext()) {
	                    
	        			JsonNode resultNode = iterator.next();
	                    String strName = resultNode.get("name").asText();
	                    String strLat = resultNode.get("geometry").get("location").get("lat").asText();
	                    String strLng = resultNode.get("geometry").get("location").get("lng").asText();
	                    LOG.debug("Finding beaches name = {}, lat = {}, long = {}", strName, strLat, strLng);
	                    
	                    BeachDTO beachDTO = new BeachDTO();
	                    beachDTO.setName(strName);
	                    beachDTO.setLat(strLat);
	                    beachDTO.setLongitude(strLng);
	                    arrayListBeachDTO.add(beachDTO);
	                 }
	        		
	        		
	        	} // end try
	        	catch (IOException ioException) {
	        		LOG.debug("Beach Finder Service Impl >> got exception =  {}",ioException.toString());
	        	} // end catch
	        	
	        }
	        
	       return arrayListBeachDTO;
		// return response.toString();
	}

	@Override
	public List<String> getCities(String strCountry) {
		
		System.out.println ("Before");
		System.out.println ("After");
		
		List<String> alCities = new ArrayList<String>();
		
		String strUrl = "https://countriesnow.space/api/v0.1/countries/cities?country={country}";
		
		LOG.debug("Nearby Place Finder Service Impl >> getting list of cities -- URL = {}", strUrl);
		
		  Map<String, String> uriVariables = new HashMap<String, String>();
	  	  uriVariables.put("country", strCountry);
	  	  
	  	  RestTemplate restTemplate = new RestTemplate();
	  	  
	  	  HttpHeaders headers = new HttpHeaders();
	        
	        headers.setContentType(MediaType.APPLICATION_JSON);
       
	        HttpEntity<String> requestEntity = new HttpEntity<String>("{\"country\":\"" + strCountry + "\"}",headers);
	  	  
	        ResponseEntity<String> response = restTemplate.exchange(strUrl, 
	        		HttpMethod.POST, requestEntity, String.class, uriVariables);
	        
	        String json = response.toString();
	        
	        if (json != null && !json.isEmpty())
	        {
	        	ObjectMapper objectMapper = new ObjectMapper();
	        	try {
	        		json = json.substring(5);
	        		json = json.substring(0, json.indexOf(",[Date:"));
	        		
	        		LOG.debug("Cities >> getting list of cities -- URL = {}", json);
	        		
	        		JsonNode rootNode = objectMapper.readTree(json);
	        		
	        		JsonNode results = rootNode.path("data");
	        		
	        		LOG.debug("Cities >> results -- URL = {}", results);
	        		
	        		json = results.toString();
	        		
	        		json = json.substring(1);
	        		json = json.substring(0, json.indexOf("]"));
	        		
	        		String arrCities[] = json.split(",");
	        		
	        		alCities = (List<String>) Arrays.asList(arrCities);
	        		
	        		
	        	} // end try
	        	catch (IOException ioException) {
	        		LOG.debug("Beach Finder Service Impl >> getCities >> got exception =  {}",ioException.toString());
	        	} // end catch
	        	
	        }
	        return alCities;
		// return response.toString();
	}
	
}


