package com.csalim.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ComponentScan("com.csalim.springdemo")
@PropertySource("classpath:fortune.properties")
public class SportConfig {
	
	//define bean for our sad fortune service
	@Bean
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}
	
	//define bean for swim coach
	@Bean
	public Coach swimCoach() {
		//We pass in the method call from above to get the sadFortuneService
		return new SwimCoach(sadFortuneService());
	}
}
