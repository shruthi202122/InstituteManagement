package com.ibm.im.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.im.dao.StudentDao;
import com.ibm.im.dto.CreateStudentRequestDto;
import com.ibm.im.dto.ResponseDto;
import com.ibm.im.entity.StudentEntity;
import com.ibm.im.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private StudentRepository studentRepository;
	
	public ResponseDto createStudent(CreateStudentRequestDto requestDto) {
		ResponseDto responseDto = new ResponseDto();
		if(requestDto.getId()==null||requestDto.getName()==null||requestDto.getName().trim().equals("")) {
			responseDto.setCode(400);
			responseDto.setUserMessage("Invalid data received");
			return responseDto;
			
		}
		if(requestDto.getId()<=0) {
			responseDto.setCode(400);
			responseDto.setUserMessage("Invalid data received");
			return responseDto;
		}
//		if(studentDao.findById(requestDto.getId())!=null) {
		if(studentRepository.findById(requestDto.getId()).isPresent()) {
			responseDto.setCode(400);
			responseDto.setUserMessage("Student is already exist with this id");
			return responseDto;
		}
		
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setId(requestDto.getId());
		studentEntity.setName(requestDto.getName());
		//studentDao.save(studentEntity);
		studentEntity = studentRepository.save(studentEntity);
		responseDto.setCode(200);
		responseDto.setUserMessage("Student Inserted Successfully");
		return responseDto;
		
	}
}
