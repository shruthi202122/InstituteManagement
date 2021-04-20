package com.ibm.im.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MappingStudentToCourseRequestDto {
	private Integer id;
	private Integer studentId;
	private List<Integer> courseIdList;

	
}
