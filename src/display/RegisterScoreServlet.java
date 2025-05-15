package display;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//s
@WebServlet("/registerScore")
public class RegisterScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String studentName = request.getParameter("studentName");
        String subjectName = request.getParameter("subject");
        int score = Integer.parseInt(request.getParameter("score"));

        try (Connection conn = DBConnection.getConnection()) {
            // 学生番号と学校コード、クラス番号を取得
            String studentSql = "SELECT NO, SCHOOL_CD, CLASS_NUM FROM STUDENT WHERE NAME = ?";
            PreparedStatement studentStmt = conn.prepareStatement(studentSql);
            studentStmt.setString(1, studentName);
            ResultSet studentRs = studentStmt.executeQuery();

            if (!studentRs.next()) {
                response.getWriter().println("学生が見つかりません。");
                return;
            }

            String studentNo = studentRs.getString("NO");
            String schoolCd = studentRs.getString("SCHOOL_CD");
            String classNum = studentRs.getString("CLASS_NUM");

            // 科目コードを取得
            String subjectSql = "SELECT CD FROM SUBJECT WHERE NAME = ? AND SCHOOL_CD = ?";
            PreparedStatement subjectStmt = conn.prepareStatement(subjectSql);
            subjectStmt.setString(1, subjectName);
            subjectStmt.setString(2, schoolCd);
            ResultSet subjectRs = subjectStmt.executeQuery();

            if (!subjectRs.next()) {
                response.getWriter().println("科目が見つかりません。");
                return;
            }

            String subjectCd = subjectRs.getString("CD");

            // テスト番号を取得（最大値 + 1）
            String noSql = "SELECT COALESCE(MAX(NO), 0) + 1 AS nextNo FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ?";
            PreparedStatement noStmt = conn.prepareStatement(noSql);
            noStmt.setString(1, studentNo);
            noStmt.setString(2, subjectCd);
            noStmt.setString(3, schoolCd);
            ResultSet noRs = noStmt.executeQuery();
            noRs.next();
            int testNo = noRs.getInt("nextNo");

            // 成績を登録
            String insertSql = "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, studentNo);
            insertStmt.setString(2, subjectCd);
            insertStmt.setString(3, schoolCd);
            insertStmt.setInt(4, testNo);
            insertStmt.setInt(5, score);
            insertStmt.setString(6, classNum);
            insertStmt.executeUpdate();

            response.getWriter().println("成績を登録しました。");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("エラーが発生しました: " + e.getMessage());
        }
    }
}
