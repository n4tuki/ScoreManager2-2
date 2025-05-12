<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, bean.Student" %>

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

<head>
    <title>成績管理</title>
</head>
<body>
<h2>成績管理</h2>
<form action="GradeSearchServlet" method="post">
    <table>
        <tr>
            <td>入学年度</td> <!-- ② -->
            <td>
                <select name="admissionYear"> <!-- ⑥ -->
                    <option value="">--------</option>
                    <option value="2021">2021</option>
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                </select>
            </td>

            <td>クラス</td> <!-- ③ -->
            <td>
                <select name="classNum"> <!-- ⑦ -->
                    <option value="">--------</option>
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                </select>
            </td>

            <td>科目</td> <!-- ④ -->
            <td>
                <select name="subjectCd"> <!-- ⑧ -->
                    <option value="">--------</option>
                    <option value="MATH">数学</option>
                    <option value="ENG">英語</option>
                    <option value="SCI">理科</option>
                </select>
            </td>

            <td>回数</td> <!-- ⑤ -->
            <td>
                <select name="no"> <!-- ⑨ -->
                    <option value="">--------</option>
                    <option value="1">1回目</option>
                    <option value="2">2回目</option>
                    <option value="3">3回目</option>
                </select>
            </td>

            <td>
                <input type="submit" value="検索"/> <!-- ⑩ -->
            </td>
        </tr>
    </table>
</form>
</body>
</html>



<%@ include file="../share/footer.jsp" %>