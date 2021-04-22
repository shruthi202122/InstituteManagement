package com.ibm.im.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.im.dto.AddressDto;
import com.ibm.im.dto.CourseDto;
import com.ibm.im.dto.CreateStudentAddressRequestDto;
import com.ibm.im.dto.CreateStudentRequestDto;
import com.ibm.im.dto.GetStudentResponseDto;
import com.ibm.im.dto.MappingStudentToCourseRequestDto;
import com.ibm.im.dto.RemoveAddressRequestDto;
import com.ibm.im.dto.RemoveStudentFromCourseRequestDto;
import com.ibm.im.dto.RemoveStudentRequestDto;
import com.ibm.im.dto.ResponseDto;
import com.ibm.im.dto.SearchStudentResponseDto;
import com.ibm.im.dto.StudentDto2;
import com.ibm.im.entity.AddressEntity;
import com.ibm.im.entity.CourseEntity;
import com.ibm.im.entity.StudentCourseMappingEntity;
import com.ibm.im.entity.StudentEntity;
import com.ibm.im.repository.CourseRepository;
import com.ibm.im.repository.StudentCourseMappingRepository;
import com.ibm.im.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentCourseMappingRepository studentCourseMappingRepository;

	public ResponseDto createStudent(CreateStudentRequestDto requestDto) {
		log.info("from createStudent()-service ");
		ResponseDto responseDto = new ResponseDto();
		if (requestDto.getAadharNo() == null || requestDto.getName() == null
				|| requestDto.getName().trim().equals("")) {
			throw new BadRequestException("Trying to insert Null values");
		}
		Optional<StudentEntity> optional = studentRepository.findByAadharNo(requestDto.getAadharNo());
		if (optional.isPresent()) {
			throw new BadRequestException("AadharNo already exist");
		}

		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setName(requestDto.getName());
		studentEntity.setAadharNo(requestDto.getAadharNo());
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
			log.info("adding Address to addressEntityList");
			addressEntitiesList.add(addressEntity);
		}

		studentEntity.setAddressEntities(addressEntitiesList);

		log.info("inserting student");
		studentRepository.save(studentEntity);

		responseDto.setUserMessage("Student Inserted Successfully");

		return responseDto;

	}

	public ResponseDto mapCourses(MappingStudentToCourseRequestDto requestDto) {
		log.info("from mapCourse()-service");
		ResponseDto responseDto = new ResponseDto();
		if (requestDto.getStudentId() == null || requestDto.getCourseIdList() == null
				|| requestDto.getCourseIdList().isEmpty()) {
			throw new BadRequestException("Trying to insert Null values");

		}

		List<StudentCourseMappingEntity> meList = studentCourseMappingRepository
				.findAllByStudentEntityIdAndCourseEntityIdIn(requestDto.getStudentId(), requestDto.getCourseIdList());
		if (!meList.isEmpty()) {
			throw new BadRequestException("Trying to insert duplicate data values");
		}

		Optional<StudentEntity> optional = studentRepository.findById(requestDto.getStudentId());
		if (optional.isEmpty()) {
			throw new NotFoundException("Student is not exist with this id");
		}

		StudentEntity studentEntity = optional.get();

		List<CourseEntity> courseIdList = courseRepository.findAllById(requestDto.getCourseIdList());
		List<StudentCourseMappingEntity> mappingEntityList = new ArrayList<>();
		log.info("mapping started");
		// int i=60;
		for (CourseEntity courseEntity : courseIdList) {

			StudentCourseMappingEntity mappingEntity = new StudentCourseMappingEntity();

			mappingEntity.setCourseEntity(courseEntity);
			mappingEntity.setStudentEntity(studentEntity);
			mappingEntityList.add(mappingEntity);
			// i++;
		}
		log.info("mapping ended");
		studentCourseMappingRepository.saveAll(mappingEntityList);

		responseDto.setUserMessage("Student mapped with Courses");

		return responseDto;

	}

	public ResponseDto removeStudentFromCourse(RemoveStudentFromCourseRequestDto requestDto) {
		log.info("From removeStudentFromCourse()-Service");
		ResponseDto responseDto = new ResponseDto();
		Optional<StudentCourseMappingEntity> mappingEntity = studentCourseMappingRepository
				.findByStudentEntityIdAndCourseEntityId(requestDto.getStudentId(), requestDto.getCourseId());
		if (mappingEntity.isEmpty()) {
			throw new NotFoundException("No such data is present with specified data");
		}
		StudentCourseMappingEntity studentCourseMappingEntity = mappingEntity.get();

		log.info("ready to delete data from db");
		studentCourseMappingRepository.delete(studentCourseMappingEntity);
		responseDto.setUserMessage("StudentCourse mapping is deleted with specified data");

		return responseDto;
	}

	public ResponseDto removeStudent(RemoveStudentRequestDto requestDto) {
		log.info("from removeStudent()-service ");
		ResponseDto responseDto = new ResponseDto();
		Optional<StudentEntity> id = studentRepository.findById(requestDto.getStudentId());
		if (id.isEmpty()) {
			throw new NotFoundException("Student is not found with specified id");
		}
		StudentEntity studentEntity = id.get();
		log.info("ready to delete data from db");

		studentRepository.delete(studentEntity);

		responseDto.setUserMessage("StudentCourse mapping is deleted with specified data");

		return responseDto;

	}

	public ResponseDto removeAddress(RemoveAddressRequestDto requestDto) {
		log.info("from createAddress()-service ");
		ResponseDto responseDto = new ResponseDto();
		if (requestDto.getStudentId() == null || requestDto.getAddressId() == null) {
			throw new BadRequestException("Student_Id or Address_Id must not be null");
		}

		/*
		 * Optional<AddressEntity> addressOptional =
		 * addressRepository.findById(requestDto.getAddressId());
		 * if(addressOptional.isEmpty()) { responseDto.setCode(400);
		 * responseDto.setUserMessage("Address not Found with specified id"); return
		 * responseDto; } AddressEntity addressEntity = addressOptional.get();
		 * StudentEntity studentEntity = addressEntity.getStudentEntity(); Integer
		 * studentId = studentEntity.getId();
		 * if(studentId.equals(requestDto.getStudentId())) {
		 * addressRepository.delete(addressEntity); responseDto.setCode(200);
		 * responseDto.setUserMessage("Address deleted successfully"); }
		 */
		Optional<StudentEntity> studentOptional = studentRepository.findById(requestDto.getStudentId());
		if (studentOptional.isEmpty()) {
			throw new NotFoundException("Student is not found with specified id");
		}
		StudentEntity studentEntity = studentOptional.get();

		List<AddressEntity> addressEntities = studentEntity.getAddressEntities();
		/*
		 * AddressEntity deleteAddressEntity = null; for (AddressEntity addressEntity :
		 * addressEntities) { if
		 * (requestDto.getAddressId().equals(addressEntity.getId())) {
		 * deleteAddressEntity = addressEntity; break; }
		 * 
		 * }
		 */
		Optional<AddressEntity> optional = addressEntities.stream()
				.filter(address -> requestDto.getAddressId().equals(address.getId())).findFirst();

		if (optional.isPresent()) {
			log.info("address is present");
			addressEntities.remove(optional.get());
			studentRepository.save(studentEntity);
			// addressRepository.delete(optional.get());
			responseDto.setUserMessage("Address is deleted successfully");
			return responseDto;
		}

		throw new NotFoundException("Address not found");

	}

	public GetStudentResponseDto getStudent(Integer studentId) {
		log.info("inside getStudent()-StudentService");
		Optional<StudentEntity> optional = studentRepository.findById(studentId);
		if(optional.isEmpty()) {
			throw new NotFoundException("Student not exist");
		}
		StudentEntity studentEntity = optional.get();
		List<AddressDto> AddressDtoList = studentEntity.getAddressEntities().stream().map(ae -> {
			AddressDto addressDto = new AddressDto();
			addressDto.setStreet(ae.getStreet());
			addressDto.setCity(ae.getCity());
			addressDto.setState(ae.getState());
			addressDto.setType(ae.getType());
			return addressDto;
		}).collect(Collectors.toList());
		List<CourseDto> courseDtoList = studentEntity.getMappingEntities().stream().map(sce -> sce.getCourseEntity())
				.map(ce -> {
					CourseDto courseDto = new CourseDto();
					courseDto.setName(ce.getName());
					courseDto.setDurationDays(ce.getDurationDays());
					return courseDto;
				}).collect(Collectors.toList());
		GetStudentResponseDto responseDto = new GetStudentResponseDto();
		responseDto.setStudentName(studentEntity.getName());
		responseDto.setAadharNo(studentEntity.getAadharNo());
		responseDto.setAddressDtoList(AddressDtoList);
		responseDto.setCourseDtoList(courseDtoList);

		return responseDto;

	}

	public SearchStudentResponseDto searchStudent(String searchText) {
		log.info("inside searchStudent()-service ");
		List<StudentEntity> studentEntities = studentRepository.searchStudentsWithNameOrAadhar(searchText);
		int size = studentEntities.size();
		log.info("studentEntities size - " + size);
		List<StudentDto2> studentDtoList = studentEntities.stream().map(se -> {
			StudentDto2 studentDto2 = new StudentDto2();
			studentDto2.setName(se.getName());
			studentDto2.setAadharNo(se.getAadharNo());
			return studentDto2;
		}).collect(Collectors.toList());

		SearchStudentResponseDto responseDto = new SearchStudentResponseDto();
		responseDto.setStudentData(studentDtoList);

		return responseDto;

	}
}
