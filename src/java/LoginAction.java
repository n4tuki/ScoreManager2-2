package java;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AnotherDAO;
import bean.Teacher;
import tool.Action;

public class LoginAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

		HttpSession session=request.getSession();

		String id=request.getParameter("id");
		String password=request.getParameter("password");
		AnotherDAO dao=new AnotherDAO();
		Teacher Another=dao.teacherSearch(id, password);

		if (Another!=null) {
			session.setAttribute("teacher", Another);
			return "menu.jsp";
		}

		return "login-error.jsp";
	}
}
