<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--<jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo" scope="request"/>--%>
    <title>Edit</title>
</head>

<body>
<form action="meals" method="post">
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Edit meals</h2>

    <input type="hidden" name="id" value=${mealTo.id}>
    <p>DateTime: <input type="datetime-local" name="date" value=${mealTo.dateTime}></p>
    <p>Description: <input name="description" value=${mealTo.description}></p>
    <p>Calories: <input name="calories" value="${mealTo.calories}"></p>

    <p>
        <button type="submit">Save</button>
        <button type="button" onclick="window.history.back()">Cancel</button>
    </p>
</form>
</body>
</html>