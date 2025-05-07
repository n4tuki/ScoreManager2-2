package javacode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.TeacherDAO;
import bean.Teacher;
import tool.Action;

public class LoginAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

		HttpSession session=request.getSession();

		String id=request.getParameter("id");
		String password=request.getParameter("password");
		TeacherDAO dao=new TeacherDAO();
		Teacher Another=dao.teacherSearch(id, password);

		if (Another!=null) {
			session.setAttribute("teacher", Another);
			session.setAttribute("school", Another.getSchool());
			return "menu.jsp";
		}

		return "login-error.jsp";
	}
}
