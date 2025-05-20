<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生情報登録完了</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            background-color: #f4f4f4;
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

#sidebar {
    width: 250px; /* 固定幅 */
    flex-shrink: 0; /* 縮まないようにする */
}

#right {
    flex: 1; /* 残りのスペースを使用 */
    padding: 10px;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    max-width: none;
}


        #main-content {
            flex-grow: 1;
            padding: 20px;
            text-align: center;
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
    <%-- ヘッダーのインクルード --%>
    <%@ include file="../share/header.jsp" %>

    <div id="wrap">
        <%-- サイドバーのインクルード (ファイル名を修正) --%>
        <div id="sidebar">
        <%@ include file="../share/sidebar.jsp" %>
        	</div>

        <%-- メインコンテンツ --%>

          <div id="right">
            <h1>学生情報変更</h1>
            <div class="success-message">
                学生情報を変更しました。
            </div>
            <div class="button-container">
                <a href="student_registration_form.jsp" class="button back-button">戻る</a>
                <a href="student_list.jsp" class="button list-button">学生一覧</a>
            </div>
        </div>
    </div>

    <%-- フッターのインクルード --%>
    <%@ include file="../share/footer.jsp" %>
</body>
</html>