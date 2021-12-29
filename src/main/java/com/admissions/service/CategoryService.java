package com.admissions.service;

import java.util.List;

import com.admissions.entity.Category;

public interface CategoryService {

	Category findById(Long id);
	
	Category findByName(String name);
	
	List<Category> findAll();
	
	Category save(Category category, int type);
	
	boolean delete(Long id);
}
