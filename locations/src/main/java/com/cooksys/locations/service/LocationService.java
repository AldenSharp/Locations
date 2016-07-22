package com.cooksys.locations.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.locations.entity.AreaCode;
import com.cooksys.locations.entity.HitByDate;
import com.cooksys.locations.entity.Location;
import com.cooksys.locations.entity.User;
import com.cooksys.locations.model.AppArea;
import com.cooksys.locations.model.AppLocation;
import com.cooksys.locations.repository.AreaCodeRepository;
import com.cooksys.locations.repository.HitByDateRepository;
import com.cooksys.locations.repository.LocationRepository;
import com.cooksys.locations.repository.UserRepository;
import com.cooksys.locations.response.AnonymousCountResponse;
import com.cooksys.locations.response.MethodResponse;
import com.cooksys.locations.response.NumResponse;
import com.cooksys.locations.response.UserResponse;

@Service
public class LocationService {

	@Autowired
	AreaCodeRepository areaCodeRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	HitByDateService hitByDateService;
	
	@Autowired
	HitByDateRepository hitByDateRepository;
	
	// Get all num values (including hit counts in past 7 days, past 30 days, past 365 days, and all time).
	public List<NumResponse> getNums() {
		List<NumResponse> response = new ArrayList<NumResponse>();
		for (AreaCode areaCode : areaCodeRepository.findAll()) {
			int num = areaCode.getNum();
			hitByDateService.purge();
			int hitsPastWeek = hitByDateService.getRecentHits(num, 7l);
			int hitsPastMonth = hitByDateService.getRecentHits(num, 30l);
			int hitsPastYear = hitByDateService.getRecentHits(num, 365l);
			response.add(new NumResponse(areaCode, hitsPastWeek, hitsPastMonth, hitsPastYear));
		}
		return response;
	}
	
	// New area.
	public MethodResponse newArea(AppArea appArea) {
		if (areaCodeRepository.findByLabel(appArea.getLabel()) != null) {
			return new MethodResponse("Error: Label aready registered.", true);
		} else {
			AreaCode areaCode = new AreaCode(appArea, areaCodeRepository.count());
			areaCodeRepository.saveAndFlush(areaCode);
		}
		return new MethodResponse("New area registered!", false);
	}
	
	// New location.
	public MethodResponse newLocation(AppLocation appLocation) {
		if (appLocation.getNum() <= 0) {
			return new MethodResponse("Error: Invalid area code value.", true);
		} else if (locationRepository.findByTitle(appLocation.getTitle()) != null) {
			return new MethodResponse("Error: Location already registered.", true);
		} else {
			locationRepository.saveAndFlush(new Location(appLocation));
			if (areaCodeRepository.findByNum(appLocation.getNum()) == null) {
				areaCodeRepository.saveAndFlush(new AreaCode(appLocation.getNum()));
			}
			return new MethodResponse("New location registered!", false);
		}
	}

	// Increment anonymous count at the given area code.
	public MethodResponse incrementAnonymous(int num) {
		if (areaCodeRepository.findByNum(num) != null) {
			// Update AreaCode table.
			AreaCode areaCode = areaCodeRepository.findByNum(num);
			areaCode.setAnonymousCount(areaCode.getAnonymousCount() + 1);
			areaCodeRepository.saveAndFlush(areaCode);
			// Add to HitsByDate table.
			hitByDateRepository.saveAndFlush(new HitByDate(num));
			return new MethodResponse("Incremented anonymous count to " + areaCode.getAnonymousCount() + ".", false);
		} else {
			return new MethodResponse("Error: unregistered area code.", true);
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
