<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>

	#left {
	width: 15%;
	border-right: 1px solid black;
	padding: 10px;
	box-sizing: border-box;
	background-color: #d1ffd1;
	}

	#side {
	margin: 2rem;
	line-height: 2;
	}

	#side a {
	text-decoration: none;
	color: #ffadff;
	}


	#side a:hover {
	color: #ff7fbf;
	}

	#side .indent {
	margin-left: 1rem;
	a
	a
	a
	a

a
a

	}

</style>


<div id="left">
      <div id="side">
        <div><a href="<%= request.getContextPath() %>/display/mmnu">メニュー</a></div>
        <div><a href="<%= request.getContextPath() %>/display/stdm">学生管理</a></div>
        <p>成績管理</p>
        <div><a href="#" class="indent">成績登録</a></div>
        <div><a href="#" class="indent">成績参照</a></div>
        <div><a href="#">科目管理</a></div>
      </div>
    </div>