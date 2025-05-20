package display;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDao;
import bean.Student;
import tool.DBConnectionManager;

@WebServlet(urlPatterns={"/display/studentcreate"})
public class StudentCreate extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        System.out.println("[StudentCreateServlet] doPost() started.");

        // **フォームデータを取得**
        String admissionYear = request.getParameter("admissionYear");
        String studentId = request.getParameter("studentId");
        String name = request.getParameter("name");
        String classNum = request.getParameter("classNum");

        System.out.println("[StudentCreateServlet] Received parameters - admissionYear: " + admissionYear + ", studentId: " + studentId + ", name: " + name + ", classNum: " + classNum);

        // **パラメータの検証**
        if (admissionYear == null || studentId == null || name == null || classNum == null) {
            System.out.println("[StudentCreateServlet] Missing required parameters.");
            request.setAttribute("error", "入力データが不足しています。");
            request.getRequestDispatcher("StudentCreate.jsp").forward(request, response);
            return;
        }

        // **Student オブジェクトを作成**
        Student student = new Student();
        student.setNo(studentId);
        student.setName(name);

        try {
            student.setEntYear(Integer.parseInt(admissionYear));
        } catch (NumberFormatException e) {
            System.err.println("[StudentCreateServlet] Invalid admissionYear format: " + admissionYear);
            request.setAttribute("error", "入学年度が不正です。");
            request.getRequestDispatcher("StudentCreate.jsp").forward(request, response);
            return;
        }

        student.setClassNum(classNum);
        student.setAttend(true);

        System.out.println("[StudentCreateServlet] Created student object: " + student);

        // **データベースに登録**
        try (Connection conn = DBConnectionManager.getConnection()) {
            if (conn == null) {
                System.out.println("[StudentCreateServlet] Database connection failed.");
                request.setAttribute("error", "データベース接続に失敗しました。");
                request.getRequestDispatcher("StudentCreate.jsp").forward(request, response);
                return;
            }

            System.out.println("[StudentCreateServlet] Database connection established.");

            StudentDao dao = new StudentDao();
            boolean success = dao.createStudent(conn, student);

            System.out.println("[StudentCreateServlet] Student creation result: " + success);

            if (success) {
                request.setAttribute("message", "登録が完了しました！");
                request.setAttribute("student", student);
                request.getRequestDispatcher("StudentCreate_success.jsp").forward(request, response);
            } else {
                System.out.println("[StudentCreateServlet] Student creation failed.");
                request.setAttribute("error", "登録に失敗しました。");
                request.getRequestDispatcher("StudentCreate.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[StudentCreateServlet] Error: " + e.getMessage());
            request.setAttribute("error", "データベースエラーが発生しました。");
            request.getRequestDispatcher("StudentCreate.jsp").forward(request, response);
        }
    }
}
