package com.ibm.im.dto;

public class CourseCreateRequestDto {

	private String name;
	private Integer durationDays;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(Integer durationDays) {
		this.durationDays = durationDays;
	}

}
