package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestListStudentDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";
    private String baseSql = "SELECT * FROM student_grades";

    public List<TestListStudent> postFilter(ResultSet rSet) throws SQLException {
        List<TestListStudent> testListStudents = new ArrayList<>();
        while (rSet.next()) {
            TestListStudent testListStudent = new TestListStudent();
            testListStudent.setStudentId(rSet.getInt("student_id"));
            testListStudent.setTestScore(rSet.getDouble("test_score"));
            testListStudents.add(testListStudent);
        }
        return testListStudents;
    }

    public List<TestListStudent> filter(Student student) {
        String sql = baseSql + " WHERE student_id = ?";
        List<TestListStudent> testListStudents = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            ResultSet rSet = pstmt.executeQuery();
            testListStudents = postFilter(rSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testListStudents;
    }
}
