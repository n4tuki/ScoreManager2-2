<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.Teacher" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<style>
    #header {
        background-color: #F8BBD0;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 1rem 2rem;
        border-radius: 6px;
        width: 100%;
        margin: 0;
        border-radius: 0;
    }

    #title {
        flex: 1;
        padding: 1rem 3rem;
        text-align: left;
        font-size: xx-large;
        display: inline-block;
    }

    #subinfo {
        display: flex;
        justify-content: flex-end;
        gap: 1rem;
        align-items: center;
    }
</style>

<div id="header">
    <div id="title">得点管理システム</div>
    <div id="subinfo">
        <%
            Teacher teacher = (Teacher) session.getAttribute("teacher"); // セッションからログインユーザー取得

            if (teacher != null) {
        %>
            <p><%= teacher.getName() %> 様</p>
            <p><a href="../javacode/LogoutServlet">ログアウト</a></p>
        <%
            } else {
        %>
            <p><a href="login-in.jsp">ログイン</a></p>
        <%
            }
        %>
    </div>
</div>