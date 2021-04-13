package com.ibm.im.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.im.entity.MappingEntity;

@Repository
public interface MappingRepository extends JpaRepository<MappingEntity, Integer> {

	List<MappingEntity> findAllByStudentEntityIdAndCourseEntityIdIn(Integer studentId, List<Integer> courseIdList);
}
