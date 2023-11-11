package com.rahul.service;

import com.rahul.dao.IStudentDao;
import com.rahul.daofactory.StudentDaoFactory;
import com.rahul.model.Student;

public class StudentServiceImpl implements IStudentService {

	private IStudentDao dao = StudentDaoFactory.getStudent();

	@Override
	public String insert(String sname, Integer sage, String saddress) {

		return dao.insert(sname, sage, saddress);
	}

	@Override
	public Student read(Integer sid) {

		return dao.read(sid);

	}

	@Override
	public String update(Student student) {

		return dao.update(student);

	}

	@Override
	public String delete(Integer sid) {

		return dao.delete(sid);

	}

}
