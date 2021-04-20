package com.ibm.im.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(value = Include.NON_NULL)
public class ResponseDto {
	private Integer code;
	@JsonInclude(value = Include.NON_DEFAULT)
	private int status;

	private String userMessage;

	
}
