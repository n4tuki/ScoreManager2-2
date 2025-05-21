package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao {
    private Connection connection;

    public SubjectDao(Connection connection) {
        this.connection = connection;
    }

    // 指定した科目コードで科目情報を取得
    public Subject get(String cd) throws SQLException {
        String sql = "SELECT cd, name FROM subject WHERE cd = ?";
        Subject subject = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cd);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    subject.setCd(rs.getString("cd"));  // カラム名を統一
                    subject.setName(rs.getString("name"));
                }
            }
        }
        return subject;
    }

    // 全科目のリストを取得
    public List<Subject> getAll() throws SQLException {
        String sql = "SELECT cd, name FROM subject";
        List<Subject> subjects = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCd(rs.getString("cd"));  // カラム名を統一
                subject.setName(rs.getString("name"));
                subjects.add(subject);
            }
        }
        return subjects;
    }

    // 科目を登録
    public boolean save(Subject subject) throws SQLException {
        String sql = "INSERT INTO subject (cd, name) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subject.getCd());
            stmt.setString(2, subject.getName());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("保存に失敗しました。影響を受けた行がありません。");
            }
            return true;
        }
    }

    // 科目を更新
    public boolean update(Subject subject) throws SQLException {
        String sql = "UPDATE subject SET name = ? WHERE cd = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subject.getName());
            stmt.setString(2, subject.getCd());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("更新に失敗しました。対象のデータが存在しない可能性があります。");
            }
            return true;
        }
    }

    // 科目を削除
    public boolean delete(String cd) throws SQLException {
        String sql = "DELETE FROM subject WHERE cd = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cd);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("削除に失敗しました。対象のデータが存在しない可能性があります。");
            }
            return true;
        }
    }
}