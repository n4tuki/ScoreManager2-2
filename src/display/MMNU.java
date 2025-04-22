package display;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/display/mmnu"})
public class MMNU extends HttpServlet {

	public void doGet (
		HttpServletRequest request,	HttpServletResponse response
	) throws ServletException, IOException {
		request.getRequestDispatcher("menu.jsp").forward(request, response);
	}
}