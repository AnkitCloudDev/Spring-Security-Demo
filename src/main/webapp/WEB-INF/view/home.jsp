<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
	<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page</h2>
	<hr>
	<p>Welcome to the luv2code company home page!</p>
	<hr>
	<security:authorize access="hasRole('MANAGER')">
<p>
<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
(Only for Managers)
</p>
	</security:authorize>

	<hr>
	<security:authorize access="hasRole('ADMIN')">
	<p>		<a href="${pageContext.request.contextPath}/systems">Admin Systems</a>
		(Only for ADMINS)
	</p>
	<hr>
	</security:authorize>
<P> User: <security:authentication property="principal.username"></security:authentication></P>
	<br>
	<P> Role: <security:authentication property="principal.authorities"></security:authentication></P>
	<hr>
<%--Add A Logout Button--%>

<form:form action="${pageContext.request.contextPath}/logout" method="post">
<input type="submit" value="LOGOUT"/>


</form:form>

</body>

</html>