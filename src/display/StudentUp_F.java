package display;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDao;
import bean.Student;

@WebServlet(urlPatterns={"/display/updf"})
public class StudentUp_F extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから `no` を取得
        String no = request.getParameter("no");
        System.out.println("StudentUp_Fサーブレットで取得した学生番号: " + no);

        // `StudentDao` を使って `no` に該当する学生を取得
        StudentDao studentDao = new StudentDao();
        Student student = null;

        try (Connection conn = studentDao.getConnection()) {
            student = studentDao.get(conn, no);
            System.out.println("StudentUp_Fで取得した学生: " + student);
        } catch (SQLException e) {
            throw new ServletException("データベースエラー", e);
        }

        // `student` をリクエストにセットし、JSP に渡す
        request.setAttribute("student", student);
        request.getRequestDispatcher("/display/StudentUpdate.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}