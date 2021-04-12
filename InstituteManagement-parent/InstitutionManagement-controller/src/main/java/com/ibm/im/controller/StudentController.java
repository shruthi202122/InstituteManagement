package com.ibm.im.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.im.Service.StudentService;
import com.ibm.im.dto.CreateStudentRequestDto;
import com.ibm.im.dto.ResponseDto;
import com.ibm.im.entity.StudentEntity;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;
	private ResponseDto responseDto;
	@PostMapping(path = "/api/student/create")
	public ResponseDto createStudent(@RequestBody CreateStudentRequestDto requestDto) {
		
		try { 
			responseDto= studentService.createStudent(requestDto);
		}
		catch (Exception e) {
			e.printStackTrace();
			responseDto.setCode(500);
			responseDto.setUserMessage("Something went wrong");
			return responseDto;
		}
		
		return responseDto;
	}
	
	@PostMapping(path = "/api/student/add-courses")
	public ResponseDto addCourses() {
		
		return null;
	}
}
