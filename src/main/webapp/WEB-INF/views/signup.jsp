<%--
  Created by IntelliJ IDEA.
  User: Bartosz
  Date: 21.08.2024
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../segments/stylesheets.jsp" %>
<link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/forms.css">
<html>
<head>
    <title>Register - Foundnes</title>
</head>
<body>
<div class="container">
    <%@include file="../segments/header.jsp"%>

    <form action="${pageContext.request.contextPath}/signup" method="post" class="user-form">
        <h2 class="user-form-title">Join FoundOnes!</h2>
        <input type="text" name="username" placeholder="Username" required>
        <input type="email" placeholder="email" name="email" required>
        <input type="password" placeholder="Password" name="password" required>
        <button class="user-form-button">Register</button>
    </form>
    <%@include file="../segments/footer.jsp"%>
</div>

</body>
</html>
