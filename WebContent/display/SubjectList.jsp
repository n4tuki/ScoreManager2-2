<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, bean.Subject" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目管理</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        html,
        body {
            height: 100vh;
        }

        #wrap {
            display: flex;
            flex-direction: column;
            width: 100%;
        }

        #content {
            flex: 1;
            display: flex;
            flex-direction: row;
            width: 100%;
            min-height: 100vh;
        }

        #subheader {
            padding: 1rem;
            background-color: gainsboro;
            border-radius: 5px;
            width: 100%;
        }

        #right {
            flex: 1;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            max-width: 80%;
        }

        .filter-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
            width: 90%;
        }

        table {
            width: 90%;
            border-collapse: collapse;
            margin-top: 15px;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f0f0f0;
        }

        .action-links {
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        .action-links form {
            display: inline-block;
        }

        button {
            padding: 6px 12px;
            border: none;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .new-entry-button {
            display: inline-block;
            padding: 8px 15px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .new-entry-button:hover {
            background-color: #1e7e34;
        }
    </style>
</head>
<body>

<div id="wrap">
    <%@include file="../share/header.jsp" %>

    <div id="content">
        <%@include file="../share/sidebar.jsp" %>

        <div id="right">
            <div id="subheader">
                <h2>科目管理</h2>
            </div>

            <div class="filter-container">
                <div>
                    <h3>科目一覧</h3>
                </div>
                <div>
                    <a href="<%= request.getContextPath() %>/display/subjectCreate.jsp" class="new-entry-button">新規登録</a>
                </div>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>科目コード</th>
                        <th>科目名</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="subject" items="${subjectList}">
                        <tr>
                            <td>${subject.cd}</td>
                            <td>${subject.name}</td>
                            <td class="action-links">
                                <form action="<%= request.getContextPath() %>/display/subjectEdit" method="get">
                                    <input type="hidden" name="cd" value="${subject.cd}">
                                    <button type="submit">変更</button>
                                </form>
                                <form action="<%= request.getContextPath() %>/display/subjectDelete" method="post">
                                    <input type="hidden" name="cd" value="${subject.cd}">
                                    <button type="submit">削除</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <c:if test="${empty subjectList}">
                <p>登録された科目がありません。</p>
            </c:if>
        </div>
    </div>
</div>

<%@ include file="../share/footer.jsp" %>

</body>
</html>