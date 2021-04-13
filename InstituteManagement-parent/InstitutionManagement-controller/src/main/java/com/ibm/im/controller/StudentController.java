package com.ibm.im.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.im.Service.StudentService;
import com.ibm.im.dto.CreateStudentRequestDto;
import com.ibm.im.dto.MappingStudentToCourseRequestDto;
import com.ibm.im.dto.ResponseDto;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping(path = "/api/student/create")
	public ResponseDto createStudent(@RequestBody CreateStudentRequestDto requestDto) {
		System.out.println("from Controller");
		ResponseDto responseDto;
		try {
			responseDto = studentService.createStudent(requestDto);
		} catch (Exception e) {
			e.printStackTrace();
			responseDto = new ResponseDto();
			responseDto.setCode(500);
			responseDto.setUserMessage("Something went wrong");
		}

		return responseDto;
	}

	@PostMapping(path = "/api/student/add_courses")
	public ResponseDto mapCourses(@RequestBody MappingStudentToCourseRequestDto requestDto) {
		System.out.println("from Controller");
		ResponseDto responseDto;
		try {
			responseDto = studentService.mapCourses(requestDto);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responseDto = new ResponseDto();
			responseDto.setCode(500);
			responseDto.setUserMessage("Something went wrong");
		}

		return responseDto;
	}
}
