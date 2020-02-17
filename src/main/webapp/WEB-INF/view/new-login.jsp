<%--
  Created by IntelliJ IDEA.
  User: Ankit
  Date: 17-01-2020
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Custom Login Page</title>
    <style>
        .failed{
            color:red
        }
    </style>
</head>
<body>
<h3>My New Login Page</h3>
<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">
    <c:if test="${param.error!=null}">
        <i class="failed"> Sorry!!! Wrong E-mail or Password. Please make sure both are correct</i>

    </c:if>
<p>Username: <input type="text" name="username"></p>
    <p>Password: <input type="password" name="password"></p>
    <input type="submit" value="Login"/>
</form:form>
</body>
</html>
