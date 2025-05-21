package display;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SubjectDao;
import tool.DBConnectionManager;

@WebServlet("/display/subjectdelete")
public class SubjectDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 文字エンコーディング設定

        String cd = request.getParameter("cd"); // フォームから科目コードを取得
        if (cd == null || cd.trim().isEmpty()) {
            System.err.println("[ERROR] 科目コードが指定されていません。");
            request.setAttribute("errorMessage", "科目コードが指定されていません。");
            request.getRequestDispatcher("/display/SubjectList_error.jsp").forward(request, response);
            return;
        }

        // データベース接続
        Connection connection = DBConnectionManager.getConnection();
        SubjectDao subjectDao = new SubjectDao(connection);

        try {
            System.out.println("[INFO] 科目削除処理開始: cd=" + cd);
            boolean success = subjectDao.delete(cd);
            if (success) {
                System.out.println("[SUCCESS] 科目削除成功: cd=" + cd);
                response.sendRedirect(request.getContextPath() + "/display/subjectdelete_success.jsp"); // 成功時に一覧へリダイレクト
            } else {
                System.err.println("[ERROR] 科目削除失敗: cd=" + cd);
                request.setAttribute("errorMessage", "科目の削除に失敗しました。対象のデータが存在しない可能性があります。");
                request.getRequestDispatcher("/display/SubjectList_error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] データベースエラー: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "データベースエラーが発生しました。");
            request.getRequestDispatcher("/display/SubjectList_error.jsp").forward(request, response);
        }
    }
}