<%@ include file="meta.jsp"%>
<%@ include file="navbar.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form:form encType = "multipart/form-data" action="upload" method="POST">
		<input type="file" name="file" /> <input type="submit"
			value="submit" />
	</form:form>
	<%@ include file="footer.jsp"%>
</body>
</html>