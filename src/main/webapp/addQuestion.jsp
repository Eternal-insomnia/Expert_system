<%--
    Created by Suvorov Alexey
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' type='text/css' href='styles/style.css'>
    <title>Добавление вопроса</title>
</head>
<body>
<div class='all_center'>
    <h2>Заполните форму</h2>
    <form action="${pageContext.request.contextPath}/addComponent" method="post">
        <input type="number" name="id" placeholder='ID' required><br>
        <input type="text" name="question" placeholder='Вопрос' required><br>
        <input type="text" name="description" placeholder='Описание' required><br>
        <input type="number" name="parentId" value="-1" style='display:none' required><br>
        <button type="submit" name="action" value="ADD_Q">Добавить</button>
    </form>

    <form action='http://localhost:8080/ExpertSystem/' method='post'>
            <button type='submit'>На главную</button>
    </form>
</div>
</body>
</html>
