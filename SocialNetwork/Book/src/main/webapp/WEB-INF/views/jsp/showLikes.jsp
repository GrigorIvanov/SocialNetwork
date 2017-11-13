<%@ include file="meta.jsp"%>
<%@ include file="navbar.jsp"%>
<header class="w3-display-container w3-content w3-wide"
	style="max-width: 1500px;" id="home">
	<img src="img/sn.jpg" style="width: 100%">
	<div class="w3-display-middle w3-margin-top w3-center"></div>
</header>

<html>
<body>

	<c:forEach items="${likes}" var="user" >
		<table style="border: 1px solid; text-align: center">
			<br>
			<br>
			<tr>
				<th>User first name:</th>
				<th>User last name:</th>
				<th>User email:</th>

			</tr>
			<tr>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.email}</td>

			</tr>
		</table>

	</c:forEach>
</body>
<%@ include file="footer.jsp"%>

</body>
</html>