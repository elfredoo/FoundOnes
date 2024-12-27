<%--
  Created by IntelliJ IDEA.
  User: Bartosz
  Date: 22.08.2024
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new discovery - FoundOnes</title>
    <%@include file="../segments/stylesheets.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/add-discovery-forms.css">
</head>
<body>
    <%@include file="../segments/header.jsp"%>

    <form action="${pageContext.request.contextPath}/discovery/add" method="post" class="discovery-form">
        <h2 class="discovery-form-title">Add new discovery</h2>
        <input type="text" name="title" placeholder="Title" required>
        <input type="url" name="url" placeholder="www.foundones.com" required>
        <select id="select-category" name="categoryId">
            <c:forEach items="${requestScope.categories}" var="category">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
        <textarea name="description" id="description" cols="30" rows="10" placeholder="What interesting have You found lastly?"></textarea>
        <button class="discovery-form-button">Make it real.</button>
    </form>

<%@include file="../segments/footer.jsp"%>
</body>
</html>
