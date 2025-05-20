<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, bean.Student" %>

<%
    List<Student> studentList = (List<Student>) request.getAttribute("studentList");
    String entYear = request.getParameter("entYear");
    String classNum = request.getParameter("classNum");
    String isAttend = request.getParameter("isAttend");

    // 削除対象の科目コードを取得
    String subjectCodeToDelete = request.getParameter("cd");
    if (subjectCodeToDelete == null || subjectCodeToDelete.isEmpty()) {
        // 科目コードが渡ってきていない場合のエラー処理
        out.println("<p style='color:red;'>削除する科目が選択されていません。</p>");
        out.println("<a href='menu.jsp'>戻る</a>");
        return; // 処理を中断
    }

    // 削除対象の科目名（ここでは固定で表示していますが、本来はDBなどから取得すべきです）
    String subjectNameToDelete = "（科目名未取得）";
    if ("F02".equals(subjectCodeToDelete)) {
        subjectNameToDelete = "Javaプログラミング基礎";
    } else {
        // 他の科目コードに対応する場合はここに追加の処理を記述
        // 例：SubjectDaoなどを使って科目コードから科目名を取得する
        // SubjectDao sDao = new SubjectDao();
        // Subject subject = sDao.get(subjectCodeToDelete);
        // if (subject != null) {
        //     subjectNameToDelete = subject.getName();
        // }
        subjectNameToDelete = "科目コード：" + subjectCodeToDelete; // 一応表示
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

                <form action="delete_success.jsp" method="post">
                    <input type="hidden" name="cd" value="<%= subjectCodeToDelete %>" />
                    <button type="submit" class="btn">削除</button>
                </form>
                </div>
            </div>
        </div>
        <a href="menu.jsp">戻る</a>
    </div>
</div>

<%@ include file="../share/footer.jsp" %>
