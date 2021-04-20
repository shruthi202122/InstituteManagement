package com.ibm.im.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentDto {
	private Integer id;
	private String name;

	private List<CourseDto> courseList;
	private List<AddressDto> addressList;

}
