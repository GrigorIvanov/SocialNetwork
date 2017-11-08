<%@ include file="meta.jsp"%>
<%@ include file="navbar.jsp"%>
<!-- Header -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<header class="w3-display-container w3-content w3-wide"
	style="max-width: 1500px;" id="home"> <img src="img/sn.jpg"
	style="width: 100%">
<div class="w3-display-middle w3-margin-top w3-center"></div>
</header>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<br>
	<c:if test="${empty sessionScope.user.email}">
		<c:redirect url="login" />
	</c:if>

	<c:out value="Your email is: ${sessionScope.user.email}" />

	<p>
		<c:out value="Your firstname is: ${sessionScope.user.firstName}" />
	<form action="changeFirstName" method="get">
		<input type="text" name="firstName" placeholder="Enter new name"
			required="required" /> <input type="submit"
			value="change your firstname" />
	</form>
	<p>

		<c:out value="Your lastname is: ${sessionScope.user.lastName}" />
	<form action="changeLastName" method="get">
		<input type="text" name="lastName" placeholder="Enter new name"
			required="required" /> <input type="submit"
			value="change your lastname" />
	</form>
</body>
</html>