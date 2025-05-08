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
 * { margin: 0; padding: 0; box-sizing: border-box; }

html, body { height: 100%; }

#wrap {
  display: flex;
  flex-direction: column;
  width: 100%;
}

#content {
  flex: 1;
  display: flex;
  flex-direction: row; /* 横並びに */
  width: 100%;
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
			<form method="get" action="StudentList.action">
    <label>入学年度:
        <select name="entYear">
            <option value="">--------</option>
            <% for (int year = 2019; year <= 2025; year++) { %>
                <option value="<%= year %>" <%= year == (entYear != null ? Integer.parseInt(entYear) : -1) ? "selected" : "" %>><%= year %></option>
            <% } %>
        </select>
    </label>

    <label>クラス:
        <select name="classNum">
            <option value="">--------</option>
            <option value="201" <%= "201".equals(classNum) ? "selected" : "" %>>201</option>
            <option value="202" <%= "202".equals(classNum) ? "selected" : "" %>>202</option>
        </select>
    </label>

    <label>
        <input type="checkbox" name="isAttend" value="true" <%= "true".equals(isAttend) ? "checked" : "" %>> 在学中
    </label>

    <button type="submit">絞込み</button>
</form>

<div style="text-align: right; margin-top: 10px;">
    <a href="StudentEntry.action">新規登録</a>
</div>

<!-- 結果件数 -->
<p>検索結果：<%= studentList != null ? studentList.size() : 0 %>件</p>

<!-- 一覧表示 -->
<table>
    <thead>
        <tr>
            <th>入学年度</th>
            <th>学生番号</th>
            <th>氏名</th>
            <th>クラス</th>
            <th>在学中</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
    <% if (studentList != null) {
        for (Student s : studentList) { %>
        <tr>
            <td><%= s.getEntYear() %></td>
            <td><%= s.getNo() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getClassNum() %></td>
            <td><%= s.isAttend() ? "○" : "×" %></td>
            <td><a href="StudentEdit.action?no=<%= s.getNo() %>">変更</a></td>
        </tr>
    <%  }
    } else { %>
        <tr><td colspan="6">学生データがありません</td></tr>
    <% } %>
    </tbody>
</table>
		</div>
	</div>
</div>


<%@ include file="../share/footer.jsp" %>