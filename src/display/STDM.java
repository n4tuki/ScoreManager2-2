package display;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.StudentDao;
import bean.School;
import bean.Student;

@WebServlet("/display/stdm")
public class STDM extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(STDM.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            logger.warning("Session is null! Redirecting to login.");
            response.sendRedirect("../javacode/LoginProcess");
            return;
        }

        logger.info("Session ID: " + session.getId());

        // **School情報の取得**
        School school = (School) session.getAttribute("school");

        if (school == null) {
            logger.warning("School object is missing! Setting default.");
            school = new School();
            school.setCd("DEFAULT_SCHOOL");
            session.setAttribute("school", school);
        }

        logger.info("Final School Code: " + school.getCd());

        // **DAOの処理**
        StudentDao dao = new StudentDao();
        List<Student> list;

        try (Connection conn = dao.getConnection()) {
            if (conn == null) {
                throw new ServletException("Database connection failed.");
            }

            // **ページにアクセスした時点で `allfilter()` を呼び出す**
            list = dao.allfilter(conn);

        } catch (Exception e) {
            logger.severe("Error retrieving student list: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
            return;
        }

        request.setAttribute("studentList", list);
        request.getRequestDispatcher("studentM.jsp").forward(request, response);
    }
}