package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

/**
 * TESTテーブルに関連する操作を扱うData Access Object (DAO)クラス。
 */
public class TestDAO extends DAO {
    // POINTがNULLのデータを取得するメソッド
    public List<Test> FindPoint(Integer year, String classNum, String subjectCd, Integer no) throws Exception {
        Connection connection = getConnection();
        String sql = "SELECT * FROM TEST WHERE POINT IS NULL "
                   + "AND (year = ? OR ? IS NULL) "
                   + "AND (class_num = ? OR ? IS NULL) "
                   + "AND (subject_cd = ? OR ? IS NULL) "
                   + "AND (no = ? OR ? IS NULL)";
        List<Test> records = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            // SQLクエリのパラメータを設定
            st.setObject(1, year);        // 入学年度条件
            st.setObject(2, year);        // NULLチェック
            st.setObject(3, classNum);    // クラス条件
            st.setObject(4, classNum);    // NULLチェック
            st.setObject(5, subjectCd);   // 科目条件
            st.setObject(6, subjectCd);   // NULLチェック
            st.setObject(7, no);          // 回数条件
            st.setObject(8, no);          // NULLチェック

            // クエリを実行し、結果セットを処理
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Test record = new Test();
                    record.setStudentNo(rs.getString("STUDENT_NO"));
                    record.setSubjectCd(rs.getString("SUBJECT_CD"));
                    record.setSchoolCd(rs.getString("SCHOOL_CD"));
                    record.setNo(rs.getInt("NO"));
                    record.setPoint(rs.getInt("POINT"));
                    record.setClassNum(rs.getString("CLASS_NUM"));

                    records.add(record); // 結果セットをTestオブジェクトにマッピングしてリストに追加
                }
            }
        } catch (Exception e) {
            throw new Exception("データの絞り込み中にエラーが発生しました: " + e.getMessage(), e);
        }
        return records;
    }

    // 基本SQLクエリ
    private static final String BASE_SQL = "SELECT * FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?";

    public Test get(String studentNo, String subjectCd, String schoolCd, int no) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(BASE_SQL)) {
            // SQLクエリのパラメータを設定
            statement.setString(1, studentNo);
            statement.setString(2, subjectCd);
            statement.setString(3, schoolCd);
            statement.setInt(4, no);

            // クエリを実行し、結果セットを処理
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet); // 結果セットをTestオブジェクトにマッピング
                }
            }
        }
        return null; // レコードが見つからない場合はnullを返す
    }

    /**
     * 結果セットを学校コードに基づいてフィルタリングし、Testレコードのリストを返します。

     */
    public List<Test> postFilter(ResultSet resultSet, String schoolCd) throws SQLException {
        List<Test> tests = new ArrayList<>();
        while (resultSet.next()) {
            // 学校コードが一致する場合、リストに追加
            if (schoolCd.equals(resultSet.getString("SCHOOL_CD"))) {
                tests.add(mapRow(resultSet));
            }
        }
        return tests;
    }

    /**
     * 指定されたパラメータに基づいてTESTテーブルをフィルタリングし、Testレコードのリストを返します。
     */
    public List<Test> filter(int entYear, String classNum, String subjectCd, int num, String schoolCd) throws SQLException {
        String sql = "SELECT * FROM TEST WHERE CLASS_NUM = ? AND SUBJECT_CD = ? AND NO = ? AND SCHOOL_CD = ?";
        List<Test> tests = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // SQLクエリのパラメータを設定
            statement.setString(1, classNum);
            statement.setString(2, subjectCd);
            statement.setInt(3, num);
            statement.setString(4, schoolCd);

            // クエリを実行し、結果セットを処理
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    tests.add(mapRow(resultSet)); // 結果セットをTestオブジェクトにマッピング
                }
            }
        }
        return tests;
    }

    private Test mapRow(ResultSet resultSet) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/**
     * TestレコードのリストをTESTテーブルに保存します。

     */
    public boolean save(List<Test> tests) throws SQLException {
        String sql = "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Test test : tests) {
                // SQLクエリのパラメータを設定
                statement.setString(1, test.getStudentNo());
                statement.setString(2, test.getSubjectCd());
                statement.setString(3, test.getSchoolCd());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
                statement.setString(6, test.getClassNum());
                statement.addBatch(); // バッチに追加
            }
            statement.executeBatch(); // バッチを実行
        }
        return true;
    }

    /**
     * 単一のTestレコードをTESTテーブルに保存します。

     */
    public boolean save(Test test, Connection connection) throws SQLException {
        String sql = "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // SQLクエリのパラメータを設定
            statement.setString(1, test.getStudentNo());
            statement.setString(2, test.getSubjectCd());
            statement.setString(3, test.getSchoolCd());
            statement.setInt(4, test.getNo());
            statement.setInt(5, test.getPoint());
            statement.setString(6, test.getClassNum());
            statement.executeUpdate(); // 更新を実行
        }
        return true;
    }


    public Connection getConnection() throws SQLException {
        // 接続ロジックをここに実装
        return null;
    }
}