package com.ibm.im.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.im.entity.StudentCourseMappingEntity;

@Repository
public interface StudentCourseMappingRepository extends JpaRepository<StudentCourseMappingEntity, Integer> {

	List<StudentCourseMappingEntity> findAllByStudentEntityIdAndCourseEntityIdIn(Integer studentId,
			List<Integer> courseIdList);

	Optional<StudentCourseMappingEntity> findByStudentEntityIdAndCourseEntityId(Integer studentId,
			Integer courseId);

	List<StudentCourseMappingEntity> findAllByCourseEntityId(Integer courseId);

}
