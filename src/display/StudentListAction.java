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

	    if (school == null) {
	        school = new School();
	        school.setCd("DEFAULT_SCHOOL");
	        request.getSession().setAttribute("school", school);
	    }

	    String entYearStr = request.getParameter("entYear");
	    String classNum = request.getParameter("classNum");
	    boolean isAttend = "true".equals(request.getParameter("isAttend"));

	    // **新しい検索を毎回実行**
	    List<Student> filteredList = dao.filter(school,
	        entYearStr != null ? Integer.parseInt(entYearStr) : 2025,
	        classNum, isAttend);

	    request.setAttribute("studentList", filteredList);

	    return "studentM.jsp"; // **その都度新しい検索結果を適用**
	}
}
