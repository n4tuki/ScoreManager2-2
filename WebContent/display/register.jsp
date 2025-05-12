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

        <div class="main-content">
            <h2>成績登録</h2>
            <form action="registerResult.jsp" method="post">
                <label for="studentName">学生名：</label>
                <input type="text" name="studentName" id="studentName" required><br><br>

                <label for="subject">科目：</label>
                <input type="text" name="subject" id="subject" required><br><br>

                <label for="score">点数：</label>
                <input type="number" name="score" id="score" min="0" max="100" required><br><br>

                <input type="submit" value="登録">
            </form>
        </div>
    </div>
</body>



<%@ include file="../share/footer.jsp" %>