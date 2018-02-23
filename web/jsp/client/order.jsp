<%@ page import="com.tsyrulik.dmitry.model.entity.Client" %>
<%@ page import="com.tsyrulik.dmitry.model.entity.Trainer" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="var"/>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Order</title>
</head>
<body>
<h1>Order page</h1>
<form name="orderPage" method="POST" action="/jsp/controller">
    <input type="hidden" name="command" value="order"/>
    <h3><fmt:message key="jsp.listOfTrainers"  bundle="${var}"/></h3>
    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th><fmt:message key="jsp.select" bundle="${var}"/></th>
            <th><fmt:message key="jsp.id" bundle="${var}"/></th>
            <th><fmt:message key="jsp.name" bundle="${var}"/></th>
            <th><fmt:message key="jsp.surname" bundle="${var}"/></th>
            <th><fmt:message key="jsp.yearsOld" bundle="${var}"/></th>
            <th><fmt:message key="jsp.sex" bundle="${var}"/></th>
            <th><fmt:message key="jsp.email" bundle="${var}"/></th>
            <th><fmt:message key="jsp.education" bundle="${var}"/></th>
            <th><fmr:message key="jsp.costPerLesson" bundle="${var}"/></th>
        </tr>
        <%
            Client client = new Client(8,"Евгений", "Соболев", 33, "M",
                    "sobolev@gamil.com", "b25ef06be3b6948c0bc431da46c2c738", "client", (long)7, 0.0,(long) 8);
            request.getSession().setAttribute("client", client);
            List<Trainer> trainers = new ArrayList<>();
            trainers.add(new Trainer(5, "Алексей", "Артемьев", 24, "M",
                    "artemiev@yandex.ru", "87e897e3b54a405da144968b2ca19b45", "trainer", 2,"мсмк", new BigDecimal(12), 4 ));

            trainers.add(new Trainer(9, "Федор", "Макаров", 27, "M",
                    "makarov@gmail.com", "5d69dd95ac183c9643780ed7027d128a", "trainer", 1, "кмс", new BigDecimal(10), 9));
            //18	Станислав	Федотов	32	М	fedotov@yandex.ru	80b8bdceb474b5127b6aca386bb8ce14	2
            trainers.add(new Trainer(18, "Станислав", "Федотов", 32, "M",
                    "fedotov@yandex.ru", "80b8bdceb474b5127b6aca386bb8ce14", "trainer", 10, "мсмк", new BigDecimal(25), 18));
            session.setAttribute("trainers", trainers);%>

        <c:forEach items="${trainers}" var="trainers">
            <tr>
                <td><input type="radio" name="selectTrainer" value="${trainers.email}" id="idTrainer"/></td>
                <td>${trainers.idTrainer}</td>
                <td>${trainers.name}</td>
                <td>${trainers.surname}</td>
                <td>${trainers.yearOld}</td>
                <td>${trainers.sex}</td>
                <td>${trainers.email}</td>
                <td>${trainers.education}</td>
                <td>${trainers.costPerHour}</td>
            </tr>
        </c:forEach>
    </table>

    <h3><fmt:message key="jsp.typeOfTraining" bundle="${var}"/></h3>
    <select name="listOfModes">
        <option value="norm" selected><fmt:message key="jsp.typeOfTraining.normal"  bundle="${var}"/></option>
        <option value="sport"><fmt:message key="jsp.typeOfTraining.sport" bundle="${var}"/></option>
        <option value="sparing"><fmt:message key="jsp.typeOfTraining.sparing" bundle="${var}"/></option>
    </select>
    <br/><br/>

    <strong><fmt:message key = "jsp.numberOfTraining" bundle="${var}"/></strong><br/>
    <label>
        <input type="number" value="1" name="countOfTraining">
    </label>

    <br/><input type="submit" value="Make Order" name="makeOrder">


</form>

</body>
</html>
