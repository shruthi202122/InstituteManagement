package com.ibm.im.dto;

public class UpdateCourseRequestDto {
	private Integer courseId;
	private Integer durationDays;
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(Integer durationDays) {
		this.durationDays = durationDays;
	}

	

}
