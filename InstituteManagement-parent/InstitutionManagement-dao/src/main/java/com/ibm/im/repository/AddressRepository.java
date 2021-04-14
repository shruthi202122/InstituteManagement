package com.ibm.im.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.im.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

}
