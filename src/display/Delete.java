package display;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 科目削除処理を行うサーブレット
 */
@WebServlet(urlPatterns={"/delete"})
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * HTTP POSTリクエストを処理します。
	 * フォームからの削除リクエストを受け取り、科目の削除処理を行います。
	 *
	 * @param request  クライアントからのリクエスト情報を持つHttpServletRequestオブジェクト
	 * @param response クライアントへのレスポンスを送信するためのHttpServletResponseオブジェクト
	 * @throws ServletException サーブレット内で例外が発生した場合
	 * @throws IOException     入出力処理中に例外が発生した場合
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータから科目コードを取得します
		String subjectCode = request.getParameter("code");

		// 実際のアプリケーションでは、以下の処理を行います。
		// 1. データベースに接続します。
		// 2. 取得した科目コードに基づいて、DELETE SQLを実行し、該当の科目を削除します。
		// 3. データベース操作中に発生する可能性のあるエラーを処理します。

		// この例では、コンソールにメッセージを出力するだけにします。
		System.out.println("削除を試みる科目コード: " + subjectCode);

		// 削除が成功した場合（または削除処理が別の場所で行われる場合）、
		// ユーザーを成功ページや科目一覧ページにリダイレクトすることが一般的です。
		// ここでは、メニューページに戻るようにリダイレクトします。
		response.sendRedirect("menu.jsp");
	}

}