package com.ibm.im.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.im.dto.CourseCreateRequestDto;
import com.ibm.im.dto.RemoveCourseMappingsRequestDto;
import com.ibm.im.dto.ResponseDto;
import com.ibm.im.dto.UpdateCourseRequestDto;
import com.ibm.im.entity.CourseEntity;
import com.ibm.im.entity.StudentCourseMappingEntity;
import com.ibm.im.entity.StudentEntity;
import com.ibm.im.repository.CourseRepository;
import com.ibm.im.repository.StudentCourseMappingRepository;

@Service
public class CourseService {

	private Logger logger= LoggerFactory.getLogger(CourseService.class);
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentCourseMappingRepository studentCourseMappingRepository;

	public ResponseDto createCourse(CourseCreateRequestDto requestDto) {
		logger.info("from createCourse()-CourseService");
		ResponseDto responseDto = new ResponseDto();
		if (requestDto.getName() == null || requestDto.getName().trim().equals("")) {
			logger.info("Checking null");
			throw new BadRequestException("Trying to insert Null values");

		}
		System.out.println("searching for course name");
		// CourseEntity entity = courseDao.findById(requestDto.getId());
		Optional<CourseEntity> optional = courseRepository.findByName(requestDto.getName());

		if (optional.isPresent()) {
			// courseEntity=optional.get();
			System.out.println("validation for course is already exist");
			throw new BadRequestException("course already exist");
		}

		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setName(requestDto.getName());
		courseEntity.setDurationDays(requestDto.getDurationDays());
		// courseDao.save(courseEntity);
		System.out.println("inserting course");
		courseRepository.save(courseEntity);
		responseDto.setUserMessage("course created successfully");
		return responseDto;
	}

	public ResponseDto updateCourse(@RequestBody UpdateCourseRequestDto requestDto) {
		logger.info("from CourseService");
		ResponseDto responseDto = new ResponseDto();
		Integer courseId = requestDto.getCourseId();
		Integer duration = requestDto.getDurationDays();
		if ((courseId == null) || (duration == null)) {
			logger.info("Checking null");
			throw new BadRequestException("Trying to update course with null values");
		}
		Optional<CourseEntity> optional = courseRepository.findById(courseId);
		if (optional.isEmpty()) {
			logger.info("Checking whether course is existing or not ");
			throw new BadRequestException("course not exist");
		}
		logger.info("Updating started");
		CourseEntity courseEntity = optional.get();
		courseEntity.setDurationDays(duration);
		courseRepository.save(courseEntity);
		responseDto.setUserMessage("course Updated Successfully");
		return responseDto;
	}

	public ResponseDto removeCourseMappings(RemoveCourseMappingsRequestDto requestDto) {
		ResponseDto responseDto = new ResponseDto();
		logger.info("From removeCourseMappings()-Service");
		
		List<StudentCourseMappingEntity> studentCourseMappingEntities = studentCourseMappingRepository
				.findAllByCourseEntityId(requestDto.getCourseId());
		if(studentCourseMappingEntities.isEmpty()) {
			throw new BadRequestException("No mappings found with specified courseId");
		}
		logger.info("ready to delete data from db");

		studentCourseMappingRepository.deleteInBatch(studentCourseMappingEntities);
		
		responseDto.setUserMessage("Coursemappings are deleted with specified data");
		return responseDto;
	}

}
