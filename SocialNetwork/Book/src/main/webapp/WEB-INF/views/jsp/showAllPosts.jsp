<%@ include file="meta.jsp"%>
<%@ include file="navbar.jsp"%>

<html>
<body>
	<form name="post" method="get" action="post">
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
				<td><img src = "img/${post.urlPicture}"/></td>
				
			</tr>
		</table>
		<form name="upload" method="get" action = "upload">
			<input type="submit" value="Upload picture" />
		</form>
		<form name="test" method="get">
			<input type="submit" value="Edit" />
		</form>
		<form name="upload" method="get">
			<input type="submit" value="Delete" />
		</form>

		<form name="test" method="get" action="newLike">
			<input type="hidden" name="postId" value="${post.postId}" /> <input
				type="submit" value="Like">
		</form>

		<form name="test" method="get" action="showAllLikes">
			<input type="hidden" name="postId" value="${post.postId}" /> <input
				type="submit" value="Show likes">
		</form>

		<form action="showPostComments" method="get">
			<input type="hidden" name="postId" value="${post.postId}" /> <input
				type="submit" value="Show comments" />
		</form>
	</c:forEach>
</body>
<%@ include file="footer.jsp"%>

</body>
</html>