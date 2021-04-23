package com.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {
	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private GeoDto geo;
	
}
