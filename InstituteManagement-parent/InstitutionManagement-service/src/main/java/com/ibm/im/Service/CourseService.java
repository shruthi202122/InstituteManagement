package com.ibm.im.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.im.dao.CourseDao;
import com.ibm.im.dto.CourseCreateRequestDto;
import com.ibm.im.dto.ResponseDto;
import com.ibm.im.entity.CourseEntity;
import com.ibm.im.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseRepository courseRepository;

	public ResponseDto createCourse(CourseCreateRequestDto requestDto) {
		System.out.println("from CourseService");
		ResponseDto responseDto = new ResponseDto();
		if (requestDto.getId() == null || requestDto.getName() == null || requestDto.getName().trim().equals("")) {
			responseDto.setCode(400);
			responseDto.setUserMessage("Invaild data recieved");
			return responseDto;
		}
		if(requestDto.getId()<=0) {
			responseDto.setCode(400);
			responseDto.setUserMessage("Id must be greater than zero");
			return responseDto;
		}

		//CourseEntity entity = courseDao.findById(requestDto.getId());
		Optional<CourseEntity> optional = courseRepository.findById(requestDto.getId());

		if (optional.isPresent()) {
			//courseEntity=optional.get();
			responseDto.setCode(400);
			responseDto.setUserMessage("course already exist");
			return responseDto;
		}

		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setId(requestDto.getId());
		courseEntity.setName(requestDto.getName());
		//courseDao.save(courseEntity);
		courseEntity = courseRepository.save(courseEntity);
		responseDto.setCode(200);
		responseDto.setUserMessage("course created successfully");
		return responseDto;
	}
}
