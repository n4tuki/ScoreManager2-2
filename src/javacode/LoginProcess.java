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

            // **Schoolをセッションにセット**
            if (Another.getSchool() != null) {
                System.out.println("Teacher's School object before setting session: " + Another.getSchool());
                System.out.println("School CD before setting session: " + Another.getSchool().getCd());

                session.setAttribute("school", Another.getSchool());
                System.out.println("Final check: School in session after LoginProcess: " + session.getAttribute("school"));

                System.out.println("School stored in session after LoginProcess: " + session.getAttribute("school"));
            } else {
                System.out.println("Warning: Another.getSchool() is null!");
            }

            response.sendRedirect("../display/menu.jsp");
            return; // ここで処理を終了
        }

        // **ログイン失敗時の処理**
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
        } else {
            request.setAttribute("errorMessage", "IDまたはパスワードが間違っています。");
        }
        request.getRequestDispatcher("../display/login-error.jsp").forward(request, response);
    }
}