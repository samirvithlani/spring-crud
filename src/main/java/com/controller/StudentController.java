package com.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.StudentBean;
import com.service.StudentService;
import com.util.ResponseMessage;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping(value = "/addstudent")
	public ResponseEntity<ResponseMessage> addStudentData(@RequestBody StudentBean studentBean) {

		System.out.println(studentBean.getsName());
		int res = studentService.addStudent(studentBean);
		ResponseMessage responseMessage = new ResponseMessage();
		if (res > 0) {
			responseMessage.setStatus(200);
			responseMessage.setMessage("Student Added Successfully");
			responseMessage.setHittime(new Timestamp(new Date().getTime()));

			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		}

		responseMessage.setMessage("Error while adding Stundet");
		responseMessage.setStatus(500);
		return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.EXPECTATION_FAILED);

	}

	@GetMapping(value = "/getstudents")
	public ResponseEntity<List<StudentBean>> getAllStudents() {

		List<StudentBean> studentList = studentService.studentList();
		if (studentList.size() > 0) {

			return new ResponseEntity<List<StudentBean>>(studentList, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/getstudent/{sid}")
	public ResponseEntity<StudentBean> getStudentById(@PathVariable("sid") int sId) {

		StudentBean studentBean = null;
		try {
			studentBean = studentService.getStudntById(sId);
			if (studentBean != null) {

				return new ResponseEntity<StudentBean>(studentBean, HttpStatus.OK);
			}
		} catch (DataAccessException e) {

			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		return null;

	}

	@DeleteMapping(value = "/deletestudent/{sid}")
	public ResponseEntity<ResponseMessage> deleteStudent(@PathVariable("sid") int sId) {

		int res = studentService.deleteStudent(sId);
		ResponseMessage responseMessage = new ResponseMessage();
		if (res > 0) {

			responseMessage.setStatus(200);
			responseMessage.setMessage("Student Deleted Successfully !!");
			responseMessage.setHittime(new Timestamp(new Date().getTime()));
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		responseMessage.setStatus(402);
		responseMessage.setMessage("Student Not Deleted Successfully !!");
		responseMessage.setHittime(new Timestamp(new Date().getTime()));

		return new ResponseEntity<>(responseMessage, HttpStatus.EXPECTATION_FAILED);
	}

	@PutMapping(value = "/updatestudent/{sid}")
	public ResponseEntity<ResponseMessage> updateStudent(@PathVariable("sid") int sId,
			@RequestBody StudentBean studentBean) {

		int res = studentService.updateStudent(sId, studentBean);
		ResponseMessage responseMessage = new ResponseMessage();
		if (res > 0) {

			responseMessage.setStatus(200);
			responseMessage.setMessage("Student Updated Successfully !!");
			responseMessage.setHittime(new Timestamp(new Date().getTime()));
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		responseMessage.setStatus(402);
		responseMessage.setMessage("Student Not Deleted Successfully !!");
		responseMessage.setHittime(new Timestamp(new Date().getTime()));

		return new ResponseEntity<>(responseMessage, HttpStatus.EXPECTATION_FAILED);
	}

}
