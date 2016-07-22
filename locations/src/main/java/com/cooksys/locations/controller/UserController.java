package com.cooksys.locations.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.locations.entity.User;
import com.cooksys.locations.model.AppUser;
import com.cooksys.locations.response.MethodResponse;
import com.cooksys.locations.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	// New user.
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public MethodResponse newUser(@RequestBody User user) {
		return userService.newUser(user);
	}
	
	// Log in.
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public User login(@RequestBody AppUser appUser) {
		return userService.login(appUser);
	}
	
	// Log in from a specific area.
	@RequestMapping(value = "login/{num}", method = RequestMethod.POST)
	public User loginNum(@RequestBody AppUser appUser, @PathVariable int num) {
		return userService.loginNum(appUser, num);
	}
	
	// Log out.
	@RequestMapping(value = "logout/{username}", method = RequestMethod.POST)
	public User logout(@PathVariable String username) {
		return userService.logout(username);
	}

}
