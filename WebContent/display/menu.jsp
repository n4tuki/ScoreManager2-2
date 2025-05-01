<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../share/header.jsp" %>
<!DOCTYPE html>
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
    display: flex;
    flex-direction: row; /* 横並びに変更 */
    justify-content: space-around; /* 均等に配置 */
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

  .box {
    border-radius: 10px;
    height: 40%;
    width: 30%;
    display: flex;
    margin-top: 20px;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  #primary {
    background-color: #ff3399;
  }

  #secondary {
    background-color: #93ff93;
  }

  #third {
    background-color: #9933ff;
  }

  .box-text {
    font-size: 20px;
    padding: 5rem;
    text-align: center;
  }
</style>
</head>
<body>
<div id="wrap">
  <div id="content">
    <%@include file="../share/sidebar.jsp" %>
    <div id="right">
      <div id="primary" class="box">
        <div class="box-text">
          <a href="#">学生管理</a>
        </div>
      </div>
      <div id="secondary" class="box">
        <div class="box-text">
          <p>成績管理</p>
          <a href="#">成績登録</a><br>
          <a href="#">成績参照</a>
        </div>
      </div>
      <div id="third" class="box">
        <div class="box-text">
          <a href="#">科目管理</a>
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
