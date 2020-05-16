package edu.poly.assignment.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "staffs")
public class Staff {

	// Records
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
	private Set<Records> records;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	@NotNull
	private String name;

	@Column(length = 100)
	private String photo;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthday;

	@Column(length = 20)
	private String gioiTinh;

	@Column(length = 50)
	private Double salary;

	@Column(length = 50)
	private String email;

	@Column(length = 12)
	private String phone;

	@Column(length = 10)
	private int capdo;

	@Column(length = 200)
	private String note;

	@ManyToOne
	@JoinColumn(name = "departId")
	private Depart departId;
	
	
	public Staff() {
		super();
	}

	public Staff(Set<Records> records, Long id, @NotNull String name, String photo, Date birthday, String gioiTinh,
			Double salary, String email, String phone, int capdo, String note, Depart departId) {
		super();
		this.records = records;
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.birthday = birthday;
		this.gioiTinh = gioiTinh;
		this.salary = salary;
		this.email = email;
		this.phone = phone;
		this.capdo = capdo;
		this.note = note;
		this.departId = departId;
	}


	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCapdo() {
		return capdo;
	}

	public void setCapdo(int capdo) {
		this.capdo = capdo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Depart getDepart() {
		return departId;
	}

	public void setDepart(Depart depart) {
		this.departId = depart;
	}

	public Set<Records> getRecords() {
		return records;
	}

	public void setRecords(Set<Records> records) {
		this.records = records;
	}



}
