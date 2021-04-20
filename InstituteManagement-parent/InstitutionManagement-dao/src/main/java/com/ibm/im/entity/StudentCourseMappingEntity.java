package com.ibm.im.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Student_Course_Mapping",uniqueConstraints = @UniqueConstraint(columnNames = {"student_id","course_id"},name = "UK_STUDENT_COURSE"))
public class StudentCourseMappingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "student_id",foreignKey = @ForeignKey(name = "FK_MAPPING_2_STUDENT"))
	private StudentEntity studentEntity;
	
	@ManyToOne
	@JoinColumn(name = "course_id",foreignKey = @ForeignKey(name = "FK_MAPPING_2_COURSE"))
	private CourseEntity courseEntity;
	
}
