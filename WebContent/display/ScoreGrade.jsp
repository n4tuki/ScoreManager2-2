<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, bean.Test" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
</head>
<body>
<h2>検索結果</h2>
 
    <%
        List<Test> testList = (List<Test>) request.getAttribute("testList");
 
        if (testList == null || testList.isEmpty()) {
    %>
<p>該当するデータはありません。</p>
<%
        } else {
    %>
<table border="1">
<tr>
<th>学生番号</th>
<th>科目コード</th>
<th>学校コード</th>
<th>回数</th>
<th>点数</th>
<th>クラス番号</th>
</tr>
<%
                for (Test t : testList) {
            %>
<tr>
<td><%= t.getStudentNo() %></td>
<td><%= t.getSubjectCd() %></td>
<td><%= t.getSchoolCd() %></td>
<td><%= t.getNo() %></td>
<td><%= t.getPoint() %></td>
<td><%= t.getClassNum() %></td>
</tr>
<%
                }
            %>
</table>
<%
        }
    %>
 
    <p><a href="../grade/grade.jsp">← 戻る</a></p>
</body>
</html>