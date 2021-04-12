package com.ibm.im.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Course")
public class CourseEntity {
	@Id
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "courseEntity")
	private List<MappingEntity> mappingEntities;

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

	public List<MappingEntity> getMappingEntities() {
		return mappingEntities;
	}

	public void setMappingEntities(List<MappingEntity> mappingEntities) {
		this.mappingEntities = mappingEntities;
	}
	
	
	
}
