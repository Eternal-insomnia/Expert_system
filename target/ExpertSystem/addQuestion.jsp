<%--
    Created by Suvorov Alexey
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление вопроса</title>
</head>
<body>
    <h2>Введи</h2>
    <form action="${pageContext.request.contextPath}/addComponent" method="post">
        <input type="number" name="id" required> ID<br>
        <input type="text" name="question" required> Вопрос<br>
        <input type="text" name="description" required> Описание<br>
        <input type="number" name="parentId" value="-1" required> ID родительского вопроса<br>
        <button type="submit" name="action" value="ADD_Q">Добавить</button>
    </form>
</body>
</html>
