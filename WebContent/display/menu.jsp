<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
<style>

  * { margin: 0; padding: 0; }

  html, body { height: 100%;}

  #wrap {

    min-height: 100%;

    display: flex;

    flex-direction: column;

    width: 100%;

  }

  #content {

    flex: 1;

    display: flex;

    width: 100%;

    background-color: #FFF0f5;

  }

  #right {

  flex: 1;

  padding: 10px;

  display: flex;

  flex-direction: column;

  align-items: center;

}

#box-container {

  display: flex;

  flex-direction: row;

  justify-content: space-between;

  width: 84%;

}

  #subheader {

    padding: 1rem;

    background-color: #DCDCDC;

    border-radius: 5px;

    display: inline-block;

    width: 95%;

  }

  #subtitle {

    padding: 0.5rem;

  }

  #footer {

    background-color: #ccc;

    text-align: center;

    padding: 10px;

    position: relative;

    bottom: 0;

    width: 100%;

  }

  .box {

    border-radius: 10px;

    height: 43%;

    width: 50%;

    display: flex;

    margin-top: 20px;

    flex-direction: column;

    justify-content: center;

    align-items: center;

    padding: 10px;

    gap: 40px;

    margin: 11px;

  }

  #ribbon {

  	position: relative;

  	background-color: #FF7FBF;

  	color: white;

  	padding: 10px 20px;

  	font-weight: bold;

  	display: inline-block;

  	}

  #primary {

    background-color: #ffffff;

  }

  #secondary {

    background-color: #ffffff;

  }

  #third {

    background-color: #ffffff;

  }

.box-text {

    font-size: 20px;

    padding: 3rem; /* 余白を減らしてスペースを確保 */

    text-align: center;

    white-space: nowrap;

}

#heart::before {

  content: "♡";

  font-size: 24px;

  color: pink;

}

</style>
</head>
<body>

<%@include file="../share/header.jsp" %>

<div id="wrap">
<div id="content">
<%@include file="../share/sidebar.jsp" %>
<div id="right">
<div id="subheader">
<div id="subtitle">
<h3>メニュー (=^・^=)♡</h3>
</div>
</div>
<div id="box-container">
<div id="primary" class="box">
<div class="box-text">
<div><a href="<%= request.getContextPath() %>/display/stdm">学生管理</a></div>
</div>
</div>
<div id="secondary" class="box">
<div class="box-text">
<h4>【成績管理】</h4>
<a href="<%= request.getContextPath() %>/display/">成績登録</a><br>
<a href="#">成績参照</a>
</div>
</div>

<div id="third" class="box">
<div class="box-text">
<a href="<%= request.getContextPath() %>/display/subjectlist" class="indent">科目管理</a>
<div class="heart"></div>
</div>
</div>
</div>
</div>
</div>
<div id="footer">
<%@include file="../share/footer.jsp" %>
</div>
</div>

</body>
</html>
