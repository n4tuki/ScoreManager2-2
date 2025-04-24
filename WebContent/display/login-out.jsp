<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>ログアウト</title>
    <style>
        body {
            font-family: sans-serif;
            background-color: #f8f9fa; /* 薄いグレーの背景 */
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
            box-sizing: border-box;
        }
        .header {
            background-color: #e9ecef; /* ヘッダーの背景色 */
            color: #333;
            padding: 20px;
            width: 100%;
            text-align: left;
            box-sizing: border-box;
            margin-bottom: 30px; /* 下の余白 */
        }
        .logout-title-container {
            background-color: #f0f0f0; /* より薄い灰色 */
            color: #333;
            padding: 15px 20px;
            border-radius: 4px;
            margin-bottom: 10px; /* 下の余白 */
            text-align: left; /* テキストを左寄せ */
            width: 80%; /* 左右に伸ばす */
            box-sizing: border-box;
        }
        .logout-title {
            font-weight: bold; /* テキストを太字に */
            margin: 0; /* デフォルトのマージンをリセット */
        }
        .logout-message-container {
            background-color: #e6f7e9; /* より薄い緑色 */
            color: #155724;
            padding: 15px 20px;
            border: 1px solid #c3e6cb;
            border-radius: 4px;
            margin-bottom: 20px; /* 下の余白 */
            text-align: center;
            width: 80%; /* 左右に伸ばす */
            box-sizing: border-box;
        }
        .login-link-container {
            margin-top: 20px; /* 上の余白 */
            width: 80%; /* 幅をログアウトメッセージと揃える */
            text-align: left; /* テキストを左寄せ */
        }
        .login-link {
            color: #007bff;
            text-decoration: none;
        }
        .login-link:hover {
            text-decoration: underline;
        }
        #footer {
            background-color: whitesmoke;
            color: black;
            padding: 1rem 0;
            margin-top: auto; /* 最下部に配置 */
            width: 100%;
            box-sizing: border-box;
        }
        #footer-content {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 0.5px;
            letter-spacing: -0.5px;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>得点管理システム</h1>
    </div>
    <div class="logout-title-container">
        <p class="logout-title">ログアウト</p>
    </div>
    <div class="logout-message-container">
        <p>ログアウトしました</p>
    </div>
    <div class="login-link-container">
        <a href="login-in.jsp" class="login-link">ログイン</a>
    </div>
    <div id="footer">
        <div id="footer-content">
            <p>&copy; 2023 TIC 大原学園 </p>
        </div>
    </div>
    <%
        // 実際のアプリケーションでは、ここでセッションの破棄などのログアウト処理を行います。
        session.invalidate();
    %>
</body>
</html>