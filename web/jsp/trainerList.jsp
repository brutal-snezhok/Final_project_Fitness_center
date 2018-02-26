<%@ page import="com.tsyrulik.dmitry.model.entity.Trainer" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="locale" var="var"/>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>List of trainers</title>
    <%@ include file="common/header.jsp" %>
</head>
<body>
    <% Trainer trainer1 = new Trainer(3, "Kosty", "Pyshyk", 35, "M",
            "pyshhyk@gmail.com", "6982e82c0b21af5526754d83df2d1635", "trainer", 1, "кмс", new BigDecimal(10), 3);
    Trainer trainer2 = new Trainer(3, "KostyKosty", "PyshykPyshyk", 53, "F",
                "pyshhyk@gmail.com", "6982e82c0b21af5526754d83df2d1635", "2", 1, "кмс", new BigDecimal(10), 3);
    Trainer trainer3 =  new Trainer(4, "Kirill", "Pavlov", 23, "M",
            "vavl@gmail.com", "58bad6b697dff48f4927941962f23e90", "2", 2,"мс", new BigDecimal(20), 4);
        List<Trainer> trainers = new ArrayList<>();
        trainers.add(trainer1);
        trainers.add(trainer2);
        trainers.add(trainer3);
        session.setAttribute("trainers", trainers);
    %>
    <h3>List of trainers: </h3>
    <table style="text-align: center" border="1" width="80%" cellpadding="6">
        <tr>
            <th text-align="center"><fmt:message key="jsp.name" bundle="${var}"/></th>
            <th align="center"><fmt:message key="jsp.surname" bundle="${var}"/></th>
            <th align="center"><fmt:message key="jsp.yearsOld" bundle="${var}"/></th>
            <th align="center"><fmt:message key="jsp.sex" bundle="${var}"/></th>
            <th align="center"><fmt:message key="jsp.education" bundle="${var}"/></th>
            <th align="center"><fmt:message key="jsp.costPerLesson" bundle="${var}"/></th>
            <th align="center"><fmt:message key="label.Photo" bundle="${var}"/></th>
        </tr>
        <c:forEach items="${trainers}" var="trainers">
            <tr>
                <td align="center">${trainers.name}</td>
                <td align="center">${trainers.surname}</td>
                <td align="center">${trainers.yearOld}</td>
                <td align="center">${trainers.sex}</td>
                <td align="center">${trainers.education}</td>
                <td align="center">${trainers.costPerHour}</td>
                <td align="center"><img src="/document/arnold.gif" height="70" width="70"></td>
            </tr>
        </c:forEach>
    </table>

<c:import url="/jsp/common/footer.jsp" />
</body>
</html>
