package com.admissions.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	
	@Id
	@Column(name = "id")
	private Long document;
	
	@Column(name = "name", nullable = false, length = 200)
	private String name;
	
	@Column(name = "subname", nullable = true, length = 200)
	private String subname;
	
	@Column(name = "phone", nullable = true, length = 200)
	private String phone;
	
	@Column(name = "address", nullable = true, length = 200)
	private String address;
}
