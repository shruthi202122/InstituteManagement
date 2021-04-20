package com.ibm.im.controller.exception.handler;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class RestApiExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ResponseDto> handleException(Exception e){
		System.out.println("inside exceptionHandler");
		e.printStackTrace();
		ResponseDto responseDto = new ResponseDto();
		responseDto.setUserMessage("Something went wrong");
		ResponseEntity<ResponseDto> responseEntity = new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;	
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseDto badRequesthandleException(BadRequestException e){
		System.out.println("inside exceptionHandler");
		e.printStackTrace();
		ResponseDto responseDto = new ResponseDto();
		responseDto.setUserMessage(e.getMessage());
		return responseDto;
		
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseDto handleException(NotFoundException e){
		System.out.println("inside exceptionHandler");
		e.printStackTrace();
		ResponseDto responseDto = new ResponseDto();
		responseDto.setUserMessage(e.getMessage());
		return responseDto;
		
	}
	
}
