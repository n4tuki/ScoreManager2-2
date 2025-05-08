package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class SchoolDao extends DAO {

    private Connection connection;

    // コンストラクタ: データベース接続を初期化
    public SchoolDao(Connection connection) {
        this.connection = connection;
    }

    // 指定された学校コードに基づいてSchoolオブジェクトを取得
    public School get(String cd) throws SQLException {
        String sql = "SELECT * FROM school WHERE cd = ?";
        School school = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cd); // 学校コードを設定
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    school = new School();
                    school.setCd(rs.getString("cd"));
                    school.setName(rs.getString("name"));
                }
            }
        }
        return school;
    }

    // すべてのSchoolオブジェクトのリストを取得
    public List<School> getAll() throws SQLException {
        String sql = "SELECT * FROM school";
        List<School> schools = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                School school = new School();
                school.setCd(rs.getString("cd"));
                school.setName(rs.getString("name"));
                schools.add(school);
            }
        }
        return schools;
    }

    // 新しいSchoolをデータベースに挿入
    public boolean save(School school) throws SQLException {
        String sql = "INSERT INTO school (cd, name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, school.getCd()); // 学校コードを設定
            stmt.setString(2, school.getName()); // 学校名を設定
            return stmt.executeUpdate() > 0; // 挿入が成功したかどうかを返す
        }
    }

    // 既存のSchoolを更新
    public boolean update(School school) throws SQLException {
        String sql = "UPDATE school SET name = ? WHERE cd = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, school.getName()); // 新しい学校名を設定
            stmt.setString(2, school.getCd()); // 学校コードを設定
            return stmt.executeUpdate() > 0; // 更新が成功したかどうかを返す
        }
    }

    // 指定された学校コードに基づいてSchoolを削除
    public boolean delete(String cd) throws SQLException {
        String sql = "DELETE FROM school WHERE cd = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cd); // 学校コードを設定
            return stmt.executeUpdate() > 0; // 削除が成功したかどうかを返す
        }
    }
}
