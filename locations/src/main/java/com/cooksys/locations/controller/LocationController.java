package com.cooksys.locations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.locations.model.AppArea;
import com.cooksys.locations.model.AppLocation;
import com.cooksys.locations.repository.AreaCodeRepository;
import com.cooksys.locations.repository.LocationRepository;
import com.cooksys.locations.response.AnonymousCountResponse;
import com.cooksys.locations.response.AreaCodeResponse;
import com.cooksys.locations.response.ConversionRateResponse;
import com.cooksys.locations.response.MethodResponse;
import com.cooksys.locations.response.NumResponse;
import com.cooksys.locations.response.UserResponse;
import com.cooksys.locations.service.LocationService;

@RestController
@RequestMapping("location")
public class LocationController {

	@Autowired
	LocationService locationService;
	
	@Autowired
	AreaCodeRepository areaCodeRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	// Get all num values (including hit counts in past 7 days, past 30 days, past 365 days, and all time).
	@RequestMapping(value = "nums", method = RequestMethod.GET)
	public List<NumResponse> getNums() {
		return locationService.getNums();
	}
	
	// New area.
	@RequestMapping(value = "new", method = RequestMethod.POST)
	public MethodResponse newArea(@RequestBody AppArea area) {
		return locationService.newArea(area);
	}

	// New location.
	@RequestMapping(value = "newlocation", method = RequestMethod.POST)
	public MethodResponse newLocation(@RequestBody AppLocation location) {
		return locationService.newLocation(location);
	}
	
	// Increment anonymous count at the given area code.
	@RequestMapping(value = "increment/{num}", method = RequestMethod.POST)
	public MethodResponse incrementAnonymous(@PathVariable int num) {
		return locationService.incrementAnonymous(num);
	}

	// Get anonymous count at the given area code.
	@RequestMapping(value = "anonymous/{num}", method = RequestMethod.GET)
	public AnonymousCountResponse getCountForAreaCode(@PathVariable int num) {
		return new AnonymousCountResponse(areaCodeRepository.findByNum(num));
	}

	// Get anonymous count at all area codes.
	@RequestMapping(value = "anonymous", method = RequestMethod.GET)
	public List<AnonymousCountResponse> getAllAreaCodes() {
		return locationService.getAllAreaCodes();
	}

	// Get conversion rate by area code.
	@RequestMapping(value = "conversion/{num}", method = RequestMethod.GET)
	public ConversionRateResponse getConversionRateByAreaCode(@PathVariable int num) {
		return new ConversionRateResponse(areaCodeRepository.findByNum(num));
	}
	
	// Get users by area code.
	@RequestMapping(value = "users/{num}", method = RequestMethod.GET)
	public List<UserResponse> getUsersByAreaCode(@PathVariable int num) {
		return locationService.getUsersByAreaCode(num);
	}
	
	// Get locations by area code.
	@RequestMapping(value = "byareacode/{num}", method = RequestMethod.GET)
	public AreaCodeResponse getLocationsByAreaCode(@PathVariable int num) {
		return new AreaCodeResponse(num, locationRepository.findByNum(num));
	}
	
}
