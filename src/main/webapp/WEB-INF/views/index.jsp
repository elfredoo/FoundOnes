<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FoundOnes</title>
    <%@include file="../segments/stylesheets.jsp" %>
</head>
<body>
<div class="container">
<!--navbar-->
    <%@include file="../segments/header.jsp"%>

<aside class="categories">
    <ul>
        <c:forEach items="${requestScope.categories}" var="category">
            <li><a href="${pageContext.request.contextPath.concat('/category?id=').concat(category.id)}">${category.name}</a></li>
        </c:forEach>
    </ul>
</aside>
<main>
    <%@include file="../segments/discovery-list.jsp"%>
</main>
<%@include file="../segments/footer.jsp"%>
</div>
</body>
</html>