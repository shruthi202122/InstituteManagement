package com.ibm.im.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.im.dto.CreateStudentAddressRequestDto;
import com.ibm.im.dto.CreateStudentRequestDto;
import com.ibm.im.dto.MappingStudentToCourseRequestDto;
import com.ibm.im.dto.ResponseDto;
import com.ibm.im.entity.AddressEntity;
import com.ibm.im.entity.CourseEntity;
import com.ibm.im.entity.MappingEntity;
import com.ibm.im.entity.StudentEntity;
import com.ibm.im.repository.AddressRepository;
import com.ibm.im.repository.CourseRepository;
import com.ibm.im.repository.MappingRepository;
import com.ibm.im.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private MappingRepository mappingRepository;
	@Autowired
	private AddressRepository addressRepository;

	public ResponseDto createStudent(CreateStudentRequestDto requestDto) {
		ResponseDto responseDto = new ResponseDto();
		if (requestDto.getName() == null || requestDto.getName().trim().equals("")) {
			responseDto.setCode(400);
			responseDto.setUserMessage("Trying to insert null values");
			return responseDto;
		}

		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setName(requestDto.getName());
		// studentDao.save(studentEntity);

		List<CreateStudentAddressRequestDto> addressDtoList = requestDto.getAddressDtoList();
		List<AddressEntity> addressEntitiesList = new ArrayList<>();

		for (CreateStudentAddressRequestDto createStudentAddressRequestDto : addressDtoList) {
			AddressEntity addressEntity = new AddressEntity();
			addressEntity.setStreet(createStudentAddressRequestDto.getStreet());
			addressEntity.setCity(createStudentAddressRequestDto.getCity());
			addressEntity.setState(createStudentAddressRequestDto.getState());
			addressEntity.setType(createStudentAddressRequestDto.getType());
			addressEntity.setStudentEntity(studentEntity);
			System.out.println("adding Address to addressEntityList");
			addressEntitiesList.add(addressEntity);
		}

		studentEntity.setAddressEntities(addressEntitiesList);
		System.out.println("inserting student");
		studentRepository.save(studentEntity);
	

		responseDto.setCode(200);
		responseDto.setUserMessage("Student Inserted Successfully");

		return responseDto;

	}

	public ResponseDto mapCourses(MappingStudentToCourseRequestDto requestDto) {
		System.out.println("from service");
		ResponseDto responseDto = new ResponseDto();
		if (requestDto.getStudentId() == null || requestDto.getCourseIdList() == null
				|| requestDto.getCourseIdList().isEmpty()) {
			responseDto.setCode(400);
			responseDto.setUserMessage("Trying to insert null values");
			return responseDto;
		}

		List<MappingEntity> meList = mappingRepository
				.findAllByStudentEntityIdAndCourseEntityIdIn(requestDto.getStudentId(), requestDto.getCourseIdList());
		if (!meList.isEmpty()) {
			responseDto.setCode(400);
			responseDto.setUserMessage("Trying to insert duplicate data");
			return responseDto;
		}

		Optional<StudentEntity> optional = studentRepository.findById(requestDto.getStudentId());
		if (optional.isEmpty()) {
			responseDto.setCode(400);
			responseDto.setUserMessage("Student is not exist with this id");
			return responseDto;
		}

		StudentEntity studentEntity = optional.get();

		List<CourseEntity> courseIdList = courseRepository.findAllById(requestDto.getCourseIdList());
		List<MappingEntity> mappingEntityList = new ArrayList<>();
		System.out.println("mapping started");
		// int i=60;
		for (CourseEntity courseEntity : courseIdList) {

			MappingEntity mappingEntity = new MappingEntity();

			mappingEntity.setCourseEntity(courseEntity);
			mappingEntity.setStudentEntity(studentEntity);
			mappingEntityList.add(mappingEntity);
			// i++;
		}

		mappingRepository.saveAll(mappingEntityList);

		responseDto.setCode(200);
		responseDto.setUserMessage("Student mapped with Courses");

		return responseDto;

	}

}
