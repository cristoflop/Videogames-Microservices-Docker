package com.formation.videogames.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {
	
	public static void main(String[] args) {
		RetryTemplate template = new RetryTemplate();
		AlwaysRetryPolicy policy = new AlwaysRetryPolicy();
		template.setRetryPolicy(policy);
		template.execute(context -> {
			SpringApplication.run(Application.class, args);
			return true;
		});
	}
	
}
