package display;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDao;
import bean.School;
import bean.Student;

@WebServlet("/display/stumanage")
public class StudentManage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 仮のSchoolオブジェクト（本来はログイン情報から取得）
            School school = new School();
            school.setCd("OHS123"); // 適宜修正

            // クエリパラメータ取得（nullでも動くように）
            String entYearStr = request.getParameter("entYear");
            String classNum = request.getParameter("classNum");
            String attendStr = request.getParameter("isAttend");

            StudentDao dao = new StudentDao();
            List<Student> list;

            if (entYearStr != null && classNum != null && attendStr != null) {
                int entYear = Integer.parseInt(entYearStr);
                boolean isAttend = Boolean.parseBoolean(attendStr);
                list = dao.filter(school, entYear, classNum, isAttend);
            } else if (attendStr != null) {
                boolean isAttend = Boolean.parseBoolean(attendStr);
                list = dao.filter(school, isAttend);
            } else {
                // デフォルト：在学中の学生を取得
                list = dao.filter(school, true);
            }

            request.setAttribute("studentList", list);
            request.getRequestDispatcher("").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "学生情報の取得に失敗しました。");
        }
    }
}
