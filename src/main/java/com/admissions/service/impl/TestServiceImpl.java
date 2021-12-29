package com.admissions.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admissions.entity.Test;
import com.admissions.entity.validate.TestValidate;
import com.admissions.repository.TestRepository;
import com.admissions.service.TestService;
import com.admissions.util.Admissions;
import com.admissions.util.exception.AdmissionsException;

@Service
public class TestServiceImpl implements TestService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Autowired
	TestRepository testRepository;

	@Override
	public Test findById(Long id) {
		if (!Admissions.isLong(id))
			throw new AdmissionsException("El id de la prueba no es valido.");
		Optional<Test> optional = testRepository.findById(id);
		if (!optional.isPresent())
			throw new AdmissionsException("No se ha encontrado ninguna prueba con el id " + id + ".");
		return optional.get();
	}

	@Override
	public Test findByName(String name) {
		if (!Admissions.isString(name))
			throw new AdmissionsException("El nombre de la categoria no es valido.");
		Test test = testRepository.findByName(name);
		if (test == null)
			throw new AdmissionsException("No se ha encontrado ninguna prueba con el nombre " + name + ".");
		return test;
	}

	@Override
	public List<Test> findAll() {
		return testRepository.findAll();
	}

	@Override
	public Test save(Test test, int type) {
		TestValidate.validate(test);
		String message = Admissions.message(type, false);
		test = !Admissions.isLong(test.getId()) ? saveToSave(test, type) : saveToUpdate(test, type);
		test = testRepository.save(test);
		if (test == null)
			throw new AdmissionsException("No se ha podido " + message + " la prueba.");
		return test;
	}

	@Override
	public boolean delete(Long id) {
		findById(id);
		testRepository.deleteById(id);
		try {
			findById(id);
		} catch (AdmissionsException e) {
			LOGGER.error("delete(Long id)", e);
			return true;
		}
		throw new AdmissionsException("No se ha eliminado la prueba con el id " + id + ".");
	}
	
	private Test saveToSave(Test test, int type) {
		if (!testName(test.getName()))
			throw new AdmissionsException("Ya existe una prueba con el nombre " + test.getName() + ".");
		return test;
	}
	
	private Test saveToUpdate(Test test, int type) {
		Test aux = null;
		if (type != 3)
			aux = findById(test.getId());
		if (!validateName(test.getName(), (type == 2 ? aux.getName() : test.getName())))
			throw new AdmissionsException("Ya existe una prueba con el nombre " + test.getName() + ".");
		return test;
	}
	
	private boolean testName(String name) {
		Test test = null;
		try {
			test = findByName(name);
		} catch (AdmissionsException e) {
			LOGGER.error("testName(String name)", e);
		}
		return test == null ? true : false;
	}

	private boolean validateName(String nameA, String nameB) {
		return (!nameA.equalsIgnoreCase(nameB)) ? testName(nameA) : true;
	}
}
