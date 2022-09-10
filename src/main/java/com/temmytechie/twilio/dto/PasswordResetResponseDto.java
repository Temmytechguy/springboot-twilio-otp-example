package com.temmytechie.twilio.dto;

import lombok.Data;

@Data
public class PasswordResetResponseDto {
	
	private OtpStatus status;
	public PasswordResetResponseDto(OtpStatus delivered, String otpMessage) {
		// TODO Auto-generated constructor stub
	}
	public OtpStatus getStatus() {
		return status;
	}
	public void setStatus(OtpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;
	

}
