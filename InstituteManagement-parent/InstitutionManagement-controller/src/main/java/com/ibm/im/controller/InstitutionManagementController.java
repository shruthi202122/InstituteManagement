package com.ibm.im.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.im.Service.InstitutionManagementService;
import com.ibm.im.dto.StudentDto;

@RestController
public class InstitutionManagementController {
	@Autowired
	private InstitutionManagementService service;

	@GetMapping(path = "/api/student/getList")
	public List<StudentDto> getStudentData() {
		List studentData = service.getStudentData();
		return studentData;
	}
}
