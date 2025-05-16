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
<title>科目別成績一覧</title>
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
    padding: 1rem;
  }

#right {
  flex: 1;
  padding: 10px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  max-width: none;
}

#button {
	border-radius: 12px;
    background-color: dimgray;
    color: white; /* 文字を白に */
    padding: 8px 12px;
    border: none;
    cursor: pointer;
}

button:hover {
	border-radius: 6px;
    background-color: darkgray; /* ホバー時にさらに濃いグレー */
}}


.grade-search {
    display: flex;
    align-items: center; /* 垂直方向を揃える */
    gap: 10px; /* 項目同士の間隔を調整 */
    flex-wrap: wrap; /* 画面サイズが狭いときに折り返し */
}
.grade-search label,
.grade-search select,
.grade-search button {
    display: inline-block;
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
<div class="flex">
<h1>成績一覧（科目）</h1>
</div>
</div>
<div class="grade-search">
<h3>・科目情報</h3>
<label for="nyugakuNendo">入学年度：</label>
<select id="nyugakuNendo" name="nyugakuNendo">
<option value="">選択してください</option>
<option value="2023">2023年度</option>
<option value="2024">2024年度</option>
<option value="2025">2025年度</option>
</select>

                    <label for="kurasu">クラス：</label>
<select id="kurasu" name="kurasu">
<option value="">選択してください</option>
<option value="A">101</option>
<option value="B">102</option>
<option value="C">200</option>
</select>

                    <label for="kamoku">科目：</label>
<select id="kamoku" name="kamoku">
<option value="">選択してください</option>
<option value="国語">国語</option>
<option value="数学">数学</option>
<option value="英語">英語</option>
</select>
<button type="submit">検索</button>
</div>

                <hr>

                <div class="student-search">
<h3>・学生情報</h3>
<label for="gakuseiBango">学生番号：</label>
<input type="text" id="gakuseiBango" name="gakuseiBango" value="2125001">
<button type="submit">検索</button>
</div>
</div>
</div>
</div>

</body>

<%@ include file="../share/footer.jsp" %>