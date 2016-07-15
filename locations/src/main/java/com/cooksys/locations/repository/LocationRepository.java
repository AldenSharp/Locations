package com.cooksys.locations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.locations.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

	Location findByTitle(String title);

	List<Location> findByNum(int num);

}
