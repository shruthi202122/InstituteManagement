package com.ibm.im.dto;

import java.util.List;

public class CreateStudentRequestDto {
	private String name;
	private Integer aadharNo;
	private List<CreateStudentAddressRequestDto> addressDtoList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Integer getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(Integer aadharNo) {
		this.aadharNo = aadharNo;
	}

	public List<CreateStudentAddressRequestDto> getAddressDtoList() {
		return addressDtoList;
	}

	public void setAddressDtoList(List<CreateStudentAddressRequestDto> addressDtoList) {
		this.addressDtoList = addressDtoList;
	}

}
