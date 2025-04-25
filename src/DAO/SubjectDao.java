package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Subject; // 正しいSubjectクラスをインポート

public class SubjectDao extends DAO {

    private Connection connection;

    public SubjectDao(Connection connection) {
        this.connection = connection;
    }

    public Subject get(String code) throws SQLException {
        String sql = "SELECT * FROM subject WHERE code = ?";
        Subject subject = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    subject.setCode(rs.getString("code"));
                    subject.setName(rs.getString("name"));
                }
            }
        }
        return subject;
    }

    public List<Subject> getAll() throws SQLException {
        String sql = "SELECT * FROM subject";
        List<Subject> subjects = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCode(rs.getString("code"));
                subject.setName(rs.getString("name"));
                subjects.add(subject);
            }
        }
        return subjects;
    }

    public boolean save(Subject subject) throws SQLException {
        String sql = "INSERT INTO subject (code, name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subject.getCode());
            stmt.setString(2, subject.getName());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean update(Subject subject) throws SQLException {
        String sql = "UPDATE subject SET name = ? WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subject.getName());
            stmt.setString(2, subject.getCode());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(String code) throws SQLException {
        String sql = "DELETE FROM subject WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            return stmt.executeUpdate() > 0;
        }
    }
}
