package javacode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AnotherDAO;
import bean.Teacher;

@WebServlet("/LoginProcess")
public class LoginProcess extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        AnotherDAO dao = new AnotherDAO(); // DAOのインスタンスを生成
        Teacher Another = null;
        String errorMessage = null;

        try {
            Another = dao.teacherSearch(id, password); // IDとパスワードで教師情報を検索
        } catch (Exception e) {
            // データベース処理中にエラーが発生した場合の処理
            e.printStackTrace(); // エラー内容をログに出力 (本番環境ではより適切なログ出力を行うべきです)
            errorMessage = "データベースエラーが発生しました。";
        }

        if (Another != null) {
            session.setAttribute("teacher", Another); // ログイン成功、Teacherオブジェクトをセッションに保存
            response.sendRedirect("login-out.jsp"); // ログイン成功後の画面へリダイレクト
        } else {
            if (errorMessage != null) {
                request.setAttribute("errorMessage", errorMessage);
            } else {
                request.setAttribute("errorMessage", "IDまたはパスワードが間違っています。"); // エラーメッセージをリクエストに設定
            }
            request.getRequestDispatcher("login-error.jsp").forward(request, response); // ログイン失敗画面へフォワード
        }
    }
}