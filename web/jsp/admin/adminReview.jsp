<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resource.pagecontent" var="var"/>
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
<div class="centerTable">
    <h1><fmt:message key="label.headerReviews" bundle="${var}"/></h1>
    <%--<%--%>
        <%--List<Client> clients = new ArrayList<>();--%>
        <%--Client client = new Client(8,"Евгений", "Соболев", 33, "M",--%>
                <%--"sobolev@gamil.com", "b25ef06be3b6948c0bc431da46c2c738", "client", (long)7, 0.0,(long) 8);--%>
        <%--clients.add(new Client(2, "Pety", "Saplov", 23, "M",--%>
                <%--"goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "client", (long)1, (double)15, (long)2));--%>

        <%--session.setAttribute("client", client);--%>

        <%--List<Review> reviews = new ArrayList<>();--%>
        <%--Review review = new Review(1, 7, "So so", 4);--%>
        <%--reviews.add(new Review(2, 2,  "Very good", 10));--%>
        <%--reviews.add(new Review(12,3,"It is very cool!", 7));--%>
        <%--reviews.add(review);--%>
        <%--session.setAttribute("reviews", reviews);--%>
    <%--%>--%>
    <table width="80%" float="left">
        <c:forEach items="${reviews}" var="review">
        <c:choose>
        <c:when test="${ review.mark > 7 }" >
        <tr><td><b><fmt:message key="label.clientId" bundle="${var}"/>:</b>      ${review.clientId}
            <b><fmt:message key="label.mark" bundle="${var}"/></b>:           ${review.mark}</td>
        </tr>
        <tr><td style="background-color: rgba(0,255,0,0.5)">${review.textReview}</td>
            <form name="localeForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
                <input type="hidden" name="command" value="delete_review_admin"/>
                <input type="hidden"  name="reviewId" value=${review.reviewId} />
                <td><input type="image" src="/document/bitseps.jpeg" height="20" width="20"></td>
            </form>
        </tr>
        </c:when>
        <c:when test="${ review.mark < 5 }" >
        <tr><td><b><fmt:message key="label.clientId" bundle="${var}"/>:</b>      ${review.clientId}
            <b><fmt:message key="label.mark" bundle="${var}"/></b>:           ${review.mark}</td></tr>
        <tr><td style="background-color: rgba(255,0,0,0.5)">${review.textReview}</td>
            <form name="localeForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
                <input type="hidden" name="command" value="delete_review_admin"/>
                <input type="hidden"  name="reviewId" value=${review.reviewId} />
                <td><input type="image" src="/document/bitseps.jpeg" height="20" width="20"></td>
            </form>
        </tr>

</div>
</c:when>
<c:otherwise>
    <tr><td><b><fmt:message key="label.clientId" bundle="${var}"/>:</b>      ${review.clientId}
        <b><fmt:message key="label.mark" bundle="${var}"/></b>:           ${review.mark}</td></tr>
    <tr><td>${review.textReview}</td>
        <form name="localeForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
            <input type="hidden" name="command" value="delete_review_admin"/>
            <input type="hidden"  name="reviewId" value=${review.reviewId} />
            <td><input type="image" src="/document/bitseps.jpeg" height="20" width="20"></td>
        </form>
    </tr>
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
</div>

<c:import url="/jsp/common/footer.jsp" />
</body>
</html>