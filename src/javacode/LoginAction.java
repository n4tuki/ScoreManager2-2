package javacode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.TeacherDAO;
import bean.Teacher;
import tool.Action;

public class LoginAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(false); // 既存のセッションを使用
        if (session == null) {
            session = request.getSession(true); // セッションが存在しない場合のみ作成
        }

        response.setHeader("Set-Cookie", "JSESSIONID=" + session.getId() + "; Path=/; HttpOnly");

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        TeacherDAO dao = new TeacherDAO();
        Teacher Another = dao.teacherSearch(id, password);

        if (Another != null) {
            session.setAttribute("teacher", Another);

            // **Schoolをセット**
            if (Another.getSchool() != null) {
                System.out.println("Teacher's School object before setting session: " + Another.getSchool());
                System.out.println("School CD before setting session: " + Another.getSchool().getCd());

                session.setAttribute("school", Another.getSchool());
                System.out.println("School stored in session after login: " + session.getAttribute("school"));

                // **セッションIDをログ出力**
                System.out.println("Session ID at login: " + session.getId());

                // **保存後の確認**
                System.out.println("School stored in session after login: " + session.getAttribute("school"));

            } else {
                System.out.println("Warning: Another.getSchool() is null!");
            }

            return "menu.jsp";
        }

        return "login-error.jsp";
    }
}