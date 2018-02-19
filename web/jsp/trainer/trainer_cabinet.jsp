<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="locale" var="var"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trainer cabinet</title>
</head>
<body>
<form name="trainerCabinet" method="POST" action = "/jsp/controller">
    <input type="hidden" name="command" value="trainerCabinet"/>
    <table border="1" width="70%" cellpadding="5">
        <tr>
            <th><fmt:message key="jsp.name" bundle="${var}"/></th>
            <th><fmt:message key="jsp.surname" bundle="${var}"/></th>
            <th><fmt:message key="jsp.yearsOld" bundle="${var}"/></th>
            <th><fmt:message key="jsp.sex" bundle="${var}"/></th>
            <th><fmt:message key="jsp.email" bundle="${var}"/></th>
            <th><fmt:message key="jsp.nameOfDish" bundle="${var}"/></th>
            <th><fmt:message key="jsp.dataOfReceipt" bundle="${var}"/></th>
            <th><fmt:message key="jsp.timeOfReceipt" bundle="${var}"/></th>
            <th><fmt:message key="jsp.muscleGroup" bundle="${var}"/></th>
            <th><fmt:message key="jsp.nameOfExercises" bundle="${var}"/></th>
            <th><fmt:message key="jsp.equipment" bundle="${var}"/></th>
        </tr>

        <c:forEach items="${clients}" var="client">
            <tr>
                <td>${clients.name}</td>
                <td>${clients.surname}</td>
                <td>${clients.yearsOld}</td>
                <td>${clients.sex}</td>
                <td>${clients.email}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </c:forEach>

        <c:forEach items="${food}" var="food">
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>${food.nameOfDish}</td>
                <td>${food.dateReceipt}</td>
                <td>${food.timeOfReceipt}</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </c:forEach>


        <c:forEach items="${exercises}" var="exercise">
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>${exercise.muscleGroup}</td>
                <td>${exercise.nameOfExercises}</td>
                <td>${exercise.equipment}</td>
            </tr>
        </c:forEach>
    </table>


</form>
</body>
</html>
