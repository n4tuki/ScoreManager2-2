<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>ログインエラー</title>
    <style>
        body {
            font-family: sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .error-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 350px;
            text-align: center;
        }
        h2 {
            color: #d9534f; /* 赤系のエラー色 */
            margin-bottom: 20px;
        }
        p {
            color: #333;
            margin-bottom: 15px;
        }
        .error-message {
            color: #c0392b; /* より強調された赤 */
            font-weight: bold;
            margin-bottom: 20px;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h2>ログインエラー</h2>
        <p class="error-message">${errorMessage}</p>
        <p>ログインIDまたはパスワードが間違っています。</p>
        <p><a href="../display/login-in.jsp">ログイン画面に戻る</a></p>
    </div>
</body>
</html>