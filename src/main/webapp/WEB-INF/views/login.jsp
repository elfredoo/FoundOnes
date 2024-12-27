<%--
  Created by IntelliJ IDEA.
  User: Bartosz
  Date: 21.08.2024
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../segments/stylesheets.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
<html>
<head>
    <title>Log in - FoundOnes</title>
</head>
<body>
<div class="container">
    <%@include file="../segments/header.jsp"%>

    <form action="j_security_check" method="post" class="user-form">
        <h2 class="user-form-title">Log into FoundOnes!</h2>
        <input type="text" name="j_username" placeholder="Username" required>
        <input type="password" placeholder="Password" name="j_password" required>
        <button class="user-form-button">Log in</button>
        <p>Don't have account yet? <a href="${pageContext.request.contextPath}/signup">Change that quickly!</a></p>
    </form>
    <%@include file="../segments/footer.jsp"%>
</div>
</body>
</html>
