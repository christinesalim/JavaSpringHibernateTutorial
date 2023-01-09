package com.csalim.springdemo;

public class BasketballCoach implements Coach {
	//define private field for dependency
	private FortuneService fortuneService;
	
	//define constructor for dependency injection
	public BasketballCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	} 
	
	@Override
	public String getDailyWorkout() {
		return "Dribble the ball for 10 minutes and make 3 free throws";
	}

	@Override
	public String getDailyFortune() {
		
		return fortuneService.getFortune();
	}

}
