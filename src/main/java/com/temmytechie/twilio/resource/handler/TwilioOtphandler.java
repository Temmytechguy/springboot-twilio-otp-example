package com.temmytechie.twilio.resource.handler;

import com.temmytechie.twilio.dto.PasswordResetRequestDto;
import com.temmytechie.twilio.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.yaml.snakeyaml.tokens.DocumentEndToken;


import reactor.core.publisher.Mono;

@Component
public class TwilioOtphandler {
	
	@Autowired
	private TwilioOtpService service;
	
	public Mono<ServerResponse> sendOTP(ServerRequest serverRequest)
	{
		return serverRequest.bodyToMono(PasswordResetRequestDto.class)
				.flatMap(dto -> service.sendOtpForPasswordReset(dto))
				.flatMap(dto -> ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(dto)));
				
	}
	
	public Mono<ServerResponse> validateOTP(ServerRequest serverRequest)
	{
		return serverRequest.bodyToMono(PasswordResetRequestDto.class)
				.flatMap(dto -> service.validOtp(dto.getOneTimePassword(), dto.getUserName()))
				.flatMap(dto -> ServerResponse.status(HttpStatus.OK).bodyValue(dto));					
				
	}
	
	

}
