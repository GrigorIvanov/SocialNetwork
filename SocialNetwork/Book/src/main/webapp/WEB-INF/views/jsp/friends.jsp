<%@ include file="meta.jsp"%>
<%@ include file="navbar.jsp"%>
<!-- Header -->

<br>
<body>
	<c:forEach items="${friends}" var="user">
		<table style="border: 1px solid; text-align: center">
			<tr>
				<th>Friends:</th>
			</tr>
			<tr>
			<c:forEach items="${friends}" var="user">
				<table style="border: 1px solid; text-align: center">
					<p>FirstName: ${user.firstName}</p>
					<p>LastName: ${user.lastName}</p>
					<p>Email ${user.email }</p>
				</table>
			</c:forEach>
			</tr>

		</table>
	</c:forEach>
</body>
<%@ include file="footer.jsp"%>

<html>
</body>
</html>