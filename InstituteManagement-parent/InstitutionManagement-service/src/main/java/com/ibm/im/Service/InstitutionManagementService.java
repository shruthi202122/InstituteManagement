package com.ibm.im.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.im.dao.InstitutionManagementDao;
import com.ibm.im.dto.AddressDto;
import com.ibm.im.dto.CourseDto;
import com.ibm.im.dto.StudentDto;
import com.ibm.im.entity.AddressEntity;
import com.ibm.im.entity.CourseEntity;
import com.ibm.im.entity.MappingEntity;
import com.ibm.im.entity.StudentEntity;

@Service
public class InstitutionManagementService {
	@Autowired
	private InstitutionManagementDao institutionManagementDao;
	
	public List<StudentDto> getStudentData() {
		List<StudentEntity> studentdata = institutionManagementDao.getStudentData();
		List<StudentDto> studentDtoList=new ArrayList();
		for (StudentEntity studentEntity : studentdata) {
			StudentDto studentDto = new StudentDto();
			studentDto.setId(studentEntity.getId());
			studentDto.setName(studentEntity.getName());
			List<MappingEntity> mappingEntitiesList = studentEntity.getMappingEntities();
			List<CourseDto> courseDtoList=new ArrayList();
			for (MappingEntity mappingEntity : mappingEntitiesList) {
				CourseDto courseDto = new CourseDto();
				CourseEntity courseEntity = mappingEntity.getCourseEntity();
				courseDto.setId(courseEntity.getId());
				courseDto.setName(courseEntity.getName());
				courseDtoList.add(courseDto);
				
			}
			studentDto.setCourseList(courseDtoList);
			
			List<AddressEntity> addressEntitiesList = studentEntity.getAddressEntities();
			List<AddressDto> addressDtoList=new ArrayList();
			for (AddressEntity addressEntity : addressEntitiesList) {
				AddressDto addressDto = new AddressDto();
				addressDto.setId(addressEntity.getId());
				addressDto.setStreet(addressEntity.getStreet());
				addressDto.setCity(addressEntity.getCity());
				addressDto.setState(addressEntity.getState());
				addressDto.setType(addressEntity.getType());
				addressDtoList.add(addressDto);
				studentDto.setAddressList(addressDtoList);
	
			}
			studentDtoList.add(studentDto);
		}
		return studentDtoList;
	}
}
