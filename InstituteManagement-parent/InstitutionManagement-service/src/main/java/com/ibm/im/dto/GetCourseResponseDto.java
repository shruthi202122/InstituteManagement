package com.ibm.im.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetCourseResponseDto {
	private String courseName;
	private Integer durationDays;
}
