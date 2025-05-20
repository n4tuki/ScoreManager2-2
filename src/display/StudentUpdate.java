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

@WebServlet("/studentupdate")
public class StudentUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームからデータ取得
    	request.setCharacterEncoding("UTF-8"); // 追加
        String no = request.getParameter("no");

        System.out.println("StudentUpdateサーブレットで取得した学生番号: " + no);

        String name = request.getParameter("name");
        String classNum = request.getParameter("classNum");
        boolean isAttend = request.getParameter("isAttend") != null;
        int entYear = Integer.parseInt(request.getParameter("entYear"));

        System.out.println("取得した学生番号 (リクエストから): " + no);
        System.out.println("取得した学生番号 (リクエストから): " + request.getParameter("no"));

        StudentDao studentDao = new StudentDao();
        try (Connection conn = studentDao.getConnection()) {
        	System.out.println("データベース接続: " + (conn != null ? "成功" : "失敗"));
            // 既存の学生情報を取得
            Student student = studentDao.get(conn, no);

            System.out.println("サーブレットで取得した学生: " + student);
            if (student == null) {
                System.out.println("サーブレットで学生が `null` になった！");
            }

            System.out.println("データベースから取得した学生: " + student);

            System.out.println("取得した学生: " + student);

            if (student != null) {
                // 学生情報を更新
                student.setName(name);
                student.setClassNum(classNum);
                student.setAttend(isAttend);
                student.setEntYear(entYear);

                boolean result = studentDao.updateStudent(conn, student);
                if (result) {
                    request.setAttribute("message", "更新が完了しました。");
                } else {
                    request.setAttribute("message", "更新に失敗しました。");
                }
            } else {
                request.setAttribute("message", "学生が見つかりませんでした。");
            }

            request.getRequestDispatcher("/display/StudentUpdate_success.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("データベースエラー", e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}