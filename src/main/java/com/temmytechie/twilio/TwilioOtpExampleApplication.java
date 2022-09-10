package com.temmytechie.twilio;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.temmytechie.twilio.config.TwilioConfig;
import com.twilio.Twilio;

@SpringBootApplication
public class TwilioOtpExampleApplication {
	
	@Autowired
	private TwilioConfig twilioConfig;
	
	@PostConstruct
	public void intiTwilio() {
		
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
		
	}

	public static void main(String[] args) {
		SpringApplication.run(TwilioOtpExampleApplication.class, args);
	}

}
