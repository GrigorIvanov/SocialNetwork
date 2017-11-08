<%@ include file="meta.jsp"%>
<%@ include file="navbar.jsp"%>

<html>
<body>
	<form name="test" method="get" action="post">
		<input type="submit" value="New post" />
	</form>
	<c:forEach items="${posts}" var="post">
		<table style="border: 1px solid; text-align: center">
			<br>
			<br>
			<tr>
				<th>Content:</th>
				<th>Date:</th>
			</tr>
			<tr>
				<td>${post.content}</td>
				<td>${post.date}</td>
			</tr>
		</table>
	</c:forEach>
</body>
<%@ include file="footer.jsp"%>

</body>
</html>