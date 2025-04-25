package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class SubjectDao extends DAO {

    private Connection connection;

    // コンストラクタ: データベース接続を初期化
    public SubjectDao(Connection connection) {
        this.connection = connection;
    }

    // 指定された科目コードに基づいてSubjectオブジェクトを取得
    public Subject get(String code) throws SQLException {
        String sql = "SELECT * FROM subject WHERE code = ?";
        Subject subject = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code); // 科目コードを設定
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    subject.setCode(rs.getString("code")); // 科目コードを設定
                    subject.setName(rs.getString("name")); // 科目名を設定
                }
            }
        }
        return subject; // 科目が見つからない場合はnullを返す
    }

    // すべてのSubjectオブジェクトのリストを取得
    public List<Subject> getAll() throws SQLException {
        String sql = "SELECT * FROM subject";
        List<Subject> subjects = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCode(rs.getString("code")); // 科目コードを設定
                subject.setName(rs.getString("name")); // 科目名を設定
                subjects.add(subject); // 科目リストに追加
            }
        }
        return subjects;
    }

    // 新しいSubjectをデータベースに挿入
    public boolean save(Subject subject) throws SQLException {
        String sql = "INSERT INTO subject (code, name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subject.getCode()); // 科目コードを設定
            stmt.setString(2, subject.getName()); // 科目名を設定
            return stmt.executeUpdate() > 0; // 挿入が成功したかどうかを返す
        }
    }

    // 既存のSubjectを更新
    public boolean update(Subject subject) throws SQLException {
        String sql = "UPDATE subject SET name = ? WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subject.getName()); // 新しい科目名を設定
            stmt.setString(2, subject.getCode()); // 科目コードを設定
            return stmt.executeUpdate() > 0; // 更新が成功したかどうかを返す
        }
    }

    // 指定された科目コードに基づいてSubjectを削除
    public boolean delete(String code) throws SQLException {
        String sql = "DELETE FROM subject WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code); // 科目コードを設定
            return stmt.executeUpdate() > 0; // 削除が成功したかどうかを返す
        }
    }
}
