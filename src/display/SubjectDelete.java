package display;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SubjectDao;
import bean.Subject;


@WebServlet(urlPatterns={"/display/delete"}) // サーブレットのURLマッピング
public class SubjectDelete extends HttpServlet {
//学生のデータを追加するクラス
	public void doPost(
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		// リクエストの文字エンコーディングをUTF-8に設定（日本語対応）
		request.setCharacterEncoding("UTF-8");

		String cd = request.getParameter("cd");

		SubjectDao sDao = new SubjectDao();
		Subject subject = sDao.get(cd);

		request.setAttribute("subject", subject);
		request.getRequestDispatcher("delete.jsp").forward(request, response); // フォワード処理を追加
	}
}