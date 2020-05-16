package edu.poly.assignment.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class StaffDto implements Serializable {

	private Long id;

	@NotNull
	@NotEmpty(message = " name is empty")
	@Length(min = 5, max = 50, message = "Name is out of range")
	private String name;

	@NotNull
	private MultipartFile image;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthday;

	@NotNull
	private Integer departId;

	@NotNull
	private String gioiTinh;

	@NotNull
	private Double salary;

	@NotNull
	private String phone;

	@NotNull
	private String email;

	@NotNull
	private String note;

	@NotNull
	private int capdo;

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getCapdo() {
		return capdo;
	}

	public void setCapdo(int capdo) {
		this.capdo = capdo;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

}
