<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, bean.Student, DAO.SubjectDao, bean.Subject" %>

<%
    List<Student> studentList = (List<Student>) request.getAttribute("studentList");
    String entYear = request.getParameter("entYear");
    String classNum = request.getParameter("classNum");
    String isAttend = request.getParameter("isAttend");

    String subjectCodeToDelete = request.getParameter("cd");
    String subjectNameToDelete = "（科目名未取得）";

    if (subjectCodeToDelete == null || subjectCodeToDelete.isEmpty()) {
        request.setAttribute("errorMessage", "削除する科目が選択されていません。");
        request.getRequestDispatcher("delete_error.jsp").forward(request, response);
        return;
    }

    try {
        SubjectDao sDao = new SubjectDao(null);
        Subject subject = sDao.get(subjectCodeToDelete);
        if (subject != null) {
            subjectNameToDelete = subject.getName();
        } else {
            request.setAttribute("errorMessage", "指定された科目は存在しません。");
            request.getRequestDispatcher("delete_error.jsp").forward(request, response);
            return;
        }
    } catch (Exception e) {
        e.printStackTrace(); // エラーログを出力
        request.setAttribute("errorMessage", "科目情報の取得中にエラーが発生しました。");
        request.getRequestDispatcher("delete_error.jsp").forward(request, response);
        return;
    }
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

.btn {
    padding: 0.5rem 1rem;
    background-color: #dc3545;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.btn:hover {
    background-color: #c82333;
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
                <h3>科目情報削除</h3>
                <p>「<%= subjectNameToDelete %>」を削除してもよろしいですか</p>

                <form action="delete_execute.jsp" method="post">
                    <input type="hidden" name="cd" value="<%= subjectCodeToDelete %>" />
                    <button type="submit" class="btn">削除</button>
                </form>
                </div>
            </div>
        </div>
        <a href="menu.jsp">戻る</a>
    </div>
</div>
</body>
<%@ include file="../share/footer.jsp" %>