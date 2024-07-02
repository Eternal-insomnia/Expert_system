<%--
    Created by Suvorov Alexey
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' type='text/css' href='styles/style.css'>
    <title>Главная</title>
</head>
<body>
<div class='all_center'>
    <h2>Добрый день!</h2>
    <form style="padding-top: 10px" action="${pageContext.request.contextPath}/dataList" method="post">
        <button type="submit">Список элементов</button>
    </form>
    <form style="padding-top: 10px" action="${pageContext.request.contextPath}/addQuestion" method="post">
        <button type="submit" name="action" value="QUESTIONS">Добавить вопрос</button>
    </form>
    <form style="padding-top: 10px" action="${pageContext.request.contextPath}/addMaterial" method="post">
        <button type="submit" name="action" value="MATERIALS">Добавить материал</button>
    </form>
    <form style="padding-top: 10px" action="${pageContext.request.contextPath}/showWeights" method="post">
        <button type="submit">Изменить веса</button>
    </form>
    <form style="padding-top: 10px" action="${pageContext.request.contextPath}/test" method="post">
        <button type="submit">Тестирование</button>
    </form>
</div>
</body>
</html>
