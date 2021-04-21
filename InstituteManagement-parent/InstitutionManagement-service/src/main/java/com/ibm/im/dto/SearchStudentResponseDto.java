package com.ibm.im.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchStudentResponseDto {
	private List<StudentDto2> studentData;
}
