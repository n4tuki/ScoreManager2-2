package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;
//a

public class TeacherDAO extends DAO {

	public Teacher teacherSearch(String id, String password) throws Exception {
	    Teacher teacher = null;
	    Connection con = getConnection();

	    PreparedStatement st = con.prepareStatement(
	        "SELECT t.*, s.name as school_name, s.cd as school_cd " +
	        "FROM teacher t JOIN school s ON t.school_cd = s.cd " +
	        "WHERE t.id = ? AND t.password = ?"
	    );
	    st.setString(1, id);
	    st.setString(2, password);
	    ResultSet rs = st.executeQuery();

	    while (rs.next()) {
	        teacher = new Teacher();
	        teacher.setId(rs.getString("id"));
	        teacher.setPassword(rs.getString("password"));
	        teacher.setName(rs.getString("name"));

	        School school = new School();
	        school.setCd(rs.getString("school_cd"));
	        school.setName(rs.getString("school_name"));
	        teacher.setSchool(school);
	    }

	    st.close();
	    con.close();
	    return teacher;
	}
}

