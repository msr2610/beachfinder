package com.beachfinder.ls.web.controller;


import java.util.List;
import java.util.stream.Collectors;

//import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beachfinder.ls.service.IBeachFinderService;
import com.beachfinder.ls.service.INearbySearchService;



@RestController
@RequestMapping(value = "/beachfinder")
public class BeachFinderController {

    private IBeachFinderService beachFinderService;
    
    private INearbySearchService nearbySearchService;

    public BeachFinderController(IBeachFinderService beachFinderService, INearbySearchService nearbySearchService) {
        this.beachFinderService = beachFinderService;
        this.nearbySearchService = nearbySearchService;
    }

    @GetMapping(value = "/{countryName}")
    public String findByCountryName(@PathVariable String countryName) {
        return beachFinderService.findByCountry(countryName);
    }
    
    @GetMapping(value = "/cities/{countryName}")
    public String getCities(@PathVariable String countryName) {
    	List <String> listCities = nearbySearchService.getCities(countryName);
    	String strDelimeter = ",";
    	
    	String result = listCities.stream().map(Object::toString).collect(Collectors.joining(strDelimeter));
    	
    	return result;
    	
    }
    
    @GetMapping(value = "/geocode/{placeName}")
    public String getGeocode(@PathVariable String placeName) {
    	String strLatLong = nearbySearchService.getLatLongFromPlaceName(placeName);
    	
    	return strLatLong;
    	
    }


}