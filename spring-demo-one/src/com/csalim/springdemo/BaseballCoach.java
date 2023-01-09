package com.csalim.springdemo;

public class BaseballCoach implements Coach {
	//define private field for dependency
	private FortuneService fortuneService;
	
	//define constructor for dependency injection
	public BaseballCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Spend 30 minutes in batting cage";
	}

	@Override
	public String getDailyFortune() {		
		//use my fortuneService to get a fortune
		return fortuneService.getFortune();
	}
}
