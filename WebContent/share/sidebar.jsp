<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
#left {
    width: 15%;
    border-right: 1px solid black;
    padding: 10px;
    box-sizing: border-box;
    background-color: #FFFFF;
}

#side {
    display: flex;
    flex-direction: column;
    align-items: flex-start; /* 左寄せにする */
    justify-content: flex-start; /* 上寄せ */
    margin: 0;
    padding: 15px;
    line-height: 2;
}

#side a {
    text-decoration: none;
    color: #000000;
    writing-mode: horizontal-tb; /* 明示的に横書きを指定 */
}

#side a:hover {
    color: #333333;
}

#side .indent {
    margin-left: 1rem;
    writing-mode: horizontal-tb; /* 明示的に横書きを指定 */
}
</style>


<div id="left">
<div id="side">
<div><a href="<%= request.getContextPath() %>/display/mmnu">メニュー</a></div>
<div><a href="<%= request.getContextPath() %>/display/stdm">学生管理</a></div>
<div><a href="<%= request.getContextPath() %>/display/ScoreList">・成績管理</a></div>
<div><a href="#" class="indent">[成績登録]</a></div>
<div><a href="#" class="indent">[成績参照]</a></div>
<div><a href="<%= request.getContextPath() %>/display/subjectlist">科目管理</a></div>
</div>
</div>