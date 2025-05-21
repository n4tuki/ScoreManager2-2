<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../share/header.jsp" %>

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
	    flex-direction: row;
	    width: 100%;
	    min-height: 100vh;
	}

    #subheader {
        padding: 1rem;
        background-color: gainsboro;
        border-radius: 5px;
        width: 100%;
        display: block;
        margin-left: 0;
    }

    /* #subtitle を下のコードのスタイルに差し替え */
    #subtitle {
        padding: 1rem;
    }

    #right {
        flex: 1;
        padding: 10px;
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        max-width: none;
    }

    /* #button を下のコードのスタイルに追加 */
    #button {
        border-radius: 12px;
        background-color: dimgray;
        color: white; /* 文字を白に */
        padding: 8px 12px;
        border: none;
        cursor: pointer;
    }

    button:hover {
        border-radius: 6px;
        background-color: darkgray;
    }

    .grade-search {
        display: flex;
        align-items: center; /* 垂直方向を揃える */
        gap: 10px; /* 項目同士の間隔を調整 */
        flex-wrap: wrap; /* 画面サイズが狭いときに折り返し */
    }
    .grade-search label,
    .grade-search select,
    .grade-search button {
        display: inline-block;
    }

    /* 登録メッセージだけ中央寄せ */
    .message-box p {
        text-align: center;
    }
</style>
</head>
<body>

<div id="wrap">

<div id="content">
<%@ include file="../share/sidebar.jsp" %>

    <div id="right">
<div id="subheader">
<div id="subtitle">
<h3>科目情報登録</h3>
</div>
</div>

        <div class="message-box">
<p>登録に失敗しました</p>
<br>

<a href="">戻る</a>

<a href="SubjectListAction.java">科目一覧</a>
</div>
</div>
</div>

</div>

<%@ include file="../share/footer.jsp" %>

</body>
</html>