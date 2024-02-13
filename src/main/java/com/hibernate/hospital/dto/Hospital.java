package com.hibernate.hospital.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hospital {
	@Id
	private String hospitalId;
	private String hospitalName;
	private String hospitalType;
	private String hospitalemail;
	private long hospitalContactNo;

	@OneToMany
	private List<Branch> branches;

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	public String getHospitalemail() {
		return hospitalemail;
	}

	public void setHospitalemail(String hospitalemail) {
		this.hospitalemail = hospitalemail;
	}

	public long getHospitalContactNo() {
		return hospitalContactNo;
	}

	public void setHospitalContactNo(long hospitalContactNo) {
		this.hospitalContactNo = hospitalContactNo;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

}
