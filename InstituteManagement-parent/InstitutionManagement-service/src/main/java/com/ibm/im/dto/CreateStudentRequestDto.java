package com.ibm.im.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateStudentRequestDto {
	private String name;
	private Integer aadharNo;
	private List<CreateStudentAddressRequestDto> addressDtoList;

}
