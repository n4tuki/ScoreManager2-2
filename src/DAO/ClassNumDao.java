package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends DAO {

    private Connection connection;

    public ClassNumDao(Connection connection) {
        this.connection = connection;
    }

    public List<ClassNum> get(String class_num, School school) throws SQLException {
        String sql = "SELECT * FROM class_num WHERE class_num = ? AND school_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, class_num);
            stmt.setInt(2, school.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                // ResultSetの処理
            }
        }
        return null; // 実際のリストを返すように変更してください
    }

    public List<String> filter(School school) throws SQLException {
        String sql = "SELECT class_num FROM class_num WHERE school_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, school.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                // ResultSetの処理
            }
        }
        return null; // 実際のリストを返すように変更してください
    }

    public boolean save(ClassNum classNum) throws SQLException {
        String sql = "INSERT INTO class_num (class_num, school_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, classNum.getClassNum());
            stmt.setInt(2, classNum.getSchool().getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean save(ClassNum classNum, String newClassNum) throws SQLException {
        String sql = "UPDATE class_num SET class_num = ? WHERE class_num = ? AND school_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newClassNum);
            stmt.setString(2, classNum.getClassNum());
            stmt.setInt(3, classNum.getSchool().getId());
            return stmt.executeUpdate() > 0;
        }
    }
}
