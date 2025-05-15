package javacode;

import java.io.IOException;
import java.io.PrintWriter;
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
    private static final String JDBC_URL = "jdbc:h2:~/test"; // H2のDBファイル
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String studentName = request.getParameter("studentName");
        String subject = request.getParameter("subject");
        String scoreStr = request.getParameter("score");

        try (PrintWriter out = response.getWriter()) {
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

                out.println("<html><body>");
                out.println("<h2>成績が正常に登録されました。</h2>");
                out.println("<a href='scoreRegister.jsp'>戻る</a>");
                out.println("</body></html>");

            } catch (SQLException e) {
                out.println("<html><body>");
                out.println("<h2>データベースエラーが発生しました。</h2>");
                out.println("<p>" + e.getMessage() + "</p>");
                out.println("<a href='scoreRegister.jsp'>戻る</a>");
                out.println("</body></html>");
            }

        } catch (NumberFormatException e) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h2>点数は数値で入力してください。</h2>");
            response.getWriter().println("<a href='scoreRegister.jsp'>戻る</a>");
            response.getWriter().println("</body></html>");
        }
    }
}
