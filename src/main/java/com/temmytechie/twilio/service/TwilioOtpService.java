package com.temmytechie.twilio.service;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temmytechie.twilio.config.TwilioConfig;
import com.temmytechie.twilio.dto.OtpStatus;
import com.temmytechie.twilio.dto.PasswordResetRequestDto;
import com.temmytechie.twilio.dto.PasswordResetResponseDto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import reactor.core.publisher.Mono;

@Service
public class TwilioOtpService {
	
	@Autowired
	private TwilioConfig twilioConfig;
	
	public Map<String, String> otpMap = new HashMap<>();

	public Mono<PasswordResetResponseDto>  sendOtpForPasswordReset(PasswordResetRequestDto passwordRequestDto)
	{
		PasswordResetResponseDto passwordResetResponseDto =null;
		
		
		try {
			PhoneNumber to = new PhoneNumber(PasswordResetRequestDto.getPhoneNumber());
			PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNUmber());
			String otp = generateOPT();
			String otpMessage ="Dear customer, Your OTP is ##" + otp + "##. Use this passcode to complete your transaction. Thank  you";
			
			
					
			Message message = Message.creator(to,from,otpMessage).create();	
			
			//use in memory database here to store the username and otp
			otpMap.put(passwordRequestDto.getUserName(), otp);
			
			passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.DELIVERED, otpMessage);
		}
		catch (Exception e) {
			passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.FAILED, e.getMessage());
		}
		
		return Mono.just(passwordResetResponseDto);	
				
	}
	
	public Mono<String> validOtp(String userInputOtp, String userName){
		if(userInputOtp.equals(otpMap.get(userName)))
		{
			otpMap.remove(userName, userInputOtp);
			return Mono.just(("Valid otp please proceed with your transaction !"));
		}
		else {
			return Mono.error(new IllegalAccessException("Invalid otp please retry"));
		}
		
	}
	
	
	//6 digit otp
	private String generateOPT()
	{
		return new DecimalFormat("000000")
				.format(new Random().nextInt(999999));
	}
}
