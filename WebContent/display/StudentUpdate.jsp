<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生情報変更</title>
    <style>
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
    <%-- ヘッダーのインクルード --%>
    <%@ include file="../share/header.jsp" %>

    <div id="container">
        <%-- サイドバーのインクルード --%>
        <%@ include file="../share/sidebar.jsp" %>

        <%-- メインコンテンツ --%>
        <div id="main-content">
            <h1>学生情報変更</h1>

            <% Student student = (Student) request.getAttribute("student"); %>

            <form action="process_update.jsp" method="post">
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
                    <input type="text" id="no" name="no" value="<%= (student != null) ? student.getNo() : "" %>" readonly>
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

    <%-- フッターのインクルード --%>
    <%@ include file="../share/footer.jsp" %>
</body>
</html>