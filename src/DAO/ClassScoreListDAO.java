package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class ClassScoresDAO {
    private Connection connection;
    private String baseSql = "SELECT class_id, student_id, score FROM class_scores";

    public ClassScoresDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ClassScore> getClassScores() throws SQLException {
        List<ClassScore> classScores = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(baseSql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int classId = resultSet.getInt("class_id");
                int studentId = resultSet.getInt("student_id");
                int score = resultSet.getInt("score");
                classScores.add(new ClassScore(classId, studentId, score));
            }
        }
        return classScores;
    }

    public void addClassScore(ClassScore classScore) throws SQLException {
        String query = "INSERT INTO class_scores (class_id, student_id, score) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, classScore.getClassId());
            statement.setInt(2, classScore.getStudentId());
            statement.setInt(3, classScore.getScore());
            statement.executeUpdate();
        }
    }

    public void updateClassScore(ClassScore classScore) throws SQLException {
        String query = "UPDATE class_scores SET score = ? WHERE class_id = ? AND student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, classScore.getScore());
            statement.setInt(2, classScore.getClassId());
            statement.setInt(3, classScore.getStudentId());
            statement.executeUpdate();
        }
    }

    public void deleteClassScore(int classId, int studentId) throws SQLException {
        String query = "DELETE FROM class_scores WHERE class_id = ? AND student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, classId);
            statement.setInt(2, studentId);
            statement.executeUpdate();
        }
    }

    public List<TestListSubject> postFilter(ResultSet resultSet) throws SQLException {
        List<TestListSubject> filteredList = new ArrayList<>();
        while (resultSet.next()) {
            // ここでResultSetからデータを取得し、TestListSubjectオブジェクトを作成してリストに追加します
            // 例:
            // int id = resultSet.getInt("id");
            // String name = resultSet.getString("name");
            // filteredList.add(new TestListSubject(id, name));
        }
        return filteredList;
    }

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws SQLException {
        String query = baseSql + " WHERE ent_year = ? AND class_num = ? AND subject_id = ? AND school_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setInt(3, subject.getId());
            statement.setInt(4, school.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                return postFilter(resultSet);
            }
        }
    }
}
