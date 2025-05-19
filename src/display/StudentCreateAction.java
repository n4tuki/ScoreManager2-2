package display;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDao;
import bean.Student;
import tool.Action;
import tool.DBConnectionManager;

public class StudentCreateAction extends Action {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        System.out.println("[StudentCreateAction] doPost() started.");

        // **フォームデータを取得**
        String admissionYear = request.getParameter("admissionYear");
        String studentId = request.getParameter("studentId");
        String name = request.getParameter("name");
        String classNum = request.getParameter("classNum");

        System.out.println("[StudentCreateAction] Received parameters - admissionYear: " + admissionYear + ", studentId: " + studentId + ", name: " + name + ", classNum: " + classNum);

        // **パラメータの検証**
        if (admissionYear == null || studentId == null || name == null || classNum == null) {
            System.out.println("[StudentCreateAction] Missing required parameters.");
            request.setAttribute("error", "入力データが不足しています。");
            request.getRequestDispatcher("StudentCreate.jsp").forward(request, response);
            return;
        }

        // **Student オブジェクトを作成**
        Student student = new Student();

        student.setNo(studentId);
        System.out.println("[StudentDao] Creating student - NO: " + student.getNo()); //確認ログ
        student.setName(name);

        try {
            student.setEntYear(Integer.parseInt(admissionYear));
        } catch (NumberFormatException e) {
            System.err.println("[StudentCreateAction] Invalid admissionYear format: " + admissionYear);
            request.setAttribute("error", "入学年度が不正です。");
            request.getRequestDispatcher("StudentCreate.jsp").forward(request, response);
            return;
        }

        student.setClassNum(classNum);
        student.setAttend(true);

        System.out.println("[StudentCreateAction] Created student object: " + student);

        // **データベースに登録**
        try (Connection conn = DBConnectionManager.getConnection()) {
            if (conn == null) {
                System.out.println("[StudentCreateAction] Database connection failed.");
                request.setAttribute("error", "データベース接続に失敗しました。");
                request.getRequestDispatcher("StudentCreate.jsp").forward(request, response);
                return;
            }

            System.out.println("[StudentCreateAction] Database connection established.");

            StudentDao dao = new StudentDao();
            boolean success = dao.createStudent(conn, student);

            System.out.println("[StudentCreateAction] Student creation result: " + success);

            if (success) {
                response.sendRedirect("studentM.jsp");
            } else {
                System.out.println("[StudentCreateAction] Student creation failed.");
                request.setAttribute("error", "登録に失敗しました。");
                request.getRequestDispatcher("StudentCreate.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[StudentCreateAction] Error: " + e.getMessage());
            request.setAttribute("error", "データベースエラーが発生しました。");
            request.getRequestDispatcher("StudentCreate.jsp").forward(request, response);
        }
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("[StudentCreateAction] execute() method called.");

        try (Connection conn = DBConnectionManager.getConnection()) {
            if (conn == null) {
                System.out.println("[StudentCreateAction] Database connection failed.");
                return "error.jsp";
            }

            System.out.println("[StudentCreateAction] Database connection successful.");

            StudentDao dao = new StudentDao();
            Student student = new Student();

            student.setNo(request.getParameter("studentId"));
            student.setName(request.getParameter("name"));
            student.setEntYear(Integer.parseInt(request.getParameter("admissionYear")));
            student.setClassNum(request.getParameter("classNum"));
            student.setAttend(true);

            // 必要なパラメータを設定（例: name, studentId など）
            boolean success = dao.createStudent(conn, student);

            System.out.println("[StudentCreateAction] Student creation result: " + success);

            if (success) {
                return "StudentCreate_success.jsp";
            } else {
                return "StudentCreate.jsp";  // 登録失敗時の遷移先
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[StudentCreateAction] Error occurred: " + e.getMessage());
            return "error.jsp";
        }
    }
}