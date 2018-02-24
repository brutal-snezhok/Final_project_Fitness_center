<%@ page import="com.tsyrulik.dmitry.model.entity.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tsyrulik.dmitry.model.entity.Trainer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tsyrulik.dmitry.model.entity.Food" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="com.tsyrulik.dmitry.model.entity.Exercises" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="locale" var="var"/>
<html>
<head>
    <title>Client cabinet</title>
</head>
<body>
<strong><h1>Client Cabinet</h1></strong>
<div class="container">
    <div class="sidebar">

        <%Client client = new Client(2, "Pety", "Saplov", 23, "M",
                "goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "client", (long)1, (double)15, (long)2);
        session.setAttribute("client", client);%>

        <strong><fmt:message key="jsp.register.firstname" bundle="${var}"/></strong>
        <label>
            ${client.name}
        </label>

        <br/>
        <strong><fmt:message key="jsp.register.secondname" bundle="${var}" /></strong>
        <label>
            ${client.surname}
        </label>

        <br/>
        <strong><fmt:message key="jsp.register.years_old" bundle="${var}" /></strong>
        <label>
            ${client.yearOld}
        </label>

        <br/>
        <strong><fmt:message key="jsp.register.sex" bundle="${var}"/></strong>
        <label>
            ${client.sex}
        </label>

        <br/>
        <strong><fmt:message key="jsp.register.email" bundle="${var}"/></strong>
        <label>
            ${client.email}
        </label>

    </div>
    <form name="clientCabinet" method="POST" action = "/jsp/controller">
        <input type="hidden" name="command" value="client_Cabinet"/>

        <table border="1" width="70%" cellpadding="5">
            <tr>
                <th><fmt:message key="jsp.nameOfDish" bundle="${var}"/></th>
                <th><fmt:message key="jsp.dataOfReceipt" bundle="${var}"/></th>
                <th><fmt:message key="jsp.timeOfReceipt" bundle="${var}"/></th>
                <th><fmt:message key="jsp.muscleGroup" bundle="${var}"/></th>
                <th><fmt:message key="jsp.nameOfExercises" bundle="${var}"/></th>
                <th><fmt:message key="jsp.equipment" bundle="${var}"/></th>
                <th><fmt:message key="jsp.button" bundle="${var}"/></th>
            </tr>


            <%
                List<Food> foods = new ArrayList<>();
                Food food1 = new Food((long)7, "Шоколад 100 гр",  LocalDate.of(2017,9, 20), LocalTime.of(15,0,0));
                Food food2 = new Food((long)8, "Сок",  LocalDate.of(2017,4, 20), LocalTime.of(17,0,0));
                foods.add(food1);
                foods.add(food2);
                session.setAttribute("foods", foods);

                List<Exercises> exercises = new ArrayList<>();
                Exercises exercises1 = new Exercises((long) 6, "широчайшие мышцы спины",
                    "тяга верхнего блока перед собой; тяга гантели одной рукой; тяга гантели к поясу; отжимания стоя на руках; подтягивания широким хватом",
                    "гантели, турник, тренажер");
                Exercises exercises2 = new Exercises((long) 5, "икры",
                        "подъем на носки", "");
                exercises.add(exercises1);
                exercises.add(exercises2);
                session.setAttribute("exercises", exercises);
            %>

            <c:forEach items="${foods}" var="food" varStatus="loop">
                <c:forEach items="${exercises}" var="exercise" begin="${loop.index}" end="${loop.index}" >
               <tr>
                <td>${food.nameOfDish}</td>
                <td>${food.dateReceipt}</td>
                <td>${food.timeOfReceipt}</td>
                <td>${exercise.muscleGroup}</td>
                <td>${exercise.nameOfExercises}</td>
                <td>${exercise.equipment}</td>
                <td><input type="submit" value= "<fmt:message key="jsp.refusing" bundle="${var}"/>" ></td>
               </tr>
                </c:forEach>
            </c:forEach>

        </table>
    </form>
</div>
</body>
</html>
