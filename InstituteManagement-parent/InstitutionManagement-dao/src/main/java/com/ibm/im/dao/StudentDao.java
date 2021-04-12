package com.ibm.im.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.im.entity.StudentEntity;

@Component
public class StudentDao {
	@Autowired
	private EntityManager entityManager;
	 
	@Transactional
	public int save(StudentEntity entity) {
		entityManager.persist(entity);
		return 1;
	}
	
	public StudentEntity findById(Integer id) {
		StudentEntity entity = entityManager.find(StudentEntity.class, id);
		return entity;
		
	}
}
