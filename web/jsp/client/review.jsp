<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="var"/>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/review.css"/>">
    <title>Reviews</title>
    <%@ include file="/jsp/common/header.jsp" %>
</head>
<body>
<%--<%--%>
    <%--List<Client> clients = new ArrayList<>();--%>
    <%--Client client = new Client(8,"Евгений", "Соболев", 33, "M",--%>
            <%--"sobolev@gamil.com", "b25ef06be3b6948c0bc431da46c2c738", "client", (long)7, 0.0,(long) 8);--%>
    <%--clients.add(new Client(2, "Pety", "Saplov", 23, "M",--%>
            <%--"goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "client", (long)1, (double)15, (long)2));--%>

    <%--session.setAttribute("client", client);--%>

    <%--List<Review> reviews = new ArrayList<>();--%>
    <%--Review review = new Review(1, 7, "So so", 4);--%>
    <%--reviews.add(new Review(2, 2, "Very good", 10));--%>
    <%--reviews.add(review);--%>
    <%--session.setAttribute("reviews", reviews);--%>
<%--%>--%>
<div class="centerTable">
    <h1><fmt:message key="label.headerReviews" bundle="${var}"/></h1>
    <table width="80%" float="left">
        <c:forEach items="${reviews}" var="review">
        <c:choose>
        <c:when test="${ review.mark > 7 }" >
        <tr><td><b><fmt:message key="label.clientId" bundle="${var}"/></b>      ${review.clientId}
            <b><fmt:message key="label.mark" bundle="${var}"/></b>:           ${review.mark}</td>
        </tr>
        <tr><td style="background-color: rgba(0,255,0,0.5)">${review.textReview}</td>
            <c:if test="${client.idClient eq review.clientId}">
                <form name="localeForm" method="POST" action="/jsp/controller">
                    <input type="hidden" name="command" value="delete_review"/>
                    <input type="hidden"  name="reviewId" value=${review.reviewId}/>
                    <td><input type="image" src="/document/bitseps.jpeg" height="20" width="20"></td>
                </form>
            </c:if>
        </tr>
        </c:when>
        <c:when test="${ review.mark < 5 }" >
        <tr><td><b><fmt:message key="label.clientId" bundle="${var}"/></b>      ${review.clientId}
            <b><fmt:message key="label.mark" bundle="${var}"/></b>:           ${review.mark}</td></tr>
        <tr><td style="background-color: rgba(255,0,0,0.5)">${review.textReview}</td>
            <c:if test="${client.idClient eq review.clientId}">
                <form name="localeForm" method="POST" action="/jsp/controller">
                    <input type="hidden" name="command" value="delete_review"/>
                    <input type="hidden"  name="reviewId" value=${review.reviewId} />
                    <td><input type="image" src="/document/bitseps.jpeg" height="20" width="20"></td>
                </form>
            </c:if></tr>

</div>
</c:when>
<c:otherwise>
    <tr><td><b><fmt:message key="label.clientId" bundle="${var}"/></b>      ${review.clientId}
        <b><fmt:message key="label.mark" bundle="${var}"/></b>:           ${review.mark}</td></tr>
    <tr><td>${review.textReview}</td>
        <c:if test="${client.idClient eq review.clientId}">
            <form name="localeForm" method="POST" action="/jsp/controller">
                <input type="hidden" name="command" value="delete_review"/>
                <input type="hidden"  name="reviewId" value=${review.reviewId} />
                <td><input type="image" src="/document/bitseps.jpeg" height="20" width="20"></td>
            </form>
        </c:if></tr>
</c:otherwise>
</c:choose>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
</c:forEach>
</table>
<c:if test="${not empty client}">
    <h3><fmt:message key="label.giveFeedback" bundle="${var}"/></h3>
    <form name="localeForm" id="addFormId" method="POST" action="/jsp/controller">
        <input type="hidden" name="command" value="add_review"/>
        <b><fmt:message key="label.mark" bundle="${var}"/></b>:
        <select name="mark">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
        </select>
        <br>
        <b><fmt:message key="label.textReview" bundle="${var}"/></b>
        <br>
        <textarea rows="4" cols="50" name="comment" form="addFormId" ></textarea>
        <br>
        <b>${MessageReview}</b>
        <br>
        <input type="submit" style="width: 200px; height: 50px;" value="<fmt:message key="label.sendReview" bundle="${var}"/>" />
        <br>
        <br>
        <br>
    </form>
</c:if>

</div>
<c:import url="/jsp/common/footer.jsp" />
</body>
</html>