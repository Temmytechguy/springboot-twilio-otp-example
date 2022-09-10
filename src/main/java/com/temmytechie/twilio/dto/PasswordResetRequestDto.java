package com.temmytechie.twilio.dto;

import lombok.Data;


@Data
public class PasswordResetRequestDto {
	
	
	public static String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOneTimePassword() {
		return oneTimePassword;
	}
	public void setOneTimePassword(String oneTimePassword) {
		this.oneTimePassword = oneTimePassword;
	}
	private static String phoneNumber; //destination
	private String userName;
	private String oneTimePassword;

}
