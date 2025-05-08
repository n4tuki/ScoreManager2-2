package javacode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.TeacherDAO;
import bean.Teacher;
// 存在しない例外クラスの例です。実際には適切な例外クラスを使用してください。
// import exception.NoSuchTeacherException;

@WebServlet("/javacode/LoginProcess")
public class LoginProcess extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        TeacherDAO dao = new TeacherDAO();
        Teacher Another = null;
        String errorMessage = null;

        try {
            Another = dao.teacherSearch(id, password);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            errorMessage = "データベースへの接続または処理中にエラーが発生しました。";
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = "予期せぬエラーが発生しました。";
        }

        if (Another != null) {
            session.setAttribute("teacher", Another);
            response.sendRedirect("../display/menu.jsp");
        } else {
            if (errorMessage != null) {
                request.setAttribute("errorMessage", errorMessage);
            } else {
                request.setAttribute("errorMessage", "IDまたはパスワードが間違っています。");
            }
            request.getRequestDispatcher("../display/login-error.jsp").forward(request, response);
        }
    }
}