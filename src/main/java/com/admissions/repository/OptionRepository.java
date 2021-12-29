package com.admissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admissions.entity.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long>{

}
