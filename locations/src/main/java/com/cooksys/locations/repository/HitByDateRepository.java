package com.cooksys.locations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.locations.entity.HitByDate;

public interface HitByDateRepository extends JpaRepository<HitByDate, Long> {
	
	List<HitByDate> findByNum(int num);

}
