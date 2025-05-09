<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>ログイン</title>
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
        .login-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 350px;
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            color: #555;
            margin-bottom: 8px;
            font-size: 0.9em;
        }
        input[type="text"],
        input[type="password"] {
            width: calc(100% - 16px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 1em;
        }
        .password-toggle {
            display: flex;
            align-items: center;
            margin-top: 10px;
        }
        .password-toggle input[type="checkbox"] {
            margin-right: 8px;
        }
        .password-toggle label {
            font-size: 0.9em;
            color: #777;
        }
        button {
            background-color: #007bff;
            color: white;
            padding: 12px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            font-size: 1em;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        function togglePasswordVisibility() {
            const passwordInput = document.getElementById('password');
            const passwordCheckbox = document.getElementById('chk_d_ps');
            if (passwordCheckbox.checked) {
                passwordInput.type = 'text';
            } else {
                passwordInput.type = 'password';
            }
        }
    </script>
</head>
<body>
    <div class="login-container">
        <h2>ログイン</h2>
        <form action="../javacode/LoginProcess" method="post">
            <div class="form-group">
                <label for="id">ID</label>
                <input type="text" id="id" name="id" size="30" value="${id}" required>
            </div>
            <div class="form-group">
                <label for="password">パスワード</label>
                <input type="password" id="password" name="password" size="30" required>
                <div class="password-toggle">
                    <input type="checkbox" id="chk_d_ps" onclick="togglePasswordVisibility()">
                    <label for="chk_d_ps">パスワードを表示</label>
                </div>
            </div>
            <button type="submit" name="login">ログイン</button>
        </form>]
    </div>
</body>
</html>