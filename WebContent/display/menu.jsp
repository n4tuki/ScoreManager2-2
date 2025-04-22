<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
//test
<html>
<head>
<meta charset="UTF-8">
<title>Score Management</title>
<style>
  * { margin: 0; padding: 0; }

  html, body { height: 100%; }

  #wrap {
    min-height: 100%;
    display: flex;
    flex-direction: column;
  }

  #content {
    flex: 1;
    display: flex;
    width: 100%;
  }

  #right {
    flex: 1;
    padding: 10px;
  }

  #subheader {
    padding: 1rem;
    background-color: gainsboro;
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

  #primary {
	background-color: #b88884;
	border-radius: 10px;
	height: 40%;
	width: 25%;
	display: flex;
	margin-top: 20px;
  }

  #primary-text {
	font-size: 20px;
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 5rem;
  }

  #secondary {
	background-color: #93ff93;
	border-radius: 10px;
	height: 40%;
	width: 25%;
	display: flex;
	margin-top: 20px;
  }

  #secondary-text {
	font-size: 20px;
	display: flex;

  }


  #third {

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
        	<h3>メニュー</h3>
        </div>
      </div>
      <div id="primary">
      	<div id="primary-text">
      <a href="#">学生管理</a></div>
      </div>
      <div id="secondary">
      	<div id="secondary-text">
      	<p>成績管理</p>
      	<a href="#">成績登録</a>
      	<a href="#">成績参照</a>
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