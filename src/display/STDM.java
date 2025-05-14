package display;

import java.io.IOException;
import java.util.Enumeration;
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
            logger.warning("Session is null in STDM! Redirecting to login.");
            response.sendRedirect("../javacode/LoginProcess");
            return;
        }

        logger.info("Session ID in STDM: " + session.getId());

        // **セッション内のすべての属性を確認**
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attrName = attributeNames.nextElement();
            logger.info("Attribute in session: " + attrName + " = " + session.getAttribute(attrName));
        }

        // **School情報をセッションから取得**
        School school = (School) session.getAttribute("school");

        if (school == null) {
            logger.warning("School is missing! Restoring default.");

            school = new School();
            school.setCd("DEFAULT_SCHOOL");
            session.setAttribute("school", school);
        }

        logger.info("Final School Code in STDM: " + school.getCd());

        // **学生情報を取得**
        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String attendStr = request.getParameter("isAttend");

        StudentDao dao = new StudentDao();
        List<Student> list;

        if (entYearStr != null && classNum != null && attendStr != null) {
            int entYear = Integer.parseInt(entYearStr);
            boolean isAttend = Boolean.parseBoolean(attendStr);
            list = dao.filter(school, entYear, classNum, isAttend);
        } else {
            list = dao.filter(school, true);
        }

        request.setAttribute("studentList", list);
        request.getRequestDispatcher("studentM.jsp").forward(request, response);
    }
}