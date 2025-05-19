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

	        // **フォームデータを取得**
	        String admissionYear = request.getParameter("admissionYear");
	        String studentId = request.getParameter("studentId");
	        String name = request.getParameter("name");
	        String classNum = request.getParameter("class");

	        // **Student オブジェクトを作成**
	        Student student = new Student();
	        student.setNo(studentId);
	        student.setName(name);
	        student.setEntYear(Integer.parseInt(admissionYear));
	        student.setClassNum(classNum);
	        student.setAttend(true);

	        new DBConnectionManager();
			// **データベースに登録**
	        try (Connection conn = DBConnectionManager.getConnection()) {
	            StudentDao dao = new StudentDao();
	            boolean success = dao.createStudent(conn, student);

	            if (success) {
	                response.sendRedirect("studentM.jsp");
	            } else {
	                request.setAttribute("error", "登録に失敗しました。");
	                request.getRequestDispatcher("StudentCreate.jsp").forward(request, response);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "データベースエラーが発生しました。");
	            request.getRequestDispatcher("StudentCreate.jsp").forward(request, response);
	        }
	    }

		@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}
	}
