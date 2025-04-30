package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDAO extends DAO{
	    // POINTがNULLのデータを取得するメソッド
	    public List<Test> FindPoint(Integer year, String classNum, String subjectCd, Integer no) throws Exception {
	    	Connection connection =getConnection();
	    	        String sql = "SELECT * FROM TEST WHERE POINT IS NULL "
	    	                   + "AND (year = ? OR ? IS NULL) "
	    	                   + "AND (class_num = ? OR ? IS NULL) "
	    	                   + "AND (subject_cd = ? OR ? IS NULL) "
	    	                   + "AND (no = ? OR ? IS NULL)";
	    	        List<Test> records = new ArrayList<>();

	    	        try (PreparedStatement st = connection.prepareStatement(sql)) {
	    	            st.setObject(1, year);        // 入学年度条件
	    	            st.setObject(2, year);        // NULLチェック
	    	            st.setObject(3, classNum);    // クラス条件
	    	            st.setObject(4, classNum);    // NULLチェック
	    	            st.setObject(5, subjectCd);   // 科目条件
	    	            st.setObject(6, subjectCd);   // NULLチェック
	    	            st.setObject(7, no);          // 回数条件
	    	            st.setObject(8, no);          // NULLチェック

	    	            try (ResultSet rs = st.executeQuery()) {
	    	                while (rs.next()) {
	    	                    Test record = new Test();
	    	                        rs.getString("STUDENT_NO");
	    	                        rs.getString("SUBJECT_CD");
	    	                        rs.getString("SCHOOL_CD");
	    	                        rs.getInt("NO");
	    	                        rs.getInt("POINT");
	    	                        rs.getString("CLASS_NUM");

	    	                    records.add(record);
	    	                }
	    	            }
	    	        } catch (Exception e) {
	    	            throw new Exception("データの絞り込み中にエラーが発生しました: " + e.getMessage(), e);
	    	        }
	    	        return records;
	    	    }
	    
	    
	    	}
