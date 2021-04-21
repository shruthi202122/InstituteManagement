package com.ibm.im.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.im.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

	@Query(value = "select s from StudentEntity s where s.name like %:searchText% or s.aadharNo like %:searchText%")
	public List<StudentEntity> searchStudentsWithNameOrAadhar(String searchText);
	
	
}
