<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生情報登録</title>
    <style>
        body {
            font-family: sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            box-sizing: border-box;
        }
        .container {
            display: flex;
            flex-grow: 1;
        }
        #main-content {
            flex-grow: 1;
            padding: 20px;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: inline-block;
            width: 120px; /* ラベルの幅を少し広げました */
            text-align: left;
            margin-right: 10px;
        }
        input[type="text"],
        input[type="number"],
        select {
            width: 400px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .button-group {
            text-align: center;
            margin-top: 20px;
        }
        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin: 0 10px;
        }
        .error-message {
            color: red;
            font-size: 0.9em;
            margin-top: 5px;
        }
    </style>
    <script>
        function validateForm() {
            const admissionYear = document.getElementById("admissionYear");
            const studentId = document.getElementById("studentId");
            const name = document.getElementById("name");
            let hasError = false;

            if (admissionYear.value === "") {
                document.getElementById("admissionYearError").textContent = "入学年度を選択してください。";
                hasError = true;
            } else {
                document.getElementById("admissionYearError").textContent = "";
            }

            if (studentId.value.trim() === "") {
                document.getElementById("studentIdError").textContent = "学生番号を入力してください。";
                hasError = true;
            } else {
                document.getElementById("studentIdError").textContent = "";
            }

            if (name.value.trim() === "") {
                document.getElementById("nameError").textContent = "氏名を入力してください。";
                hasError = true;
            } else {
                document.getElementById("nameError").textContent = "";
            }

            return !hasError; // エラーがあれば送信をキャンセル
        }
    </script>
</head>
<body>
    <jsp:include page="../share/header.jsp" />

    <div class="container">
        <jsp:include page="../share/sidebar.jsp" />

        <div id="main-content">
            <h1>学生情報登録</h1>

            <form action="#" method="post" onsubmit="return validateForm()">
                <div class="form-group">
                    <label for="admissionYear">入学年度</label>
                    <select id="admissionYear" name="admissionYear">
                        <option value="">----</option>
                        <option value="2023">2023</option>
                        <option value="2024">2024</option>
                        <option value="2025">2025</option>
                    </select>
                    <div id="admissionYearError" class="error-message">
                        <% if (request.getAttribute("admissionYearError") != null) { %>
                            <%= request.getAttribute("admissionYearError") %>
                        <% } %>
                    </div>
                </div>

                <div class="form-group">
                    <label for="studentId">学生番号</label>
                    <input type="text" id="studentId" name="studentId" placeholder="学生番号を入力してください" required="required">
                    <div id="studentIdError" class="error-message">
                        <% if (request.getAttribute("studentIdError") != null) { %>
                            <%= request.getAttribute("studentIdError") %>
                        <% } %>
                    </div>
                </div>

                <div class="form-group">
                    <label for="name">氏名</label>
                    <input type="text" id="name" name="name" placeholder="氏名を入力してください" required="required">
                    <div id="nameError" class="error-message">
                        <% if (request.getAttribute("nameError") != null) { %>
                            <%= request.getAttribute("nameError") %>
                        <% } %>
                    </div>
                </div>

                <div class="form-group">
                    <label for="class">クラス</label>
                    <select id="class" name="class">
                        <option value="101">101</option>
                        <option value="102">102</option>
                        <option value="201">201</option>
                    </select>
                </div>

                <div class="button-group">
                    <button type="submit">登録して終了</button>
                    <button type="button">戻る</button>
                </div>
            </form>
        </div>
    </div>

    <jsp:include page="../share/footer.jsp" />
</body>
</html>