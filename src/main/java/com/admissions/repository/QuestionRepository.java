package com.admissions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
  
import com.admissions.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
	
	List<Question> findByIdTest(Long idTest);
	
	@Query(nativeQuery = false, value = "SELECT q FROM Question q WHERE q.category.id = :idCategory")
	List<Question> findByCategoryAll(@Param("idCategory") Long idCategory);
}
