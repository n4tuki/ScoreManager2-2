package display;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/display/subjectdeletef")
public class SubjectDelete_F extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 文字エンコーディング設定

        // フォームから取得
        String cd = request.getParameter("cd");
        String subjectName = request.getParameter("subjectName");

        // セッションを取得
        HttpSession session = request.getSession();

        // セッションに科目情報を保存
        session.setAttribute("subjectCd", cd);
        session.setAttribute("subjectName", subjectName);

        // `subjectdelete.jsp` へフォワード
        request.getRequestDispatcher("/display/subjectdelete.jsp").forward(request, response);
    }
}