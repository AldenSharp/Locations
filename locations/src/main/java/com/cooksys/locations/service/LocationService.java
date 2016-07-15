package com.cooksys.locations.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.locations.entity.AreaCode;
import com.cooksys.locations.entity.Location;
import com.cooksys.locations.entity.User;
import com.cooksys.locations.model.AppLocation;
import com.cooksys.locations.repository.AreaCodeRepository;
import com.cooksys.locations.repository.LocationRepository;
import com.cooksys.locations.repository.UserRepository;
import com.cooksys.locations.response.AnonymousCountResponse;
import com.cooksys.locations.response.UserResponse;

@Service
public class LocationService {

	@Autowired
	AreaCodeRepository areaCodeRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	// New location.
	public String newLocation(AppLocation appLocation) {
		if (appLocation.getNum() <= 0) {
			return "Error: Invalid area code value.";
		} else if (locationRepository.findByTitle(appLocation.getTitle()) != null) {
			return "Error: Location already registered.";
		} else {
			locationRepository.saveAndFlush(new Location(appLocation));
			if (areaCodeRepository.findByNum(appLocation.getNum()) == null) {
				areaCodeRepository.saveAndFlush(new AreaCode(appLocation.getNum()));
			}
			return "New location registered!";
		}
	}

	// Increment anonymous count at the given area code.
	public String incrementAnonymous(int num) {
		if (areaCodeRepository.findByNum(num) != null) {
			AreaCode areaCode = areaCodeRepository.findByNum(num);
			areaCode.setAnonymousCount(areaCode.getAnonymousCount() + 1);
			areaCodeRepository.saveAndFlush(areaCode);
			return "Incremented anonymous count to " + areaCode.getAnonymousCount() + ".";
		} else {
			return "Error: unregistered area code.";
		}
	}

	// Get anonymous count at all area codes.
	public List<AnonymousCountResponse> getAllAreaCodes() {
		List<AnonymousCountResponse> response = new ArrayList<AnonymousCountResponse>();
		for (AreaCode areaCode : areaCodeRepository.findAll()) {
			response.add(new AnonymousCountResponse(areaCode));
		}
		return response;
	}

	// Get users by area code.
	public List<UserResponse> getUsersByAreaCode(int num) {
		List<UserResponse> response = new ArrayList<UserResponse>();
		for (User user : userRepository.findByNum(num)) {
			response.add(new UserResponse(user));
		}
		return response;
	}

}
