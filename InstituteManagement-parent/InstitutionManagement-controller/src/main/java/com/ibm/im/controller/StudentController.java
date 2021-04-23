package com.ibm.im.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ibm.im.Service.StudentService;
import com.ibm.im.dto.CreateStudentRequestDto;
import com.ibm.im.dto.GetStudentResponseDto;
import com.ibm.im.dto.MappingStudentToCourseRequestDto;
import com.ibm.im.dto.RemoveAddressRequestDto;
import com.ibm.im.dto.RemoveStudentFromCourseRequestDto;
import com.ibm.im.dto.RemoveStudentRequestDto;
import com.ibm.im.dto.ResponseDto;
import com.ibm.im.dto.SearchStudentResponseDto;
import com.user.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class StudentController {
	@Autowired
	private StudentService studentService;

	// private Logger logger = LoggerFactory.getLogger(StudentController.class);

	@PostMapping(path = "/api/student/create")
	public ResponseDto createStudent(@RequestBody CreateStudentRequestDto requestDto) {
		log.info("from createStudent()-Controller");
		ResponseDto responseDto = studentService.createStudent(requestDto);

		return responseDto;
	}

	@PostMapping(path = "/api/student/add-courses")
	public ResponseDto mapCourses(@RequestBody MappingStudentToCourseRequestDto requestDto) {
		log.info("from mapCourses()-Controller");
		ResponseDto responseDto = studentService.mapCourses(requestDto);

		return responseDto;
	}

	@PostMapping(path = "/api/student/removefrom-course")
	public ResponseDto removeStudentFromCourse(@RequestBody RemoveStudentFromCourseRequestDto requestDto) {
		log.info("From removeStudentFromCourse()-Controller");
		ResponseDto responseDto = studentService.removeStudentFromCourse(requestDto);

		return responseDto;

	}

	@PostMapping(path = "/api/student/remove-student")
	public ResponseDto removeStudent(@RequestBody RemoveStudentRequestDto requestDto) {
		log.info("From removeStudentFromCourse()-Controller");
		ResponseDto responseDto = studentService.removeStudent(requestDto);

		return responseDto;

	}

	@PostMapping(path = "/api/student/remove-address")
	public ResponseDto removeAddress(@RequestBody RemoveAddressRequestDto requestDto) {
		log.info("From removeAddress()-Controller");
		ResponseDto responseDto = studentService.removeAddress(requestDto);

		return responseDto;

	}

	@GetMapping(path = "/api/student/get/{studentId}")
	public GetStudentResponseDto getStudent(@PathVariable(name = "studentId") Integer studentId) {
		log.info("inside getStudent()-Controller");
		GetStudentResponseDto responseDto = studentService.getStudent(studentId);

		return responseDto;

	}

	@GetMapping(path = "/api/student/search/{searchText}")
	public SearchStudentResponseDto searchStudent(@PathVariable(name = "searchText") String searchText) {
		log.info("inside searchStudent()-controller");
		log.info("searchText: "+searchText);
		SearchStudentResponseDto responseDto = studentService.searchStudent(searchText);
		return responseDto;
	}
//	@GetMapping(path="/api/getUser")
//	public String getUser() {
//		log.info("inside getUser()--calling external api");
//		String uri="https://jsonplaceholder.typicode.com/users/1";
//		RestTemplate restTemplate = new RestTemplate();
//		String string = restTemplate.getForObject(uri, String.class);
//		return string;
//	}
	
	@GetMapping(path = "/api/getUser")
	public UserDto getUser() {
		log.info("inside getUser()--calling external api");
		String uri="https://jsonplaceholder.typicode.com/users/1";
		RestTemplate restTemplate = new RestTemplate();
		UserDto userDto = restTemplate.getForObject(uri, UserDto.class);
		
		return userDto;
		
	}

}
