package com.beachfinder.ls.service.impl;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.beachfinder.ls.service.IBeachFinderService;
import com.fasterxml.jackson.databind.JsonNode;



@Service
@PropertySource("classpath:UV.properties")
public class BeachFinderServiceImpl implements IBeachFinderService {
	
	@Value ("${OpenUV_URL}")
	private String url; 

	@Value ("${UV.lat}")
	private String lat;
	
	@Value ("${UV.long}")
	private String longitude;
	
	@Value ("${UV.date}")
	private String strDate;
	
	private static final Logger LOG = LoggerFactory.getLogger(BeachFinderServiceImpl.class);
    
    public BeachFinderServiceImpl() {
        
    }

    @Override
    public String findByCountry(String strCountry) {
    	LOG.debug("Beach Finder Service Impl >> Finding by country {}",strCountry);
    	
  	  Map<String, String> uriVariables = new HashMap<String, String>();
  	  uriVariables.put("lat", lat);
  	  uriVariables.put("lng", longitude);
  	  uriVariables.put("dt", strDate);
  	  
  	  RestTemplate restTemplate = new RestTemplate();
  	  
  	  	  
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-access-token", "21acfb2c83bc81d36f8966215e91b84e");
        
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
  	  
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class, uriVariables);
        
    	LOG.debug("Beach Finder Service Impl >> response is {}",response);

        
        String json = response.toString();
        if (json != null && !json.isEmpty())
        {
        	ObjectMapper objectMapper = new ObjectMapper();
        	try {
        		json = json.substring(5);
        		json = json.substring(0, json.indexOf(",[Server:"));
        		
        		JsonNode jsonNode = objectMapper.readTree(json);
        		
        		String strUV = jsonNode.get("result").get("uv").asText();
        		String strUVMax = jsonNode.get("result").get("uv_max").asText();
        		//String strSunrise = jsonNode.get("sunrise").asText();
        		//String strSunset = jsonNode.get("sunset").asText();
        		
        		LOG.debug("Beach Finder Service Impl >> uv =  {},{}",strUV,strUVMax);
        		
        		
        	} // end try
        	catch (IOException ioException) {
        		LOG.debug("Beach Finder Service Impl >> got exception =  {}",ioException.toString());
        	} // end catch
        	
        }
        
        return response.toString();
        
        
    }


}
