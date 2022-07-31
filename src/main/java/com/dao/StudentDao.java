package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bean.StudentBean;
import com.service.StudentService;

@Repository
public class StudentDao implements StudentService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int addStudent(StudentBean studentBean) {
		return jdbcTemplate.update("insert into student(sName,sEmail,sAge,sPassword,sPhone,rid)values(?,?,?,?,?,?)",
				studentBean.getsName(), studentBean.getsEmail(), studentBean.getsAge(), studentBean.getsPassword(),
				studentBean.getsPhone(), 1);
	}

	private final static class StudentMapper implements RowMapper<StudentBean> {

		@Override
		public StudentBean mapRow(ResultSet rs, int rowNum) throws SQLException {

			StudentBean studentBean = new StudentBean();
			studentBean.setsName(rs.getString("sname"));
			studentBean.setsEmail(rs.getString("semail"));
			studentBean.setsAge(rs.getInt("sage"));
			studentBean.setsPassword(rs.getString("spassword"));
			studentBean.setsPhone(rs.getString("sphone"));
			studentBean.setrId(rs.getInt("rid"));
			studentBean.setrName(rs.getString("rname"));
			studentBean.setsId(rs.getInt("sid"));

			return studentBean;
		}

	}

	@Override
	public int deleteStudent(int sId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("delete from student where sid = " + sId + "");
	}

	@Override
	public List<StudentBean> studentList() {

		return jdbcTemplate.query("select * from student natural join role", new StudentMapper());
	}

	@Override
	public int updateStudent(int sId, StudentBean studentBean) {

		return jdbcTemplate.update("update student set sName = ?,sEmail=?,sAge= ?,sPassword =?,sPhone = ? where sId =?",
				studentBean.getsName(), studentBean.getsEmail(), studentBean.getsAge(), studentBean.getsPassword(),
				studentBean.getsPhone(), sId);
	}

	@Override
	public StudentBean getStudntById(int sId) throws DataAccessException {

		return jdbcTemplate.queryForObject("select * from student natural join role where sid = " + sId + "",
				new StudentMapper());
	}

	@Override
	public StudentBean login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
