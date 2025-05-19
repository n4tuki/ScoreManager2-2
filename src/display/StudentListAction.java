package display;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.StudentDao;
import bean.School;
import bean.Student;
import tool.Action;
import tool.DBConnectionManager; // DB接続管理クラス

public class StudentListAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        School school = (School) session.getAttribute("school");

        // **Schoolがnullならデータベースから取得**
        try (Connection conn = DBConnectionManager.getConnection()) {
            if (conn == null) {
                throw new RuntimeException("Failed to establish database connection.");
            }

            if (school == null) {
                StudentDao dao = new StudentDao();
                school = dao.getSchoolByDefault(conn);
                session.setAttribute("school", school);
            }

            // **リクエストパラメータを取得 & 安全な処理**
            String entYearStr = request.getParameter("entYear");
            String classNum = request.getParameter("classNum");
            String attendStr = request.getParameter("isAttend");

            int entYear = 2025; // デフォルト値
            boolean isAttend = "true".equals(attendStr);

            try {
                if (entYearStr != null && !entYearStr.isEmpty()) {
                    entYear = Integer.parseInt(entYearStr);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid entYear format: " + entYearStr);
            }

            // **DAOの処理**
            StudentDao dao = new StudentDao();
            List<Student> filteredList = dao.filter(conn, school, entYear, classNum, isAttend);

            System.out.println("Received entYear: " + entYear);
            System.out.println("Received classNum: " + classNum);

            request.setAttribute("studentList", filteredList);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "データ取得時にエラーが発生しました。");
        }

        return "studentM.jsp"; // **その都度新しい検索結果を適用**
    }
}