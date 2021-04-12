package com.ibm.im.dto;

import java.util.List;

public class MappingStudentToCourseRequestDto {
	private Integer id;
	private Integer studentId;
	private List<Integer> courseIdList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public List<Integer> getCourseIdList() {
		return courseIdList;
	}
	public void setCourseIdList(List<Integer> courseIdList) {
		this.courseIdList = courseIdList;
	}
	
}
