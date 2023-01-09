package com.csalim.springdemo;

public class TrackCoach implements Coach {
	private FortuneService fortuneService;
	
	public TrackCoach() {
		
	}
	
	public void doMyInitStuff() {
		System.out.println("Track coach doMyInitStuff() method called");
	}
	
	public void timeToCleanupStuff() {
		System.out.println("Track coach timeToCleanupStuff() method called");
	}
	
	
	
	public TrackCoach(FortuneService fortuneService) {
		super();
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5K";
	}

	@Override
	public String getDailyFortune() {
		
		return "Just do it: " + fortuneService.getFortune();
	}

}
