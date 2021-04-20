package com.ibm.im.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateStudentAddressRequestDto {
	private String street;
	private String city;
	private String state;
	private String type;


}
