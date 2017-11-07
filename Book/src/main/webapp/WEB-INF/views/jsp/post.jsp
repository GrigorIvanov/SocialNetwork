<%@ include file="meta.jsp"%>
<%@ include file="navbar.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Posts</title>
</head>
<body>
	<form:form commandName="post">
		<form:input path="content" type="text" name="content"
			placeholder="Contetn:" />
		<input type="submit" value="submit" />
	</form:form>
	<%@ include file="footer.jsp"%>
</body>
</html>