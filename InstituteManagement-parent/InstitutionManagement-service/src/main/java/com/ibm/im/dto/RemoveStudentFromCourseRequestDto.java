package com.ibm.im.dto;

public class RemoveStudentFromCourseRequestDto {
	private Integer studentId;
	private Integer courseId;
	public Integer getStudentId() {
		return studentId; 
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
}
