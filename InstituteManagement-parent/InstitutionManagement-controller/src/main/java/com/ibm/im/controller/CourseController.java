package com.ibm.im.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.im.Service.CourseService;
import com.ibm.im.dto.CourseCreateRequestDto;
import com.ibm.im.dto.RemoveCourseMappingsRequestDto;
import com.ibm.im.dto.ResponseDto;
import com.ibm.im.dto.UpdateCourseRequestDto;

@RestController
public class CourseController {

	private Logger logger;

	@Autowired
	private CourseService courseService;

	@PostMapping(path = "/api/course/create")
	public @ResponseBody ResponseDto createCourse(@RequestBody CourseCreateRequestDto requestDto) {
		logger.info("from createCourse()-Controller");
		ResponseDto responseDto = courseService.createCourse(requestDto);

		return responseDto;
	}

	@PostMapping(path = "/api/course/update")
	public @ResponseBody ResponseDto updateCourse(@RequestBody UpdateCourseRequestDto requestDto) {
		logger.info("from updateCourse()-Controller");
		ResponseDto responseDto = courseService.updateCourse(requestDto);

		return responseDto;
	}

	@PostMapping(path = "/api/course/remove-mappings")
	public @ResponseBody ResponseDto removeCourseMappings(@RequestBody RemoveCourseMappingsRequestDto requestDto) {
		logger.info("from CourseController");
		ResponseDto responseDto = courseService.removeCourseMappings(requestDto);

		return responseDto;
	}

}
