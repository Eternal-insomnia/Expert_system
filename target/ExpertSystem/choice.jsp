<%--
  Created by Suvorov Alexey
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Выбирайте</title>
</head>
<body>
<%-- а что если получать вопросы на сервлете из БД, а потом
массив строк вопросов циклом давать пользователю, а после каждого ответа по коэффициентам считать ответ
придумать, как переключать вопросы не меняя страницы--%>
    <h2>Вы ели детей?</h2>
    <form action="test.jsp" method="post">
        <button type="submit" name="ans" value="1">ДА</button>
        <button type="submit" name="ans" value="0">НЕТ</button>
    </form>
</body>
</html>
