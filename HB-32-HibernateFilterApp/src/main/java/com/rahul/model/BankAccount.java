package com.rahul.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@Table (name = "bankaccount")
@FilterDef (name = "FILTER_BANK_ACCOUNT_STATUS",
			parameters = {
				@ParamDef(type = String.class,name = "accType1"),
				@ParamDef (type = String.class, name = "accType2")
			}
		)
@Filter (name = "FILTER_BANK_ACCOUNT_STATUS", condition = "status not in (:accType1, :accType2)")
public class BankAccount implements Serializable 
{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer accno;
	private String holderName;
	private Float balance;
	private String status;

	public Integer getAccno() {
		return accno;
	}

	public void setAccno(Integer accno) {
		this.accno = accno;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BankAccount [accno=" + accno + ", holderName=" + holderName + ", balance=" + balance + ", status="
				+ status + "]";
	}

}
