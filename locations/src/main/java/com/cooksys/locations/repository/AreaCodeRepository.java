package com.cooksys.locations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.locations.entity.AreaCode;

public interface AreaCodeRepository extends JpaRepository<AreaCode, Long> {
	
	AreaCode findByNum(int num);
	AreaCode findByLabel(String label);

}
