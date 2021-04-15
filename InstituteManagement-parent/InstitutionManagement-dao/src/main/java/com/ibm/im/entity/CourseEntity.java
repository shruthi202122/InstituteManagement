package com.ibm.im.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Course")
public class CourseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer durationDays;

	@OneToMany(mappedBy = "courseEntity")
	private List<StudentCourseMappingEntity> studentCourseMappingEntities;

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

	public List<StudentCourseMappingEntity> getStudentCourseMappingEntities() {
		return studentCourseMappingEntities;
	}

	public void setStudentCourseMappingEntities(List<StudentCourseMappingEntity> studentCourseMappingEntities) {
		this.studentCourseMappingEntities = studentCourseMappingEntities;
	}

	public Integer getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(Integer durationDays) {
		this.durationDays = durationDays;
	}

}
