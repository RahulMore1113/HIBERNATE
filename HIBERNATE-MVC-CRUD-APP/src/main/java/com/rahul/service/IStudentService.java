package com.rahul.service;

import com.rahul.model.Student;

public interface IStudentService {

	public String insert(String sname, Integer sage, String saddress);

	public Student read(Integer sid);

	public String update(Student student);

	public String delete(Integer sid);

}
