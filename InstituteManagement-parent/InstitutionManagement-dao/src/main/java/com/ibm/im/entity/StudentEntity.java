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

@Entity
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

	@OneToMany(mappedBy = "studentEntity", cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE })
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

	public List<StudentCourseMappingEntity> getMappingEntities() {
		return mappingEntities;
	}

	public void setMappingEntities(List<StudentCourseMappingEntity> mappingEntities) {
		this.mappingEntities = mappingEntities;
	}

	public List<AddressEntity> getAddressEntities() {
		return addressEntities;
	}

	public void setAddressEntities(List<AddressEntity> addressEntities) {
		this.addressEntities = addressEntities;
	}

	public Integer getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(Integer aadharNo) {
		this.aadharNo = aadharNo;
	}

}
