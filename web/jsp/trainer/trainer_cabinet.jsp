<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="locale" var="var"/>
<fmt:setLocale value="${changeLanguage}"/>
<html>
<head>
    <title>Trainer cabinet</title>
    <%@ include file="/jsp/common/header.jsp" %>
</head>
<body>
<form name="trainerCabinet" method="POST" action = "${pageContext.request.contextPath}/jsp/controller">
    <input type="hidden" name="command" value="trainer_cabinet"/>
    <h1><fmt:message key="label.trainerCabinet" bundle="${var}"/></h1>
    <table border="1" width="70%" cellpadding="5">
        <tr>
            <th><fmt:message key="jsp.select" bundle="${var}"/></th>
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

        <c:forEach items="${clientInfList}" var="clientInfList" varStatus="loop">
            <tr>
                <td><input type="checkbox" name="selectClient" value="${clientInfList.client.email}" id=""/></td>
                <td>${clientInfList.client.name}</td>
                <td>${clientInfList.client.surname}</td>
                <td>${clientInfList.client.yearOld}</td>
                <td>${clientInfList.client.sex}</td>
                <td>${clientInfList.client.email}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>

            </tr>
                <c:forEach items="${clientInfList.food}" var="food" varStatus="loop">
                    <tr>
                    <td></td>
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
                 <c:forEach items="${clientInfList.exercises}" var="exercises" varStatus="loop">
                 <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>${exercises.muscleGroup}</td>
                    <td>${exercises.nameOfExercises}</td>
                    <td>${exercises.equipment}</td>
                 </tr>
                </c:forEach>

            </c:forEach>
    </table>


    <strong><fmt:message key="jsp.nameOfDish" bundle="${var}"/></strong>
    <label>
       <input type="text" name="nameOfDish" value=""/>
    </label>
    <strong><fmt:message key = "jsp.dataOfReceipt" bundle="${var}"/></strong>
    <label>
        <input type="text" name="dataOfReceipt" value=""/>
    </label>
    <strong><fmt:message key = "jsp.timeOfReceipt" bundle="${var}"/></strong>
    <label>
        <input type="text" name="timeOfReceipt" value=""/>
    </label>
    <label>
        <input type="submit" name="actionFood" value="Add food">
    </label>
    <label>
        <input type="submit" name="actionFood" value="Delete food">
    </label>
    <label>
        <input type="submit" name="actionFood" value="Update food">
    </label>
    <br/><br/>

    <strong> <fmt:message key="jsp.muscleGroup" bundle="${var}"/></strong>
    <label>
       <input type="text" name="muscleGroup" value=""/>
    </label>
    <strong><fmt:message key="jsp.nameOfExercises" bundle="${var}"/></strong>
    <label>
        <input type="text" name="nameOfExercises" value=""/>
    </label>
    <strong><fmt:message key="jsp.equipment" bundle="${var}"/></strong>
    <label>
       <input type="text" name="equipment" value=""/>
    </label>
    <label>
        <input type="submit" name="actionExercise" value="Add exercises">
    </label>
    <label>
        <input type="submit" name="actionExercise" value="Delete exercises">
    </label>
    <label>
        <input type="submit" name="actionExercise" value="Update exercises">
    </label>
</form>

</body>
</html>
