<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users</title>
</head>
<body>
	<h1>${user.firstName}${user.lastName}</h1>
	<p>${user.email}</p>
	<p>${user.birthDate}</p>
	<p>${user.password}</p>
	
	<c:forEach var="post" items="&{user.posts}">
		<li><c:out value="${post}" /></li>
	</c:forEach>

</body>
</html>