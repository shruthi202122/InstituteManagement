package com.ibm.im.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.im.dto.CreateStudentAddressRequestDto;
import com.ibm.im.dto.CreateStudentRequestDto;
import com.ibm.im.dto.MappingStudentToCourseRequestDto;
import com.ibm.im.dto.RemoveAddressRequestDto;
import com.ibm.im.dto.RemoveStudentFromCourseRequestDto;
import com.ibm.im.dto.RemoveStudentRequestDto;
import com.ibm.im.dto.ResponseDto;
import com.ibm.im.entity.AddressEntity;
import com.ibm.im.entity.CourseEntity;
import com.ibm.im.entity.StudentCourseMappingEntity;
import com.ibm.im.entity.StudentEntity;
import com.ibm.im.repository.AddressRepository;
import com.ibm.im.repository.CourseRepository;
import com.ibm.im.repository.StudentCourseMappingRepository;
import com.ibm.im.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentCourseMappingRepository studentCourseMappingRepository;
	@Autowired
	private AddressRepository addressRepository;

	public ResponseDto createStudent(CreateStudentRequestDto requestDto) {
		ResponseDto responseDto = new ResponseDto();
		if (requestDto.getAadharNo()==null||requestDto.getName() == null || requestDto.getName().trim().equals("")) {
			throw new BadRequestException("Trying to insert Null values");
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
			System.out.println("adding Address to addressEntityList");
			addressEntitiesList.add(addressEntity);
		}

		studentEntity.setAddressEntities(addressEntitiesList);
		System.out.println("inserting student");
		studentRepository.save(studentEntity);

		responseDto.setUserMessage("Student Inserted Successfully");

		return responseDto;

	}

	public ResponseDto mapCourses(MappingStudentToCourseRequestDto requestDto) {
		System.out.println("from mapCourse()-service");
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
		System.out.println("mapping started");
		// int i=60;
		for (CourseEntity courseEntity : courseIdList) {

			StudentCourseMappingEntity mappingEntity = new StudentCourseMappingEntity();

			mappingEntity.setCourseEntity(courseEntity);
			mappingEntity.setStudentEntity(studentEntity);
			mappingEntityList.add(mappingEntity);
			// i++;
		}

		studentCourseMappingRepository.saveAll(mappingEntityList);

		responseDto.setUserMessage("Student mapped with Courses");

		return responseDto;

	}

	public ResponseDto removeStudentFromCourse(RemoveStudentFromCourseRequestDto requestDto) {
		System.out.println("From removeStudentFromCourse()-Service");
		ResponseDto responseDto = new ResponseDto();
		Optional<StudentCourseMappingEntity> mappingEntity = studentCourseMappingRepository
				.findByStudentEntityIdAndCourseEntityId(requestDto.getStudentId(), requestDto.getCourseId());
		if (mappingEntity.isEmpty()) {
			throw new NotFoundException("No such data is present with specified data");
		}
		StudentCourseMappingEntity studentCourseMappingEntity = mappingEntity.get();

		System.out.println("ready to delete data from db");
		studentCourseMappingRepository.delete(studentCourseMappingEntity);
		responseDto.setUserMessage("StudentCourse mapping is deleted with specified data");

		return responseDto;
	}

	public ResponseDto removeStudent(RemoveStudentRequestDto requestDto) {
		ResponseDto responseDto = new ResponseDto();
		Optional<StudentEntity> id = studentRepository.findById(requestDto.getStudentId());
		if (id.isEmpty()) {
			throw new NotFoundException("Student is not found with specified id");
		}
		StudentEntity studentEntity = id.get();
		studentRepository.delete(studentEntity);

		responseDto.setUserMessage("StudentCourse mapping is deleted with specified data");

		return responseDto;

	}

	public ResponseDto removeAddress(RemoveAddressRequestDto requestDto) {
		ResponseDto responseDto = new ResponseDto();
		if (requestDto.getStudentId() == null || requestDto.getAddressId() == null) {
			throw new BadRequestException("Student_Id or Address_Id must not be null");
		}

		/*
		 * Optional<AddressEntity> addressOptional =
		 * addressRepository.findById(requestDto.getAddressId());
		 * if(addressOptional.isEmpty()) { responseDto.setCode(400);
		 * responseDto.setUserMessage("Address not Found with specified id"); return
		 * responseDto; } 
		 * AddressEntity addressEntity = addressOptional.get();
		 * StudentEntity studentEntity = addressEntity.getStudentEntity(); 
		 * Integer studentId = studentEntity.getId();
		 * if(studentId.equals(requestDto.getStudentId())) {
		 * addressRepository.delete(addressEntity); responseDto.setCode(200);
		 * responseDto.setUserMessage("Address deleted successfully"); }
		 */
		Optional<StudentEntity> studentOptional = studentRepository.findById(requestDto.getStudentId());
		if(studentOptional.isEmpty()) {
			throw new NotFoundException("Student is not found with specified id");
		}
		StudentEntity studentEntity = studentOptional.get();
		
		List<AddressEntity> addressEntities = studentEntity.getAddressEntities();
		/*AddressEntity deleteAddressEntity = null;
		for (AddressEntity addressEntity : addressEntities) {
			if (requestDto.getAddressId().equals(addressEntity.getId())) {
				deleteAddressEntity = addressEntity;
				break;
			}
			
		}*/
		Optional<AddressEntity> optional = addressEntities.stream().filter(address -> requestDto.getAddressId().equals(address.getId())).findFirst();
		
		if(optional.isPresent()) {
			System.out.println("address is present");
			addressEntities.remove(optional.get());
			studentRepository.save(studentEntity);
			//addressRepository.delete(optional.get());
			responseDto.setUserMessage("Address is deleted successfully");
			return responseDto;
		}
		
		throw new NotFoundException("Address not found");

	}
}
