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

@WebServlet("/display/subjectcreate")
public class SubjectCreate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 文字エンコーディング設定

        String schoolCd ="001"; //スクールコードを設定（固定）
        String cd = request.getParameter("code");
        String name = request.getParameter("name");

        boolean hasError = false;

        // 入力チェック
        if (schoolCd == null || schoolCd.trim().isEmpty() || schoolCd.length() > 3) {
            request.setAttribute("fieldError_schoolCd", "学校コードは3文字以内で入力してください。");
            System.err.println("入力エラー: schoolCd=" + schoolCd);
            hasError = true;
        }
        if (cd == null || cd.trim().isEmpty() || cd.length() > 3) {
            request.setAttribute("fieldError_code", "科目コードは3文字以内で入力してください。");
            System.err.println("入力エラー: cd=" + cd);
            hasError = true;
        }
        if (name == null || name.trim().isEmpty() || name.length() > 20) {
            request.setAttribute("fieldError_name", "科目名は20文字以内で入力してください。");
            System.err.println("入力エラー: name=" + name);
            hasError = true;
        }

        // エラーがある場合は入力値を保持して再表示
        if (hasError) {
            request.setAttribute("school_cd", schoolCd);
            request.setAttribute("code", cd);
            request.setAttribute("name", name);
            request.getRequestDispatcher("/display/SubjectCreate_error.jsp").forward(request, response);
            return;
        }

        // データベース接続
        Connection connection = DBConnectionManager.getConnection();
        SubjectDao subjectDao = new SubjectDao(connection);
        Subject subject = new Subject();
        subject.setSchoolCd(schoolCd); // 学校コードを設定
        subject.setCd(cd);
        subject.setName(name);

        try {
            boolean success = subjectDao.save(subject);
            if (success) {
                System.out.println("科目登録成功: schoolCd=" + schoolCd + ", cd=" + cd + ", name=" + name);
                response.sendRedirect(request.getContextPath() + "/display/SubjectCreate_success.jsp"); // 成功時に一覧へリダイレクト
            } else {
                System.err.println("科目登録失敗: schoolCd=" + schoolCd + ", cd=" + cd + ", name=" + name);
                request.setAttribute("errorMessage", "科目の登録に失敗しました。");
                request.getRequestDispatcher("/display/SubjectCreate_error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            System.err.println("データベースエラー: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "データベースエラーが発生しました。");
            request.getRequestDispatcher("/display/SubjectCreate_error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}