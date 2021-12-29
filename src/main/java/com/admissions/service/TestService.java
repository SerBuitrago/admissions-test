package com.admissions.service;

import java.util.List;

import com.admissions.entity.Test;

public interface TestService {
	
	Test findById(Long id);
	
	Test findByName(String name);
	
	List<Test> findAll();
	
	Test save(Test test, int type);
	
	boolean delete(Long id);
}
