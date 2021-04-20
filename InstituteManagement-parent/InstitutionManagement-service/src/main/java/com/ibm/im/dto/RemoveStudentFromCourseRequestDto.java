package com.ibm.im.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RemoveStudentFromCourseRequestDto {
	private Integer studentId;
	private Integer courseId;
	
}
