<%--
    Created by Suvorov Alexey
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление материала</title>
</head>
<body>
    <h2>Введи</h2>
    <form action="${pageContext.request.contextPath}/addComponent" method="post">
        <input type="text" name="material" required> Материал<br>
        <input type="text" name="description" required> Описание<br>
        <button type="submit" name="action" value="ADD_M">Добавить</button>
    </form>
</body>
</html>
