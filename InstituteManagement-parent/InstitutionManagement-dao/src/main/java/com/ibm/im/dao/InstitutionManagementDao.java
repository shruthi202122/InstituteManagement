package com.ibm.im.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.im.entity.StudentEntity;

@Component
public class InstitutionManagementDao {
	@Autowired
	private EntityManager entityManager;
	public List<StudentEntity> getStudentData() {
		String jpql="select s from StudentEntity s";
		TypedQuery<StudentEntity> query = entityManager.createQuery(jpql, StudentEntity.class);
		List<StudentEntity> resultList = query.getResultList();
		
		return resultList;
	}
}
