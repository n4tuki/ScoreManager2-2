<%@ page contentType="text/html; charset=UTF-8" %>
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
  align-items: center;
}

#box-container {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  width: 100%;
}

  #subheader {
    padding: 1rem;
    background-color: gainsboro;
    border-radius: 5px;
    display: inline-block;
    width: 92%;
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
    width: 50%;
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

<%
    if (session == null) {
        out.println("<p>Error: No active session found in menu.jsp</p>");
    } else {
        out.println("<p>Session ID in menu.jsp: " + session.getId() + "</p>");
        out.println("<p>School stored in session in menu.jsp: " + session.getAttribute("school") + "</p>");

        java.util.Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attrName = attributeNames.nextElement();
            out.println("<p>Attribute in session: " + attrName + " = " + session.getAttribute(attrName) + "</p>");
        }
    }
%>


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
  <div id="box-container">
    <div id="primary" class="box">
      <div class="box-text">
        <a href="studentM.jsp">学生管理</a>
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
</div>
  <div id="footer">
    <%@include file="../share/footer.jsp" %>
  </div>
</div>

</body>
</html>
