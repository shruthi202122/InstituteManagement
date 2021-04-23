package com.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
	private Integer id;
	private String name;
	private String username;
	private String email;
	private String phone;
	private String website;
	private AddressDto address;
	private CompanyDto company;
}
