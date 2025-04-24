<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#header {
		background-color: #bcffff;
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 1rem 2rem;
		border-radius: 6px;

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
      <p><a href="login-in.jsp">ログイン</a></p>
      <p><a href="#">ログアウト</a></p>
    </div>
  </div>