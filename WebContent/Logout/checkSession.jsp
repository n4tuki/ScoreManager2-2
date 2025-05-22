<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%

    boolean isLoggedIn = (session != null && session.getAttribute("teacher") != null);
%>
{"isLoggedIn": <%= isLoggedIn %>}