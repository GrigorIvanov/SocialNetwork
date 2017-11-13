<%@ include file="meta.jsp"%>
<%@ include file="navbar.jsp"%>


<body>
	<div id="gallery">
		<c:forEach items="${posts}" var="post">
			<table style="border: 1px solid; text-align: center">
				<br>
				<br>

				<tr>

					<td><img src="img/${post.urlPicture}" /></td>

				</tr>
			</table>
			<form name="upload" method="get">
				<input type="submit" value="Upload picture" />
			</form>

		</c:forEach>
	</div>
	<hr><%@ include file="footer.jsp"%>
</body>
<html>
</body>
</html>

