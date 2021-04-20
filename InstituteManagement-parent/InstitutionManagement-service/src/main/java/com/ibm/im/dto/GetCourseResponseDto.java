package com.ibm.im.dto;

import java.util.List;

import com.ibm.im.entity.StudentEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetCourseResponseDto {
	private String courseName;
	private Integer durationDays;
	private List<StudentDto2> studentDtoList;
}
