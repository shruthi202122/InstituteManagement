package com.ibm.im.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.im.entity.CourseEntity;

@Component
public class CourseDao {
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public int save(CourseEntity course) {
		System.out.println("we are in CourseDao ");
		entityManager.persist(course);
		return 1;
	}
	public CourseEntity findById(Integer id) {
		System.out.println("from findById()");
		CourseEntity entity = entityManager.find(CourseEntity.class, id);
		
		return entity;
		
	}
}
