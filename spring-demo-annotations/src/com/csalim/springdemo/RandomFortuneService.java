package com.csalim.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {

	//create an array of strings containing fortunes
	private String[] data = {
			"Beware of the wolf in sheep's clothing",
			"Diligence is the mother of good luck",
			"The journey is the reward"
	};
	
	//Create a random generator
	private Random rand = new Random();
	
	@Override
	public String getFortune() {
		//pick a random string from the array of fortunes
		int index = rand.nextInt(data.length);
		return data[index];
	}

}
