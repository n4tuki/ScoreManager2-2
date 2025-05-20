<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, bean.Student" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Score Management</title>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    html,
    body {
        height: 100vh;
    }

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

    .success-message {
            background-color: #e8f5e9; /* 薄い緑 */
            color: #388e3c; /* 濃い緑 */
            padding: 15px;
            margin-top: 20px;
            border: 1px solid #c8e6c9;
            border-radius: 5px;
            margin-bottom: 30px;

        }

        .button-container {
            margin-top: 30px;
        }

        .button {
            padding: 10px 20px;
            margin: 0 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
        }

        .back-button {
            background-color: #fbe9e7; /* 薄いオレンジ */
            color: #d84315; /* 濃いオレンジ */
        }

        .list-button {
            background-color: #e3f2fd; /* 薄い青 */
            color: #1e88e5; /* 濃い青 */
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
                    <h3>学生情報変更</h3>
                </div>
            </div>

            <div id="right">

            <div class="success-message">
                学生情報を変更しました。
            </div>
			<div class="button-container">
                <button type="button" class="button back-button" onclick="history.back()">戻る</button>
                <button type="button"  class="button list-button"onclick="location.href='/display/stdm'">学生一覧へ</button>
            </div>
        </div>
    </div>
    </div>
    </div>


<%@ include file="../share/footer.jsp" %>