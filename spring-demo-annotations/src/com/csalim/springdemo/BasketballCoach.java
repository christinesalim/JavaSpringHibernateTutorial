package com.csalim.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BasketballCoach implements Coach {
	private FortuneService fortuneService;
	
	public BasketballCoach() {
		System.out.println(">> BasketballCoach: inside default constructor");
	}
	
	@Override
	public String getDailyWorkout() {
		return "Dribble your ball for 10 minutes on both hands";
	}

	@Override
	public String getDailyFortune() {
		
		return "You will win the game today!";
	}
	
	@Autowired
	@Qualifier("happyFortuneService")
	public void setFortuneService( FortuneService fortService) {
		System.out.println(">> BasketballCoach: inside setFortuneService");
		fortuneService = fortService;
	}

}
