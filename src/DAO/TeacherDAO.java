package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;
import bean.Teacher;
//a

public class TeacherDAO extends DAO {

	public Teacher teacherSearch(String id, String password) throws Exception {
	    Teacher teacher = null;
	    String sql = "SELECT * FROM TEACHER WHERE ID = ? AND PASSWORD = ?";
	    try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
	        stmt.setString(1, id);
	        stmt.setString(2, password);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            teacher = new Teacher();
	            teacher.setId(rs.getString("ID"));
	            teacher.setName(rs.getString("NAME"));

	            // School情報を取得
	            School school = new School();
	            school.setCd(rs.getString("SCHOOL_CD"));
	            teacher.setSchool(school); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return teacher;
	}
}

