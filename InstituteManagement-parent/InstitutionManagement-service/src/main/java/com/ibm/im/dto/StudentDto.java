package com.ibm.im.dto;

import java.util.List;

import com.ibm.im.entity.AddressEntity;
import com.ibm.im.entity.MappingEntity;

public class StudentDto {
	private Integer id;
	private String name;
	
	private List<CourseDto> courseList;
	private List<AddressDto> addressList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CourseDto> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<CourseDto> courseList) {
		this.courseList = courseList;
	}
	public List<AddressDto> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<AddressDto> addressList) {
		this.addressList = addressList;
	}

	
}
