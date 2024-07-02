<%--
    Created by Suvorov Alexey
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' type='text/css' href='styles/style.css'>
    <title>Добавление материала</title>
</head>
<body>
<div class='all_center'>
    <h2>Заполните форму</h2>
    <form action="${pageContext.request.contextPath}/addComponent" method="post">
        <input type="text" name="material" placeholder='Материал' required><br>
        <input type="text" name="description" placeholder='Описание'><br>
        <br><button type="submit" name="action" value="ADD_M">Добавить</button>
    </form>

    <form action='http://localhost:8080/ExpertSystem/' method='post'>
        <button type='submit'>На главную</button>
    </form>
</div>
</body>
</html>
