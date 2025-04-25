<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>ログアウト</title>
    <style>
        body {
            font-family: sans-serif;
            background-color: #f8f9fa; /* より薄いグレー */
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
            box-sizing: border-box;
        }
        .header {
            background-color: #e9ecef;
            color: #333;
            padding: 20px;
            width: 100%;
            text-align: left;
            box-sizing: border-box;
            margin-bottom: 40px; /* 下に少し余白を追加 */
        }
        .logout-message-container {
            background-color: #e2f7e5; /* より薄い緑 */
            color: #28a745;
            padding: 15px 20px;
            border: 1px solid #c3e6cb;
            border-radius: 4px;
            margin-bottom: 30px;
            text-align: center;
        }
        .login-link {
            color: #007bff;
            text-decoration: none;
            margin-top: 20px; /* 上に余白を追加 */
        }
        .login-link:hover {
            text-decoration: underline;
        }
        #footer {
            background-color: whitesmoke;
            color: black;
            padding: 1rem 0;
            margin-top: auto; /* 画面下部に配置 */
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
    <div id="footer">
        <div id="footer-content">
            <p>&copy; 2025 TIC </p>
            <p>大原学園</p>
        </div>
    </div>
    <%
        // 実際のアプリケーションでは、ここでセッションの破棄などのログアウト処理を行います。
        session.invalidate();
    %>
</body>
</html>