package javacode;
//ss
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerScore")
public class RegisterScoreServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:h2:~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String studentName = request.getParameter("studentName");
        String subject = request.getParameter("subject");
        String scoreStr = request.getParameter("score");

        try {
            int score = Integer.parseInt(scoreStr);

            try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                // テーブルがなければ作成
                String createTableSQL = "CREATE TABLE IF NOT EXISTS scores (" +
                        "id IDENTITY PRIMARY KEY, " +
                        "student_name VARCHAR(100), " +
                        "subject VARCHAR(100), " +
                        "score INT)";
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(createTableSQL);
                }

                // 成績を挿入
                String insertSQL = "INSERT INTO scores (student_name, subject, score) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setString(1, studentName);
                    pstmt.setString(2, subject);
                    pstmt.setInt(3, score);
                    pstmt.executeUpdate();
                }

                // 成功時にリダイレクト
                response.sendRedirect("management/display/registerResult.jsp");

            } catch (SQLException e) {
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
