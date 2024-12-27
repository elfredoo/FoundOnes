<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${requestScope.category.name} - FoundOnes</title>
    <%@include file="../segments/stylesheets.jsp" %>
</head>
<body>
<div class="container">
    <!--navbar-->
    <%@include file="../segments/header.jsp"%>

    <main>
        <h1 class="category-name">${requestScope.category.name}</h1>
        <p>${requestScope.category.description}</p>
        <%@include file="../segments/discovery-list.jsp"%>
    </main>
    <%@include file="../segments/footer.jsp"%>
</div>
</body>
</html>
