<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, bean.Student" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>成績管理</title>
    <style>
        * {
        margin: 0; padding: 0; box-sizing: border-box;
        }

        html, body {
         height: 100%;
         }

        #wrap {
         display: flex;
         flex-direction:
          column;
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
        padding: 10px;
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        max-width: none;
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
                <h2>成績管理</h2>
                <form action="GradeSearchServlet" method="post">
                    <table>
                        <tr>
                            <td>入学年度</td>
                            <td>
                                <select name="admissionYear">
                                    <option value="">--------</option>
                                    <option value="2021">2021</option>
                                    <option value="2022">2022</option>
                                    <option value="2023">2023</option>
                                </select>
                            </td>

                            <td>クラス</td>
                            <td>
                                <select name="classNum">
                                    <option value="">--------</option>
                                    <option value="101">101</option>
                                    <option value="102">102</option>
                                    <option value="131">131</option>
                                    <option value="201">201</option>
                                    <option value="202">202</option>
                                </select>
                            </td>

                            <td>科目</td>
                            <td>
                                <select name="subjectCd">
                                    <option value="">--------</option>
                                    <option value="MATH">数学</option>
                                    <option value="ENG">英語</option>
                                    <option value="SCI">理科</option>
                                    <option value="Python1">Python1</option>
                                </select>
                            </td>

                            <td>回数</td>
                            <td>
                                <select name="no">
                                    <option value="">--------</option>
                                    <option value="1">1回目</option>
                                    <option value="2">2回目</option>
                                    <option value="3">3回目</option>
                                </select>
                            </td>

                            <td>
                                <input type="submit" value="検索"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>

            <%-- 検索結果表示エリア --%>
            <c:choose>
                <c:when test="${not empty searchResults}">
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
                                        <c:set var="scoreFound" value="false"/>
                                        <c:forEach var="grade" items="${student.grades}">
                                            <c:if test="${grade.subjectCd == param.subjectCd && grade.no == param.no}">
                                                ${grade.score}
                                                <c:set var="scoreFound" value="true"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${not scoreFound}">-</c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <c:if test="${not empty param.admissionYear}">
                        <p>該当する成績データは見つかりませんでした。</p>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <%@ include file="../share/footer.jsp" %>
</div>

</body>
</html>