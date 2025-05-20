<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, bean.Student" %>

<%
    List<Student> studentList = (List<Student>) request.getAttribute("studentList");
    String entYear = request.getParameter("entYear");
    String classNum = request.getParameter("classNum");
    String isAttend = request.getParameter("isAttend");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Score Management</title>
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
        display: block;
        margin-left: 0;
    }

    #subtitle {
        padding: 0.5rem;
    }

    #right {
        flex: 1;
        padding: 10px;
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        max-width: none;
    }

    /* フォームコンテナ */
    .filter-container {
        display: flex;
        flex-wrap: wrap; /* 折り返しを有効にする */
        align-items: center; /* 垂直方向中央揃え */
        margin-bottom: 10px;
        gap: 10px; /* 要素間のギャップ */
    }

    /* フォーム要素のスタイル */
    .filter-container label,
    .filter-container select,
    .filter-container input[type="checkbox"] {
        margin-right: 10px;
        margin-bottom: 5px; /* 各要素の下マージン */
    }

    /* セレクトボックスのスタイル */
    .filter-container select {
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    /* チェックボックスのスタイル */
    .filter-container input[type="checkbox"] {
        margin-right: 5px;
    }

    /* 絞り込みボタンのスタイル */
    .filter-container button {
        padding: 8px 15px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .filter-container button:hover {
        background-color: #0056b3;
    }

    /* 新規登録ボタンのスタイル */
    .new-entry-button {
        display: inline-block;
        padding: 8px 15px;
        background-color: #28a745; /* 緑色 */
        color: white;
        text-decoration: none;
        border-radius: 5px;
    }

    .new-entry-button:hover {
        background-color: #1e7e34; /* 濃い緑色 */
    }

    /* 結果件数のスタイル */
    .result-count {
        text-align: left;
        margin-top: 10px;
        margin-bottom: 10px;
    }

    /* テーブルのスタイル */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 10px;
    }

    th,
    td {
        border: 1px solid #ccc;
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #f0f0f0;
    }

     body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            background-color: #f4f4f4;
        }

        #container {
            display: flex;
            flex-grow: 1;
        }

        #main-content {
            flex-grow: 1;
            padding: 20px;
        }

        h1 {
            color: #333;
            text-align: left;
            padding: 10px;
            background-color: #e0f7fa; /* 明るい水色 */
            border-bottom: 2px solid #b2ebf2;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
            display: flex;
            align-items: center;
        }

        .form-group label {
            display: inline-block;
            width: 120px;
            text-align: left;
            margin-right: 10px;
            font-weight: bold;
        }

        .form-group input[type="text"],
        .form-group select {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 250px;
        }

        .form-group input[type="checkbox"] {
            margin-right: 5px;
        }

        .button-container {
            margin-top: 20px;
            text-align: left;
        }

        .button {
            padding: 10px 20px;
            margin-right: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
        }

        .update-button {
            background-color: #4caf50; /* 緑 */
            color: white;
        }

        .cancel-button {
            background-color: #f44336; /* 赤 */
            color: white;
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
                <div id="subtitle">
                    <h3>学生情報変更</h3>
                </div>
            </div>

            <div class="filter-container">


            <% Student student = (Student) request.getAttribute("student"); %>

            <form action="<%= request.getContextPath() %>/studentupdate" method="post">
                <div class="form-group">
                    <label for="entYear">入学年度:</label>
                    <select id="entYear" name="entYear">
                        <option value="">選択してください</option>
                        <% for (int i = 2020; i <= 2025; i++) { %>
                            <option value="<%= i %>" <%= (student != null && student.getEntYear() == i) ? "selected" : "" %>> <%= i %> </option>
                        <% } %>
                    </select>
                </div>

                <div class="form-group">
					   <label for="no">学生番号:</label>
					   <span id="no"><%= (student != null) ? student.getNo() : "未登録" %></span>
					   <input type="hidden" name="no" value="<%= (student != null) ? student.getNo() : "" %>">
				</div>

                <div class="form-group">
                    <label for="name">氏名:</label>
                    <input type="text" id="name" name="name" value="<%= (student != null) ? student.getName() : "" %>">
                </div>

                <div class="form-group">
                    <label for="classNum">クラス:</label>
                    <input type="text" id="classNum" name="classNum" value="<%= (student != null) ? student.getClassNum() : "" %>">
                </div>

                <div class="form-group">
                    <label for="isAttend">在学中:</label>
                    <input type="checkbox" id="isAttend" name="isAttend" <%= (student != null && student.isAttend()) ? "checked" : "" %>> <label for="isAttend"></label>
                </div>

                <div class="button-container">
                    <button type="submit" class="button update-button">更新</button>
                    <a href="<%= request.getContextPath() %>/display/mmnu" class="button cancel-button">戻る</a>
                </div>
            </form>
        </div>
    </div>
    </div>
</div>

<%@ include file="../share/footer.jsp" %>