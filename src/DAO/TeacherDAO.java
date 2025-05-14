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

	            School school = new School();
	            //school.setCd(rs.getString("SCHOOL_CD"));

	            String schoolCd = rs.getString("SCHOOL_CD");
	            System.out.println("Retrieved SCHOOL_CD before setting: " + schoolCd);

	            if (schoolCd != null) {
	                school.setCd(schoolCd);
	            } else {
	                System.out.println("Warning: SCHOOL_CD is null before assigning to School object.");
	            }

	            teacher.setSchool(school);
	            System.out.println("Set School in Teacher: " + teacher.getSchool().getCd());

	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return teacher;
	}
}

