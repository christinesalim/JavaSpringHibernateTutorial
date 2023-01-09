package com.csalim.springdemo;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:fortune.properties")
public class FileFortuneService implements FortuneService {
	
	private Random rand = new Random();
	
	@Value("${fortune.values}")
	private String[] fortunes;
	
	@Override
	public String getFortune() {
		int index = rand.nextInt(fortunes.length);
		return fortunes[index];
	}

}

