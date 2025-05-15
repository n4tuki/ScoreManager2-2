<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>学生情報登録</title>
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

    .main-container {
    display: flex;
    flex-direction: row; /* 横並びに配置 */
    width: 100%;
    min-height: 100vh; /* 画面いっぱいの高さを確保 */
}

#sidebar {
    width: 250px; /* 固定幅 */
    flex-shrink: 0; /* 縮まないように設定 */
    background-color: #f0f0f0; /* 背景を設定して視認性を向上 */
}

#right {
    flex: 1; /* 残りのスペースを占有 */
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    padding: 20px;
}

        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 600px;
            margin-top: 20px;
        }

        .form-container h2 {
            text-align: left;
            color: #333;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            color: #555;
            margin-bottom: 5px;
            font-size: 0.9em;
        }

        .form-group input[type="text"],
        .form-group select {
            width: calc(100% - 12px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 1em;
        }

        .form-actions {
            margin-top: 30px;
            text-align: left;
        }

        .form-actions button {
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s ease;
        }

        .form-actions button[type="submit"] {
            background-color: #007bff;
            color: white;
        }

        .form-actions button[type="submit"]:hover {
            background-color: #0056b3;
        }

        .form-actions button[type="button"] {
            background-color: #ccc;
            color: #333;
            margin-left: 10px;
        }

        .form-actions button[type="button"]:hover {
            background-color: #bbb;
        }

        #footer {
            background-color: whitesmoke;
            color: black;
            padding: 1rem 0;
            margin-top: auto;
            width: 100%;
            box-sizing: border-box;
            text-align: center;
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
    <%-- ヘッダーのインクルード --%>
    <%@ include file="../share/header.jsp" %>

    <div class="main-container">
        <%-- サイドバーのインクルード --%>
        <%@ include file="../share/sidebar.jsp" %>

	<div id="right">
        <div class="content">
            <div class="subheadder">
            	<div class="subtitle">
                	<h3>学生情報登録</h3>
                </div>
            </div>
                <div class="form-container">
                <form action="#" method="post">
                    <div class="form-group">
                        <label for="admissionYear">入学年度</label>
                        <select id="admissionYear" name="admissionYear">
                            <option value="">選択してください</option>
                            <option value="2023">2023</option>
                            <option value="2024">2024</option>
                            <option value="2025" selected>2025</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="studentId">学生番号</label>
                        <input type="text" id="studentId" name="studentId" placeholder="学生番号を入力してください" required>
                    </div>
                    <div class="form-group">
                        <label for="name">氏名</label>
                        <input type="text" id="name" name="name" placeholder="氏名を入力してください" required>
                    </div>
                    <div class="form-group">
                        <label for="class">クラス</label>
                        <select id="class" name="class">
                            <option value="101" selected>101</option>
                            <option value="102">102</option>
                            <option value="103">103</option>
                        </select>
                    </div>
                    <div class="form-actions">
                        <button type="submit">登録して終了</button>
                        <button type="button">戻る</button>
                    </div>
                </form>
                </div>
            </div>
        </div>
    </div>

    <%-- フッターのインクルード --%>
    <%@ include file="../share/footer.jsp" %>
</body>
</html>