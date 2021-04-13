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
@Table(name = "Student")
public class StudentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "studentEntity")
	private List<MappingEntity> mappingEntities;
	
	@OneToMany(mappedBy = "studentEntity")
	private List<AddressEntity> addressEntities;

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

	public List<AddressEntity> getAddressEntities() {
		return addressEntities;
	}

	public void setAddressEntities(List<AddressEntity> addressEntities) {
		this.addressEntities = addressEntities;
	}


	
}
