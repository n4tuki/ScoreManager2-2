package display;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SubjectDao;
import bean.Subject;
import tool.DBConnectionManager;


@WebServlet(urlPatterns={"/display/delete"}) // サーブレットのURLマッピング
public class SubjectDelete extends HttpServlet {
//学生のデータを追加するクラス
	public void doPost(
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		// リクエストの文字エンコーディングをUTF-8に設定（日本語対応）
		request.setCharacterEncoding("UTF-8");

		String cd = request.getParameter("cd");
		Connection connection = DBConnectionManager.getConnection();

		SubjectDao sDao = new SubjectDao(connection);
		Subject subject = null ;
		try {
			subject = sDao.get(cd);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("subject", subject);
		request.getRequestDispatcher("delete.jsp").forward(request, response); // フォワード処理を追加
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request, response);
	}
}
