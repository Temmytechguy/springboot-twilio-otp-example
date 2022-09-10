package com.temmytechie.twilio.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwilioConfig {
	
	
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getTrialNUmber() {
		return trialNUmber;
	}
	public void setTrialNUmber(String trialNUmber) {
		this.trialNUmber = trialNUmber;
	}
	private String accountSid;
	private String authToken;
	private String trialNUmber;

}
