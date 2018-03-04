<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="locale" var="var"/>
<fmt:setLocale value="${changeLanguage}"/>
<html>
<head>
    <title>Client cabinet</title>
</head>
<%@ include file="/jsp/common/header.jsp" %>
<body>
<strong><h1><fmt:message key="label.clientCabinet" bundle="${var}"/></h1></strong>
<a href="${pageContext.request.contextPath}/jsp/client/order.jsp"><fmt:message key="label.wantToMakeOrder"  bundle="${var}"/></a><br/>
<a href="${pageContext.request.contextPath}/jsp/client/review.jsp"><fmt:message key="label.wantToLeaveFeedback"  bundle="${var}"/></a>
<div class="container">
    <div class="sidebar">

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
    <form name="clientCabinet" method="POST" action = "${pageContext.request.contextPath}/jsp/controller">
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
<c:import url="/jsp/common/footer.jsp" />
</body>
</html>
