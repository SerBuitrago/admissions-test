package com.admissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admissions.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>{
	
	Test findByName(String name);
}
