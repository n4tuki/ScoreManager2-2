<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="bean.Subject" %>
<%@ include file="../share/header.jsp" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>科目情報変更</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    html, body { height: 100%; font-family: sans-serif; }
    #wrap { display: flex; flex-direction: column; width: 100%; }
    #content { flex: 1; display: flex; flex-direction: row; width: 100%; }
    #subheader { padding: 1rem; background-color: gainsboro; border-radius: 5px; width: 100%; }
    #right { flex: 1; padding: 10px; display: flex; flex-direction: column; align-items: flex-start; max-width: 600px; }
    form { margin-top: 2rem; width: 100%; }
    label { display: block; margin-top: 1rem; font-weight: bold; }
    input[type="text"] { width: 100%; padding: 0.5rem; margin-top: 0.3rem; font-size: 1rem; border: 1px solid #ccc; border-radius: 4px; }
    input[type="submit"] { margin-top: 1.5rem; background-color: #007bff; color: white; border: none; padding: 0.5rem 1.5rem; border-radius: 5px; cursor: pointer; font-size: 1rem; }
    input[type="submit"]:hover { background-color: #0056b3; }
    .back-link { display: block; margin-top: 1rem; color: #007bff; text-decoration: underline; font-size: 0.95rem; }
    .back-link:hover { color: #0056b3; }
    .error { color: red; font-size: 0.9rem; margin-top: 0.3rem; }
    .success { color: green; font-size: 0.9rem; margin-top: 0.3rem; }
</style>
</head>
<body>

<%
    Subject subject = (Subject) session.getAttribute("subject");
    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");

    if (subject == null) {
        subject = new Subject();
    }
%>

<div id="wrap">
    <div id="content">
        <%@ include file="../share/sidebar.jsp" %>

        <div id="right">
            <div id="subheader">
                <h3>科目情報変更</h3>
            </div>

            <% if (successMessage != null) { %>
                <div class="success"><%= successMessage %></div>
            <% } %>

            <% if (errorMessage != null) { %>
                <div class="error"><%= errorMessage %></div>
            <% } %>

            <form action="<%= request.getContextPath() %>/display/subject_change" method="post">
                <label for="code">科目コード</label>
                <input type="text" id="code" name="code"
                       value="<%= subject.getCd() != null ? subject.getCd() : "" %>"
                       readonly>

                <label for="name">科目名</label>
                <input type="text" id="name" name="name" maxlength="20"
                       placeholder="科目名を入力してください"
                       value="<%= subject.getName() != null ? subject.getName() : "" %>" required>

                <input type="submit" value="変更">
                <a href="subject_list.jsp" class="back-link">戻る</a>
            </form>
        </div>
    </div>
</div>

<%@ include file="../share/footer.jsp" %>

</body>
</html>