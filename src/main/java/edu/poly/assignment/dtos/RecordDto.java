package edu.poly.assignment.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.sun.istack.NotNull;

public class RecordDto implements Serializable {

	private Integer id;

	@NotNull
	private int type;

	@NotNull
	private String reason;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date;

	@NotNull
	private Long staff;

	public RecordDto() {
		super();
	}

	public RecordDto(Integer id, int type, String reason, Date date, Long staff) {
		super();
		this.id = id;
		this.type = type;
		this.reason = reason;
		this.date = date;
		this.staff = staff;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getStaff() {
		return staff;
	}

	public void setStaff(Long staff) {
		this.staff = staff;
	}

}
