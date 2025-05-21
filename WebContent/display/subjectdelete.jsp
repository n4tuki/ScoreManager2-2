<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.Subject" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>科目情報削除</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    html, body { height: 100vh; }
    #wrap { display: flex; flex-direction: column; width: 100%; }
    #content { flex: 1; display: flex; flex-direction: row; width: 100%; min-height: 100vh; }
    #subheader { padding: 1rem; background-color: gainsboro; border-radius: 5px; width: 100%; }
    #right { flex: 1; padding: 20px; display: flex; flex-direction: column; align-items: center; max-width: 80%; }
    .confirmation-box { padding: 15px; background-color: #f8d7da; border: 1px solid #dc3545; border-radius: 5px; text-align: center; width: 80%; }
    .btn-container { display: flex; justify-content: center; gap: 10px; margin-top: 10px; }
    .btn { padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-size: 1rem; }
    .btn-delete { background-color: #dc3545; color: white; }
    .btn-delete:hover { background-color: #c82333; }
    .btn-cancel { background-color: #007bff; color: white; text-decoration: none; padding: 10px 20px; display: inline-block; border-radius: 5px; }
    .btn-cancel:hover { background-color: #0056b3; }
</style>
</head>
<body>

<div id="wrap">
    <%@include file="../share/header.jsp" %>

    <div id="content">
        <%@include file="../share/sidebar.jsp" %>

        <div id="right">
            <div id="subheader">
                <h3>科目情報削除</h3>
            </div>

            <div class="confirmation-box">
                <%
                    String subjectName = request.getParameter("subjectName");
                    String subjectCd = request.getParameter("cd");
                %>
                <p>科目「<%= subjectName != null ? subjectName : "不明な科目" %>」を削除してもよろしいですか？</p>

                <div class="btn-container">
                    <form action="<%= request.getContextPath() %>/display/subjectdelete" method="post">
                        <input type="hidden" name="cd" value="<%= subjectCd %>">
                        <button type="submit" class="btn btn-delete">削除</button>
                    </form>
                    <a href="<%= request.getContextPath() %>/display/subjectlist.jsp" class="btn btn-cancel">キャンセル</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../share/footer.jsp" %>

</body>
</html>