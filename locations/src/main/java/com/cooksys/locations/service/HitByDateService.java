package com.cooksys.locations.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.locations.entity.HitByDate;
import com.cooksys.locations.repository.HitByDateRepository;

@Service
public class HitByDateService {
	
	@Autowired
	HitByDateRepository hitByDateRepository;

	public int getRecentHits(int num, long pastDays) {
		int count = 0;
		for (HitByDate hitByDate : hitByDateRepository.findByNum(num)) {
			long pastMilliseconds = (long) pastDays * 24 * 60 * 60 * 1000;
			Date today = new Date();
			if(hitByDate.getDate().after(new Date(today.getTime() - pastMilliseconds))) {
				count++;
			}
		}
		return count;
	}
	
	// Delete entries in the HitByDate table older than a year old.
	public void purge() {
		for (HitByDate hitByDate : hitByDateRepository.findAll()) {
			long pastYear = 365 * 24 * 60 * 60 * 1000;
			Date today = new Date();
			if(hitByDate.getDate().before(new Date(today.getTime() - pastYear))) {
				hitByDateRepository.delete(hitByDate);
			}
		}
	}
}
