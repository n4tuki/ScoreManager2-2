package display;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SubjectDao;
import bean.Subject;
import tool.DBConnectionManager;

@WebServlet("/display/subjecteditf")
public class SubjectUpdate_F extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 文字エンコーディング設定

        String cd = request.getParameter("cd"); // 科目コードの取得
        if (cd == null || cd.trim().isEmpty()) {
            request.setAttribute("errorMessage", "科目コードが指定されていません。");
            request.getRequestDispatcher("../share/share_error.jsp").forward(request, response);
            return;
        }

        // データベース接続
        Connection connection = DBConnectionManager.getConnection();
        SubjectDao subjectDao = new SubjectDao(connection);

        try {
            Subject subject = subjectDao.get(cd); // 科目情報を取得
            if (subject == null) {
                request.setAttribute("errorMessage", "指定された科目は存在しません。");
                request.getRequestDispatcher("../share/share_error.jsp").forward(request, response);
                return;
            }

            // セッションに科目情報を保存
            HttpSession session = request.getSession();
            session.setAttribute("subject", subject);

            // `subjectupdate.jsp` にフォワード
            request.getRequestDispatcher("/display/SubjectUpdate.jsp").forward(request, response);

        } catch (SQLException e) {
            System.err.println("[ERROR] データベースエラー: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "データベースエラーが発生しました。");
            request.getRequestDispatcher("../share/share_error.jsp").forward(request, response);
        }
    }
}