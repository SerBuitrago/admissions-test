package com.admissions.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admissions.entity.Category;
import com.admissions.entity.validate.CategoryValidate;
import com.admissions.repository.CategoryRepository;
import com.admissions.service.CategoryService;
import com.admissions.util.Admissions;
import com.admissions.util.exception.AdmissionsException;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category findById(Long id) {
		if (!Admissions.isLong(id))
			throw new AdmissionsException("El id de la categoria no es valido.");
		Optional<Category> optional = categoryRepository.findById(id);
		if (!optional.isPresent())
			throw new AdmissionsException("No se ha encontrado ninguna categoria con el id " + id + ".");
		return optional.get();
	}

	@Override
	public Category findByName(String name) {
		if (!Admissions.isString(name))
			throw new AdmissionsException("El nombre de la categoria no es valido.");
		Category category = categoryRepository.findByName(name);
		if (category == null)
			throw new AdmissionsException("No se ha encontrado ninguna categoria con el nombre " + name + ".");
		return category;
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category save(Category category, int type) {
		CategoryValidate.validate(category);
		String message = Admissions.message(type, false);
		category = !Admissions.isLong(category.getId()) ? saveToSave(category, type) : saveToUpdate(category, type);
		category = categoryRepository.save(category);
		if (category == null)
			throw new AdmissionsException("No se ha podido " + message + " la categoria.");
		return category;
	}

	@Override
	public boolean delete(Long id) {
		findById(id);
		categoryRepository.deleteById(id);
		try {
			findById(id);
		} catch (AdmissionsException e) {
			LOGGER.error("delete(Long id)", e);
			return true;
		}
		throw new AdmissionsException("No se ha eliminado la categoria con el id " + id + ".");
	}

	private Category saveToSave(Category category, int type) {
		if (!testName(category.getName()))
			throw new AdmissionsException("Ya existe una categoria con el nombre " + category.getName() + ".");
		return category;
	}

	private Category saveToUpdate(Category category, int type) {
		Category aux = null;
		if (type != 3)
			aux = findById(category.getId());
		if (!validateName(category.getName(), (type == 2 ? aux.getName() : category.getName())))
			throw new AdmissionsException("Ya existe una categoria con el nombre " + category.getName() + ".");
		return category;
	}

	private boolean testName(String name) {
		Category category = null;
		try {
			category = findByName(name);
		} catch (AdmissionsException e) {
			LOGGER.error("testName(String name)", e);
		}
		return category == null ? true : false;
	}

	private boolean validateName(String nameA, String nameB) {
		return (!nameA.equalsIgnoreCase(nameB)) ? testName(nameA) : true;
	}
}
