<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, bean.Student" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成績参照</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    html, body { height: 100%; font-family: sans-serif; }

    #wrap {
        display: flex;
        flex-direction: column;
        min-height: 100vh;
        width: 100%;
    }

    #content {
        flex: 1;
        display: flex;
        width: 100%;
    }

    #right {
        flex: 1;
        padding: 10px;
        display: flex;
        flex-direction: column;
        align-items: flex-start;
    }

    #subheader {
        padding: 1rem;
        background-color: gainsboro;
        border-radius: 5px;
        width: 100%;
    }

    table {
        margin-top: 1rem;
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        padding: 0.5rem;
        border: 1px solid #ccc;
        text-align: center;
    }

    th { background-color: #f0f0f0; }

    tr:nth-child(even) { background-color: #f9f9f9; }
    tr:hover { background-color: #f1f1f1; }

    .form-inline {
        display: flex;
        flex-wrap: wrap;
        gap: 10px;
        align-items: center;
        margin-top: 1rem;
    }

    .form-inline select,
    .form-inline input[type="text"] {
        padding: 0.3rem;
    }

    .form-inline input[type="submit"] {
        padding: 0.3rem 0.8rem;
        background-color: #007acc;
        border: none;
        color: white;
        border-radius: 4px;
        cursor: pointer;
    }

    .form-inline input[type="submit"]:hover {
        background-color: #005fa3;
    }

    .note {
        margin-top: 1rem;
        color: #007acc;
    }

    h2, h3 {
        margin-top: 0.5rem;
    }

    p {
        margin-top: 1rem;
    }

    footer {
        background-color: #f1f1f1;
        padding: 10px;
        text-align: center;
    }
</style>
</head>
<body>
<div id="wrap">
<%@ include file="../share/header.jsp" %>

<div id="content">
<%@ include file="../share/sidebar.jsp" %>

<div id="right">
    <div id="subheader">
        <h2>成績参照</h2>
        <form action="ScoreReference.action" method="post">
            <div class="form-inline">
                <label>入学年度</label>
                <select name="admissionYear">
                    <option value="">--------</option>
                    <option value="2021">2021</option>
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                </select>

                <label>クラス</label>
                <select name="classNum">
                    <option value="">--------</option>
                    <option value="101">101</option>
                    <option value="102">102</option>
                    <option value="001">001</option>
                    <option value="201">201</option>
                    <option value="202">202</option>
                </select>

                <label>科目</label>
                <select name="subjectCd">
                    <option value="">--------</option>
                    <option value="MATH">数学</option>
                    <option value="ENG">英語</option>
                    <option value="SCI">理科</option>
                    <option value="Python1">Python1</option>
                </select>

                <input type="submit" value="検索">
            </div>

            <div class="form-inline">
                <label>学生番号</label>
                <input type="text" name="studentId" placeholder="学生番号を入力してください">
                <input type="submit" value="検索">
            </div>

            <p class="note">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
        </form>
    </div>

    <c:if test="${not empty searchResults}">
        <h3>検索結果</h3>
        <table>
            <thead>
                <tr>
                    <th>入学年度</th>
                    <th>クラス</th>
                    <th>学生番号</th>
                    <th>氏名</th>
                    <th>点数</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${searchResults}">
                    <tr>
                        <td>${student.admissionYear}</td>
                        <td>${student.classNum}</td>
                        <td>${student.studentId}</td>
                        <td>${student.name}</td>
                        <td>
                            <c:forEach var="grade" items="${student.grades}">
                                <c:if test="${grade.subjectCd == param.subjectCd}">
                                    ${grade.score}
                                </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty searchResults && not empty param.admissionYear}">
        <p>該当する成績データは見つかりませんでした。</p>
    </c:if>
</div>
</div>

<%@ include file="../share/footer.jsp" %>
</div>
</body>
</html>
