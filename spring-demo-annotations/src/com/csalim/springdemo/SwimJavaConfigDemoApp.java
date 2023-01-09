package com.csalim.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwimJavaConfigDemoApp {

	public static void main(String[] args) {
		//Read spring config java file
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SportConfig.class);
		
		
		//Get the bean from the container using the bean ID
		SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class);
		
		//Call method on a bean
		System.out.println(theCoach.getDailyWorkout());
		
		//Get the daily fortune
		System.out.println(theCoach.getDailyFortune());
		
		System.out.println("team: " + theCoach.getTeam());
		System.out.println("email: " + theCoach.getEmail());
		
		
		
		
		//Close the context
		context.close();
	}

}
