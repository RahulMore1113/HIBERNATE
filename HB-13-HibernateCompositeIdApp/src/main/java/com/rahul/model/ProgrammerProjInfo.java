package com.rahul.model;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class ProgrammerProjInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProgrammerProjId id;

	private String pname;
	private Integer deptNo;
	private String deptName;

	public ProgrammerProjId getId() {
		return id;
	}

	public void setId(ProgrammerProjId id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "ProgrammerProjInfo [id=" + id + ", pname=" + pname + ", deptNo=" + deptNo + ", deptName=" + deptName
				+ "]";
	}

}
