package tool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    try {
	        String path = request.getServletPath().substring(1);
	        System.out.println("Requested servlet path: " + path);

	        String name = path.replace(".a", "A").replace('/', '.');
	        System.out.println("Converted class name: " + name);

	        Class<?> actionClass = Class.forName(name);
	        System.out.println("Loaded action class: " + actionClass);

	        Action action = (Action) actionClass.getDeclaredConstructor().newInstance();
	        if (action == null) {
	            System.out.println("Action instance is null!");
	            throw new ServletException("Action instance is null for class: " + name);
	        }

	        System.out.println("Executing action...");
	        String url = action.execute(request, response);
	        System.out.println("Action executed, returned URL: " + url);

	        if (url == null || url.isEmpty()) {
	            System.out.println("Action returned a null or empty URL.");
	            url = "/error.jsp";  // デフォルトエラーページ
	        }

	        request.getRequestDispatcher(url).forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "予期しないエラーが発生しました。");
	        request.getRequestDispatcher("/error.jsp").forward(request, response);
	    }
	}
}
