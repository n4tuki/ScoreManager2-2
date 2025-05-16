package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao {


	public List<Student> allfilter(Connection conn) {
	    List<Student> students = new ArrayList<>();
	    String sql = "SELECT * FROM STUDENT";

	    try (PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            students.add(mapRowToStudent(rs));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return students;
	}



    public Student get(Connection conn, String no) {
        String sql = "SELECT * FROM STUDENT WHERE NO = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, no);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToStudent(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> filter(Connection conn, School school, int entYear, String classNum, boolean isAttend) {
        List<Student> students = new ArrayList<>();

        if (school == null || school.getCd() == null) {
            throw new IllegalArgumentException("School object or school code is null");
        }

        String sql = "SELECT * FROM STUDENT WHERE SCHOOL_CD = ? AND ENT_YEAR = ? AND CLASS_NUM = ? AND IS_ATTEND = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, school.getCd());
            stmt.setInt(2, entYear);
            stmt.setString(3, classNum);
            stmt.setBoolean(4, isAttend);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                students.add(mapRowToStudent(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public boolean save(Connection conn, Student student) {
        String sql = "INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getNo());
            stmt.setString(2, student.getName());
            stmt.setInt(3, student.getEntYear());
            stmt.setString(4, student.getClassNum());
            stmt.setBoolean(5, student.isAttend());
            stmt.setString(6, student.getSchoolCd());
            stmt.setString(6, student.getSchool() != null ? student.getSchool().getCd() : "DEFAULT_SCHOOL");

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Student mapRowToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setNo(rs.getString("NO"));
        student.setName(rs.getString("NAME"));
        student.setEntYear(rs.getInt("ENT_YEAR"));
        student.setClassNum(rs.getString("CLASS_NUM"));
        student.setAttend(rs.getBoolean("IS_ATTEND"));
        student.setSchoolCd(rs.getString("SCHOOL_CD"));
        return student;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mond", "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed", e);
        }
    }

    public School getSchoolByDefault(Connection conn) {
        String sql = "SELECT * FROM SCHOOL WHERE CD = 'DEFAULT_SCHOOL'";
        School school = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                school = new School(rs.getString("CD"), rs.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return school != null ? school : new School("DEFAULT_SCHOOL", "未設定の学校");
    }


}