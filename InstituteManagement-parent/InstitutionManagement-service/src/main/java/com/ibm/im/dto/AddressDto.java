package com.ibm.im.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {
	private Integer id;
	
	private String street;
	
	private String city;
	
	private String state; 
	
	private String type;

}
