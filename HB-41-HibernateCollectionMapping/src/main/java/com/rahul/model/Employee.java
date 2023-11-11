package com.rahul.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.ListIndexBase;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLOYEEE_COLLECTION")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer eid;
	private String ename;
	private String eaddress;

	@ElementCollection
	@Column(name = "friend_name")
	@CollectionTable(name = "EMP_FRNDS", joinColumns = @JoinColumn(referencedColumnName = "eid", name = "emp_id"))
	@OrderColumn(name = "friend_no")
	@ListIndexBase(value = 1)
	private List<String> friendList;

	@ElementCollection
	@Column(name = "mobile_no")
	@CollectionTable(name = "EMP+PHONE", joinColumns = @JoinColumn(referencedColumnName = "eid", name = "emp_id"))
	private Set<Long> phones;

	@ElementCollection
	@Column(name = "account_no")
	@CollectionTable(name = "EMP_ACCOUNTS", joinColumns = @JoinColumn(referencedColumnName = "eid", name = "emp_id"))
	@MapKeyColumn(name = "bankName", length = 10)
	private Map<String, Long> bankAccount;

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEaddress() {
		return eaddress;
	}

	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
	}

	public List<String> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<String> friendList) {
		this.friendList = friendList;
	}

	public Set<Long> getPhones() {
		return phones;
	}

	public void setPhones(Set<Long> phones) {
		this.phones = phones;
	}

	public Map<String, Long> getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(Map<String, Long> bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", eaddress=" + eaddress + ", friendList=" + friendList
				+ ", phones=" + phones + ", bankAccount=" + bankAccount + "]";
	}

}
