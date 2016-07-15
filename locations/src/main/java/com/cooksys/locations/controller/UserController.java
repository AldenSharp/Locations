package com.cooksys.locations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.locations.entity.User;
import com.cooksys.locations.service.UserService;

@RestController
@RequestMapping("")
public class UserController {

	@Autowired
	UserService userService;

	// New user.
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String newUser(@RequestBody User user) {
		return userService.newUser(user);
	}
	
	// Log in.
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestBody User user) {
		return userService.login(user);
	}

}
