package com.ibm.im.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetStudentResponseDto {
	
	private String studentName;
	private Integer aadharNo;
	@JsonProperty(value = "addresses")
	private List<AddressDto> addressDtoList;
	@JsonProperty(value = "courses")
	private List<CourseDto> courseDtoList;

}
