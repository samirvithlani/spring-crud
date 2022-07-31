package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bean.StudentBean;

@Service
public interface StudentService {

	public int addStudent(StudentBean studentBean);

	public int deleteStudent(int sId);

	public List<StudentBean> studentList();

	public int updateStudent(int sId, StudentBean studentBean);

	public StudentBean getStudntById(int sId);

	public StudentBean login(String email, String password);
}
