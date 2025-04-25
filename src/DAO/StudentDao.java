package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends DAO {
    private String baseSql = "SELECT * FROM STUDENT";

    public Student get(String no) throws Exception {
        String sql = baseSql + " WHERE NO = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, no);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToStudent(rs);
            }
        }
        return null;
    }

    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        List<Student> students = new ArrayList<>();
        while (rSet.next()) {
            Student student = mapRowToStudent(rSet);
            if (student.getSchoolCd().equals(school.getCd())) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        String sql = baseSql + " WHERE SCHOOL_CD = ? AND ENT_YEAR = ? AND CLASS_NUM = ? AND IS_ATTEND = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, school.getCd());
            stmt.setInt(2, entYear);
            stmt.setString(3, classNum);
            stmt.setBoolean(4, isAttend);
            ResultSet rs = stmt.executeQuery();
            return postFilter(rs, school);
        }
    }

    public List<Student> filter(School school, boolean isAttend) throws Exception {
        String sql = baseSql + " WHERE SCHOOL_CD = ? AND IS_ATTEND = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, school.getCd());
            stmt.setBoolean(2, isAttend);
            ResultSet rs = stmt.executeQuery();
            return postFilter(rs, school);
        }
    }

    public boolean save(Student student) throws Exception {
        String sql = "INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, student.getNo());
            stmt.setString(2, student.getName());
            stmt.setInt(3, student.getEntYear());
            stmt.setString(4, student.getClassNum());
            stmt.setBoolean(5, student.isAttend());
            stmt.setString(6, student.getSchoolCd());
            return stmt.executeUpdate() > 0;
        }
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
}
