package com.ibm.im.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.im.Service.CourseService;
import com.ibm.im.dto.CourseCreateRequestDto;
import com.ibm.im.dto.ResponseDto;
import com.ibm.im.dto.UpdateCourseRequestDto;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PostMapping(path = "/api/course/create")
	public @ResponseBody ResponseDto createCourse(@RequestBody CourseCreateRequestDto requestDto) {
		System.out.println("from CourseController");
		ResponseDto responseDto;
		try {

			 responseDto = courseService.createCourse(requestDto);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			responseDto = new ResponseDto();
			responseDto.setCode(500);
			responseDto.setUserMessage("sorry something went wrong");
		}

		return responseDto;
	}
	
	@PostMapping(path = "/api/course/update")
	public @ResponseBody ResponseDto updateCourse(@RequestBody UpdateCourseRequestDto requestDto) {
		System.out.println("from CourseController");
		ResponseDto responseDto;
		try {
			responseDto = courseService.updateCourse(requestDto);
		}
		catch (Exception e) {
			e.printStackTrace();
			responseDto = new ResponseDto();
			responseDto.setCode(500);
			responseDto.setUserMessage("sorry something went wrong");
		}
		return responseDto;
	}
	
}
