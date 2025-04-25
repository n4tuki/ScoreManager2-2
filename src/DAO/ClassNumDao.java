package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends DAO {

    private Connection connection;

    public ClassNumDao(Connection connection) {
        this.connection = connection;
    }

    public List<ClassNum> get(String classNum, String schoolId) throws SQLException {
        String sql = "SELECT * FROM class_num WHERE class_num = ? AND school_id = ?";
        List<ClassNum> classNums = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, classNum);
            stmt.setString(2, schoolId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ClassNum cn = new ClassNum();
                    cn.setClassNum(rs.getString("class_num"));
                    cn.setSchool(new School(schoolId)); // Schoolオブジェクトを設定
                    classNums.add(cn);
                }
            }
        }
        return classNums;
    }

    public List<String> filter(School school) throws SQLException {
        String sql = "SELECT class_num FROM class_num WHERE school_id = ?";
        List<String> classNums = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, school.getCd()); // 学校IDを設定
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    classNums.add(rs.getString("class_num"));
                }
            }
        }
        return classNums;
    }

    public boolean save(ClassNum classNum) throws SQLException {
        String sql = "INSERT INTO class_num (class_num, school_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, classNum.getClassNum());
            stmt.setString(2, classNum.getSchool().getCd()); // 学校IDを設定
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean save(ClassNum classNum, String newClassNum) throws SQLException {
        String sql = "UPDATE class_num SET class_num = ? WHERE class_num = ? AND school_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newClassNum); // 新しいクラス番号を設定
            stmt.setString(2, classNum.getClassNum()); // 現在のクラス番号を設定
            stmt.setString(3, classNum.getSchool().getCd()); // 学校IDを設定
            return stmt.executeUpdate() > 0;
        }
    }
}
