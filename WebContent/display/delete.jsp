<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Score Management</title>
<style>
	*{ margin:0;padding: 0;}

	html, body { height: 100%; }

	#wrap {
    min-height: 100%;
    display: flex;
    flex-direction: column;
  }

	#subtitle {
	padding: 0.5rem 0.5rem;
	}

	#subheader {
    padding: 1rem;
    background-color: gainsboro;
    border-radius: 5px;
    display: inline-block;
    width: 95%;
  }

  #right {
    flex: 1;
    padding: 10px;
  }

  #content{
  flex: 1;
  display: flex;
  width: 100%;
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
				<p>「Javaプログラミング基礎(F02)」を削除してもよろしいですか</p>

        <form action="SubjectDelete.action" method="post">
            <input type="hidden" name="code" value="F02" />
            <button type="submit" class="btn"> 削除</button>
        </form>
				</div>
			</div>
		</div>


		<a href="menu.jsp">④ 戻る</a>
	</div>
</div>



<%@ include file="../share/footer.jsp" %>