package com.csalim.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationsDemoApp {

	public static void main(String[] args) {
		//Read spring config file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		//Get the bean from the container using the bean ID
		Coach theCoach = context.getBean("tennisCoach", Coach.class);
		
		//Call method on a bean
		System.out.println(theCoach.getDailyWorkout());
		
		//Get the daily fortune
		System.out.println(theCoach.getDailyFortune());
		
		//Close the context
		context.close();
	}

}
