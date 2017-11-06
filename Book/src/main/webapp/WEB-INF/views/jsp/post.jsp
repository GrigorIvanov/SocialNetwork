<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
		<%@ page import="com.oreilly.servlet.MultipartRequest" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Posts</title>
</head>
<body>

	<form:form encType = "multipart/form-data" commandName="post">
		<form:input path = "file" type = "file" name = "fileName" placeholder = "file"/>
		<form:input path = "content"  type = "text" name = "content" placeholder = "Contetn:" />
		

		
		<input type="submit" value="submit" />
	</form:form>
	
</body>
</html>