package edu.poly.assignment.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departs")
public class Depart implements Serializable {
	
	@OneToMany(mappedBy = "departId", cascade = CascadeType.ALL)
	private Set<Staff> staffs;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100)
	private String name;

	public Depart() {
		
	}

	public Depart(Integer id, String name) {
		
		this.id = id;
		this.name = name;
	}


	public Set<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
