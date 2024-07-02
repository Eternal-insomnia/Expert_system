<%--
    Created by Suvorov Alexey
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная</title>
</head>
<body>
    <h2>Добрый день!</h2>
    <form action="${pageContext.request.contextPath}/main">
        <button type="submit">Список вопросов</button>
    </form>
    <form action="${pageContext.request.contextPath}/addQuestion" method="post">
        <button type="submit" name="action" value="QUESTIONS">Добавить вопрос</button>
    </form>
    <form action="${pageContext.request.contextPath}/addMaterial" method="post">
        <button type="submit" name="action" value="MATERIALS">Добавить материал</button>
    </form>
</body>
</html>
