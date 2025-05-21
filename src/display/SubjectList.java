package display;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SubjectDao;
import bean.Subject;
import tool.DBConnectionManager;

@WebServlet(urlPatterns={"/display/subjectlist"})
public class SubjectList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = DBConnectionManager.getConnection();
        SubjectDao sDao = new SubjectDao(connection);

        try {
            List<Subject> subjectList = sDao.getAll(); // DAOで全科目取得
            request.setAttribute("subjectList", subjectList);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "科目一覧の取得に失敗しました");
        }

        request.getRequestDispatcher("SubjectList.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}