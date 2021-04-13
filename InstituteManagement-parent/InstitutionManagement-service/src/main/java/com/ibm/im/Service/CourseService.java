package com.ibm.im.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.im.dto.CourseCreateRequestDto;
import com.ibm.im.dto.ResponseDto;
import com.ibm.im.dto.UpdateCourseRequestDto;
import com.ibm.im.entity.CourseEntity;
import com.ibm.im.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;

	public ResponseDto createCourse(CourseCreateRequestDto requestDto) {
		System.out.println("from CourseService");
		ResponseDto responseDto = new ResponseDto();
		if (requestDto.getName() == null || requestDto.getName().trim().equals("")) {
			System.out.println("Checking null");
			responseDto.setCode(400);
			responseDto.setUserMessage("Invaild data recieved");
			return responseDto;
		}
		System.out.println("searching for course name");
		// CourseEntity entity = courseDao.findById(requestDto.getId());
		Optional<CourseEntity> optional = courseRepository.findByName(requestDto.getName());
		
		if (optional.isPresent()) {
			// courseEntity=optional.get();
			System.out.println("validation for course is already exist");
			responseDto.setCode(400);
			responseDto.setUserMessage("course already exist");
			return responseDto;
		}

		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setName(requestDto.getName());
		courseEntity.setDurationDays(requestDto.getDurationDays());
		// courseDao.save(courseEntity);
		System.out.println("inserting course");
		courseRepository.save(courseEntity);
		responseDto.setCode(200);
		responseDto.setUserMessage("course created successfully");
		return responseDto;
	}

	public ResponseDto updateCourse(@RequestBody UpdateCourseRequestDto requestDto) {
		System.out.println("from CourseService");
		ResponseDto responseDto = new ResponseDto();
		Integer courseId = requestDto.getCourseId();
		Integer duration = requestDto.getDurationDays();
		if((courseId==null)||(duration==null)) {
			System.out.println("Checking null");
			responseDto.setCode(400);
			responseDto.setUserMessage("Trying to update course with null values");
			return responseDto;
		}
		Optional<CourseEntity> optional = courseRepository.findById(courseId);
		if (optional.isEmpty()) {
			System.out.println("Checking whether course is existing or not ");
			responseDto.setCode(400);
			responseDto.setUserMessage("course is not exist");
			return responseDto;
		}
		System.out.println("Updating started");
		CourseEntity courseEntity = optional.get();
		courseEntity.setDurationDays(duration);
		courseRepository.save(courseEntity);
		responseDto.setCode(200);
		responseDto.setUserMessage("course Updated Successfully");
		return responseDto;
	}
}
