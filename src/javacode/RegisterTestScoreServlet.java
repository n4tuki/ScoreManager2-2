package javacode;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerTestScore")
public class RegisterTestScoreServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/mondH2";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String studentNo = request.getParameter("studentNo");
        String subjectCd = request.getParameter("subjectCd");
        String schoolCd = request.getParameter("schoolCd");
        String no = request.getParameter("no");
        String pointStr = request.getParameter("point");
        String classNum = request.getParameter("classNum");

        try {
            int point = Integer.parseInt(pointStr);

            try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String insertSQL = "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) " +
                                   "VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setString(1, studentNo);
                    pstmt.setString(2, subjectCd);
                    pstmt.setString(3, schoolCd);
                    pstmt.setString(4, no);
                    pstmt.setInt(5, point);
                    pstmt.setString(6, classNum);
                    int rows = pstmt.executeUpdate();

                    if (rows > 0) {
                        response.sendRedirect("management/display/registerResult.jsp");
                    } else {
                        showError(response, "登録に失敗しました。");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                showError(response, "データベースエラー: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            showError(response, "点数は0〜100の数値で入力してください。");
        }
    }

    private void showError(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>エラーが発生しました</h2>");
        response.getWriter().println("<p>" + message + "</p>");
        response.getWriter().println("<a href='scoreRegister.jsp'>戻る</a>");
        response.getWriter().println("</body></html>");
    }
}

