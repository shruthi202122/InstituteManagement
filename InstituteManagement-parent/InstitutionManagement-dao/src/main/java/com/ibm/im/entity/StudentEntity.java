package com.ibm.im.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Student")
public class StudentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false,unique = true)
	private Integer aadharNo;

	@OneToMany(mappedBy = "studentEntity",cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE} )
	private List<StudentCourseMappingEntity> mappingEntities;

	@OneToMany(mappedBy = "studentEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE },orphanRemoval = true)
	private List<AddressEntity> addressEntities;

	
}
