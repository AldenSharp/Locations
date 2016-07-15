package com.cooksys.locations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.locations.entity.AreaCode;
import com.cooksys.locations.entity.User;
import com.cooksys.locations.repository.AreaCodeRepository;
import com.cooksys.locations.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AreaCodeRepository areaCodeRepository;
	
	// New user.
	public String newUser(User user) {

		if (userRepository.findByName(user.getName()) != null) {
			return "Error: Username already registered.";
		} else {
			if (areaCodeRepository.findByNum(user.getNum()) == null) {
				user.setNum(0);
			} else {
				AreaCode areaCode = areaCodeRepository.findByNum(user.getNum());
				if (areaCode.getRegisteredUserCount() >= areaCode.getTotalCount()) {
					areaCode.setRegisteredUserCount(areaCode.getTotalCount());
					areaCodeRepository.saveAndFlush(areaCode);
					return "Critical error: Registered user count exceeding total user count.";
				}
				areaCode.incrementRegisteredUserCount();
				areaCodeRepository.saveAndFlush(areaCode);
			}
			userRepository.saveAndFlush(user);
			if(user.getNum() <= 0) {
				return "User " + user.getName() + " registered in undetermined area.";
			} else {
				return "User " + user.getName() + " registered in area #" + user.getNum() + ".";
			}
			
		}
	}
	
	// Log in.
	public String login(User user) {
		if (userRepository.findByName(user.getName()) == null || !userRepository.findByName(user.getName()).getPassword().equals(user.getPassword())) {
			return "Error: Incorrect username or password.";
		} else {
			AreaCode areaCode = areaCodeRepository.findByNum(user.getNum()); 
			if(areaCode.getAnonymousCount() <= 0) {
				areaCode.setAnonymousCount(0);
				areaCodeRepository.saveAndFlush(areaCode);
				return "Critical error: Logged in user count exceeding total user count.";
			}
			user.setId(userRepository.findByName(user.getName()).getId());
			userRepository.saveAndFlush(user);
			areaCode.decrementAnonymousCount();
			areaCode.incrementLoggedInUserCount();
			return "User " + user.getName() + " logged in from area #" + user.getNum() + ".";	
		}
	}

}
