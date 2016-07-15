package com.cooksys.locations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.locations.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByName(String name);
	
	List<User> findByNum(int num);

}
