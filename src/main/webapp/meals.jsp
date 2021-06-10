<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Meals</title>
</head>

<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<p><a href="meals?action=add">Add Meal</a></p>
<table border="1" cellspacing="0" cellpadding="8">
    <section>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>

        <c:forEach items="${list}" var="mealTo">
            <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
            <c:set var="color" scope="request" value="${mealTo.excess ? \"red\" : \"green\"}"/>
            <%
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMMM-dd HH:mm");
                String date = dtf.format(mealTo.getDateTime());
            %>
            <tr>
                <td style="color:${color};"><%=date%>
                </td>
                <td style="color:${color};">${mealTo.description}
                </td>
                <td style="color:${color};">${mealTo.calories}
                </td>
                <td><a href="meals?action=update&id=${mealTo.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${mealTo.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </section>
</table>
</body>
</html>