package com.rahul.dao;

import com.rahul.model.Student;

public interface IStudentDao {

	public String insert(String sname, Integer sage, String saddress);

	public Student read(Integer sid);

	public String update(Student student);

	public String delete(Integer sid);

}
