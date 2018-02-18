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
<div class="container">
    <div class="sidebar">
        <fmt:message key="jsp.register.firstname" bundle="${var}"/><br/>
        <label>
            ${client.name}
        </label>

        <br/> <fmt:message key="jsp.register.secondname" bundle="${var}" /><br/>
        <label>
            ${client.surname}
        </label>

        <br/><fmt:message key="jsp.register.years_old" bundle="${var}" /><br/>
        <label>
            ${user.yearOld}
        </label>

        <br/><fmt:message key="jsp.register.sex" bundle="${var}"/><br/>
        <label>
            ${user.sex}
        </label>

        <br/><fmt:message key="jsp.register.email" bundle="${var}"/><br/>
        <label>
            ${user.email}
        </label>

    </div>
    <form name="clientCabinet" method="POST" action = "/jsp/controller">
        <input type="hidden" name="command" value="clientCabinet"/>
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

            <c:forEach items="${food}" var="food">
            <tr>
                <td>${food.nameOfDish}</td>
                <td>${food.dataReceipt}</td>
                <td>${food.timeOfReceipt}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            </c:forEach>


            <c:forEach items="${exercises}" var="exercises">
                <td></td>
                <td></td>
                <td></td>
                <td>${exercises.muscleGroup}</td>
                <td>${exercises.namesOfExercises}</td>
                <td>${exercises.equipment}</td>
                <td><input type="submit" value= "<fmt:message key="jsp.refusing" bundle="${var}"/>" ></td>
            </c:forEach>

        </table>
    </form>
</div>
</body>
</html>
