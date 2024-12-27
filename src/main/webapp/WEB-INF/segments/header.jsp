<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar">
    <a href="${pageContext.request.contextPath}" class="logo">
        <img src="${pageContext.request.contextPath}/static/images/logo.png" alt="FoundOnes" class="foundones-logo">
    </a>
    <c:choose>
        <c:when test="${empty pageContext.request.userPrincipal}">
            <a href="${pageContext.request.contextPath}/login" class="login-button">
                <p class="login-text">Log in</p>
            </a>
        </c:when>
        <c:when test="${not empty pageContext.request.userPrincipal}">
            <h2 id="welcome-user">Welcome, ${pageContext.request.userPrincipal.name}.</h2>
            <a href="${pageContext.request.contextPath}/logout" class="login-button">
                <p class="login-text">Log out</p>
            </a>
        </c:when>
    </c:choose>
</nav>
