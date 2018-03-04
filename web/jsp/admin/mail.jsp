<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="locale" var="var"/>
<fmt:setLocale value="${changeLanguage}"/>
<html>
<head>
    <title>JavaMail</title>
    <title>Fitness center</title>
    <style>
        @import "/css/mail.css";
    </style>
    <%@ include file="/jsp/common/header.jsp" %>
</head>
<body>
    <form action="${pageContext.request.contextPath}/jsp/mailServlet" method="POST">
        <div class="centerTable">
        <table>
            <tr>
                <td><fmt:message key="label.sendTo" bundle="${var}"/></td>
                 <td><input type="text" name="to"/></td>
            </tr>
            <tr>
                <td><fmt:message key="label.subject" bundle="${var}"/></td>
                <td><input type="text" name="subject"/></td>
            </tr>
        </table>
        <hr/>
        <textarea type="text" name="body" rows="5" cols="45"></textarea>
        <br/><br/>
        <input type="submit" style="width: 200px; height: 50px;" value="<fmt:message key="label.sendMessage"  bundle="${var}"/>"/>
        </div>
    </form>
    <c:import url="/jsp/common/footer.jsp" />
</body>
</html>
