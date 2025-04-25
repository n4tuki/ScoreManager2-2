package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends DAO {
    private String baseSql = "SELECT * FROM STUDENT"; // 基本SQLクエリ

    // 指定された学生番号に基づいてStudentオブジェクトを取得
    public Student get(String no) throws Exception {
        String sql = baseSql + " WHERE NO = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, no); // 学生番号を設定
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToStudent(rs); // 結果セットをStudentオブジェクトにマッピング
            }
        }
        return null; // 学生が見つからない場合はnullを返す
    }

    // 結果セットをフィルタリングして指定された学校の学生リストを取得
    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        List<Student> students = new ArrayList<>();
        while (rSet.next()) {
            Student student = mapRowToStudent(rSet); // 結果セットをStudentオブジェクトにマッピング
            if (student.getSchoolCd().equals(school.getCd())) { // 学校コードが一致する場合
                students.add(student); // 学生リストに追加
            }
        }
        return students;
    }

    // 指定された条件に基づいて学生リストをフィルタリング
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        String sql = baseSql + " WHERE SCHOOL_CD = ? AND ENT_YEAR = ? AND CLASS_NUM = ? AND IS_ATTEND = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, school.getCd()); // 学校コードを設定
            stmt.setInt(2, entYear); // 入学年度を設定
            stmt.setString(3, classNum); // クラス番号を設定
            stmt.setBoolean(4, isAttend); // 出席状況を設定
            ResultSet rs = stmt.executeQuery();
            return postFilter(rs, school); // 結果セットをフィルタリング
        }
    }

    // 指定された学校と出席状況に基づいて学生リストをフィルタリング
    public List<Student> filter(School school, boolean isAttend) throws Exception {
        String sql = baseSql + " WHERE SCHOOL_CD = ? AND IS_ATTEND = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, school.getCd()); // 学校コードを設定
            stmt.setBoolean(2, isAttend); // 出席状況を設定
            ResultSet rs = stmt.executeQuery();
            return postFilter(rs, school); // 結果セットをフィルタリング
        }
    }

    // 新しいStudentをデータベースに挿入
    public boolean save(Student student) throws Exception {
        String sql = "INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, student.getNo()); // 学生番号を設定
            stmt.setString(2, student.getName()); // 学生名を設定
            stmt.setInt(3, student.getEntYear()); // 入学年度を設定
            stmt.setString(4, student.getClassNum()); // クラス番号を設定
            stmt.setBoolean(5, student.isAttend()); // 出席状況を設定
            stmt.setString(6, student.getSchoolCd()); // 学校コードを設定
            return stmt.executeUpdate() > 0; // 挿入が成功したかどうかを返す
        }
    }

    // 結果セットの行をStudentオブジェクトにマッピング
    private Student mapRowToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setNo(rs.getString("NO")); // 学生番号を設定
        student.setName(rs.getString("NAME")); // 学生名を設定
        student.setEntYear(rs.getInt("ENT_YEAR")); // 入学年度を設定
        student.setClassNum(rs.getString("CLASS_NUM")); // クラス番号を設定
        student.setAttend(rs.getBoolean("IS_ATTEND")); // 出席状況を設定
        student.setSchoolCd(rs.getString("SCHOOL_CD")); // 学校コードを設定
        return student;
    }
}
