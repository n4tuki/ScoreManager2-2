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
                    <h3>学生管理</h3>
                </div>
            </div>

            <div class="filter-container">


    <div id="content">

      <%-- メインコンテンツ --%>
      <div id="main-content">
        <h1>学生情報登録</h1>
        <div class="success-message">
          学生情報を登録しました。
        </div>
        <div class="button-container">
          <a href="menu.jsp" class="button back-button">戻る</a>
          <a href="studentM.jsp" class="button list-button">学生一覧</a>
        </div>
      </div>
    </div>

  </div>
<%@ include file="../share/footer.jsp" %>