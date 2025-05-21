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
import bean.Subject;
import tool.DBConnectionManager;

@WebServlet("/display/subject_change")
public class SubjectUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 文字エンコーディング設定

        String cd = request.getParameter("code"); // 科目コード
        String name = request.getParameter("name"); // 科目名

        // 入力チェック
        if (cd == null || cd.trim().isEmpty()) {
            System.err.println("[ERROR] 入力エラー: 科目コードが指定されていません。");
            request.setAttribute("errorMessage", "科目コードが指定されていません。");
            request.getRequestDispatcher("../share/share_error.jsp").forward(request, response);
            return;
        }

        if (name == null || name.trim().isEmpty() || name.length() > 20) {
            System.err.println("[ERROR] 入力エラー: 科目名が無効です (name=" + name + ")");
            request.setAttribute("errorMessage", "科目名は1文字以上20文字以内で入力してください。");
            request.getRequestDispatcher("../share/share_error.jsp").forward(request, response);
            return;
        }

        // データベース接続
        Connection connection = DBConnectionManager.getConnection();
        SubjectDao subjectDao = new SubjectDao(connection);

        // 更新処理
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);

        try {
            System.out.println("[INFO] 科目更新処理開始: cd=" + cd + ", name=" + name);
            boolean success = subjectDao.update(subject);
            if (success) {
                System.out.println("[SUCCESS] 科目更新成功: cd=" + cd + ", name=" + name);
                request.setAttribute("successMessage", "科目「" + name + "」の情報を更新しました。");
            } else {
                System.err.println("[ERROR] 科目更新失敗: cd=" + cd);
                request.setAttribute("errorMessage", "科目の更新に失敗しました。対象のデータが存在しない可能性があります。");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] データベースエラー: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "データベースエラーが発生しました。");
        }

        // `subjectlist.jsp` へフォワード
        request.getRequestDispatcher("/display/SubjectUpdate_success.jsp").forward(request, response);
    }
}