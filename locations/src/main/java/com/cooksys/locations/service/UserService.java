package com.cooksys.locations.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.locations.entity.AreaCode;
import com.cooksys.locations.entity.User;
import com.cooksys.locations.model.AppUser;
import com.cooksys.locations.repository.AreaCodeRepository;
import com.cooksys.locations.repository.UserRepository;
import com.cooksys.locations.response.MethodResponse;

@Service
public class UserService {
	
	public Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	AreaCodeRepository areaCodeRepository;
	
	// New user.
	public MethodResponse newUser(User user) {

		if (userRepository.findByName(user.getName()) != null) {
			return new MethodResponse("Error: Username already registered.", true);
		} else {
			if (areaCodeRepository.findByNum(user.getNum()) == null) {
				user.setNum(0);
			} else {
				AreaCode areaCode = areaCodeRepository.findByNum(user.getNum());
				if (areaCode.getRegisteredUserCount() >= areaCode.getTotalCount()) {
					areaCode.setRegisteredUserCount(areaCode.getTotalCount());
					areaCodeRepository.saveAndFlush(areaCode);
					return new MethodResponse("Critical error: Registered user count exceeding total user count.", true);
				}
				areaCode.incrementRegisteredUserCount();
				areaCodeRepository.saveAndFlush(areaCode);
			}
			userRepository.saveAndFlush(user);
			if(user.getNum() <= 0) {
				return new MethodResponse("User " + user.getName() + " registered in undetermined area.", false);
			} else {
				return new MethodResponse("User " + user.getName() + " registered in area #" + user.getNum() + ".", false);
			}
			
		}
	}
	
	// Log in.
	public User login(AppUser appUser) {
		if (userRepository.findByName(appUser.getName()) == null || !userRepository.findByName(appUser.getName()).getPassword().equals(appUser.getPassword())) {
			return new User("unrecognized");
		} else {
			return userRepository.findByName(appUser.getName());
		}
	}
	
	// Log in from a specific area.
	public User loginNum(AppUser appUser, int num) {
		if (userRepository.findByName(appUser.getName()) == null || !userRepository.findByName(appUser.getName()).getPassword().equals(appUser.getPassword())) {
			return new User("unrecognized");
		} else {
			User user = userRepository.findByName(appUser.getName());
			AreaCode areaCode = areaCodeRepository.findByNum(num); 
			if(areaCode.getAnonymousCount() <= 0) {
				areaCode.setAnonymousCount(0);
				areaCodeRepository.saveAndFlush(areaCode);
				log.error("Critical: Logged in user count exceeding total user count.");
			}
			user.setId(userRepository.findByName(user.getName()).getId());
			user.setNum(num);
			userRepository.saveAndFlush(user);
			areaCode.decrementAnonymousCount();
			areaCode.incrementLoggedInUserCount();
			return user;
		}
	}
	
	// Log out.
	public User logout(String username) {
		User user = userRepository.findByName(username);
		AreaCode areaCode = areaCodeRepository.findByNum(user.getNum());
		if(areaCode.getLoggedInUserCount() <= 0) {
			areaCode.setLoggedInUserCount(0);
			areaCodeRepository.saveAndFlush(areaCode);
			log.error("Critical: Negative logged in user count.");
		}
		areaCode.decrementLoggedInUserCount();
		areaCode.incrementAnonymousCount();
		return new User("unrecognized");
	}

}
