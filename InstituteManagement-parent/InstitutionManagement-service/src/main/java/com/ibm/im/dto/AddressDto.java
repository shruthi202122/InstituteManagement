package com.ibm.im.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(value = Include.NON_NULL)
public class AddressDto {
	
	private Integer id;
	
	private String street;
	
	private String city;
	
	private String state; 
	
	private String type;

}
