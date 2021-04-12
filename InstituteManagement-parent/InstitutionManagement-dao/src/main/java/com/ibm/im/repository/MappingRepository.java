package com.ibm.im.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.im.entity.MappingEntity;

@Repository
public interface MappingRepository extends JpaRepository<MappingEntity, Integer>{

}
