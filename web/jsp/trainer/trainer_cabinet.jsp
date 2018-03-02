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
    <%@ include file="/jsp/common/header.jsp" %>
</head>
<body>
<form name="trainerCabinet" method="POST" action = "/jsp/controller">
    <input type="hidden" name="command" value="trainer_cabinet"/>
    <h1>Trainer cabinet</h1>
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

        <%--<%--%>
            <%--List<Client> clients = new ArrayList<>();--%>
            <%--Client client1 = new Client(2, "Pety", "Saplov", 23, "M",--%>
                    <%--"goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "client", (long)1, (double)15, (long)2);--%>
<%--//            clients.add(new Client(3, "Danila", "Letov", 21, "M",--%>
<%--//                    "letov@gmail.com", "6982e45352af5526754d83df2d1635", "client",(long)2, 0.65, (long)4));--%>

            <%--Client client2 = new Client(4,"Данила", "Куликов", 42, "M",--%>
                    <%--"kulikov42@gmail.com", "34cc93ece0ba9e3f6f235d4af979b16c", "client", (long)3, 30.0,(long) 4);--%>

            <%--List<Food> foods1 = new ArrayList<>();--%>
            <%--List<Food> foods2 = new ArrayList<>();--%>
            <%--Food food1 = new Food((long)7, "Шоколад 100 гр",  LocalDate.of(2017,9, 20), LocalTime.of(15,0,0,0));--%>
            <%--Food food2 = new Food((long)8, "Сок",  LocalDate.of(2017,4, 20), LocalTime.of(17,0,0,0));--%>
            <%--foods1.add(food1);--%>
            <%--foods1.add(food2);--%>
            <%--foods2.add(food1);--%>
            <%--List<Exercises> exercisess1 = new ArrayList<>();--%>
            <%--List<Exercises> exercisess2 = new ArrayList<>();--%>
            <%--Exercises exercises1 = new Exercises((long) 6, "широчайшие мышцы спины",--%>
                    <%--"тяга верхнего блока перед собой; тяга гантели одной рукой; тяга гантели к поясу; отжимания стоя на руках; подтягивания широким хватом",--%>
                    <%--"гантели, турник, тренажер");--%>
            <%--Exercises exercises2 = new Exercises((long) 5, "икры",--%>
                    <%--"подъем на носки", "");--%>
            <%--exercisess1.add(exercises1);--%>
            <%--exercisess1.add(exercises2);--%>
            <%--exercisess2.add(exercises1);--%>


            <%--List<ClientInf> clientInfList = new ArrayList<>();--%>
            <%--ClientInf clientInf1 = new ClientInf(client1, foods1, exercisess1);--%>
            <%--ClientInf clientInf2 = new ClientInf(client2, foods2, exercisess2);--%>
            <%--clientInfList.add(clientInf1);--%>
            <%--clientInfList.add(clientInf2);--%>


            <%--session.setAttribute("clientInfList", clientInfList);--%>
        <%--%>--%>

        <c:forEach items="${clientInfList}" var="clientInfList" varStatus="loop">
            <tr>
                <td><input type="checkbox" name="selectClient" value="${clientInfList.client.email}" id=""/></td>
                <td>${clientInfList.client.name}</td>
                <td>${clientInfList.client.surname}</td>
                <td>${clientInfList.client.yearOld}</td>
                <td>${clientInfList.client.sex}</td>
                <td>${clientInfList.client.email}</td>
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
               </tr>
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
        <input type="submit" name="actionFood" value="Add Food">
    </label>
    <label>
        <input type="submit" name="actionFood" value="Delete Food">
    </label>
    <label>
        <input type="submit" name="actionFood" value="Update Food">
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
        <input type="submit" name="actionExercise" value="Add Exercise">
    </label>
    <label>
        <input type="submit" name="actionExercise" value="Delete Exercise">
    </label>
    <label>
        <input type="submit" name="actionExercise" value="Update Exercise">
    </label>
</form>

</body>
</html>
