package display;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/display/main"})
public class Main extends HttpServlet {

	public void doPost (
		HttpServletRequest request,	HttpServletResponse response
	) throws ServletException, IOException {
		request.getRequestDispatcher("login-in.jsp").forward(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request, response);
	}
}