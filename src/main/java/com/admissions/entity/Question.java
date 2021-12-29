package com.admissions.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="id_test", nullable =  false)
	private Long idTest;
	
	@Column(name="name", nullable =  false, length = 200)
	private String name;
	
	@Column(name="description", nullable =  false, length = 800)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;
	
	@OneToMany(mappedBy = "question")
	private List<Option> options;
}
