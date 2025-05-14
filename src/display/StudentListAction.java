package display;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDao;
import bean.School;
import bean.Student;
import tool.Action;

public class StudentListAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        StudentDao dao = new StudentDao();
        School school = (School) request.getSession().getAttribute("school");
        System.out.println("School retrieved from session: " + school);

        if (school == null) {
            throw new IllegalStateException("School object is null. Ensure it is set in the session.");
        }

        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        boolean isAttend = "true".equals(request.getParameter("isAttend"));

        List<Student> list;


        if (entYearStr != null && !entYearStr.isEmpty() &&
            classNum != null && !classNum.isEmpty()) {
            int entYear = Integer.parseInt(entYearStr);
            list = dao.filter(school, entYear, classNum, isAttend);
        } else {
            list = dao.filter(school, isAttend);
        }

        request.setAttribute("studentList", list);
        return "studentM.jsp";
    }
}
