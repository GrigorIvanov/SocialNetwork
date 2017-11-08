<%@ include file="meta.jsp"%>
<%@ include file="navbar.jsp"%>

<html>
<body>
	<form name="upload" method="get">
		<input type="submit" value="Upload piscture" />
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

		<form name="test" method="get">
			<input type="submit" value="Edit" />
		</form>
		<form name="upload" method="get">
			<input type="submit" value="Delete" />
		</form>
		<form name="upload" method="get">
			<input type="submit" value="Like" />
		</form>
		<form name="upload" method="get">
			<input type="submit" value="Show likes" />
		</form>
		<form name="upload" method="get">
			<input type="submit" value="Show comments" />
		</form>
	</c:forEach>
</body>
<%@ include file="footer.jsp"%>

</body>
</html>