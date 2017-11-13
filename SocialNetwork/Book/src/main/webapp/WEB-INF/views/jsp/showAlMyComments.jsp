<%@ include file="meta.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="css/style.css">
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${commentList}" var="comment">
		<table>
			<tr>
				<th>Comment ID</th>
				<th>Post ID</th>
				<th>Comment TEXT</th>
				<th>USER:</th>
				<th>DATE:</th>
				<th></th>
			</tr>
			<tr>

				<td>${comment.content}</td>

				<td></td>
			</tr>
		</table>
		<br>
		<form:form commandName="comment" action="newComment" method="get">
			<form:input type="text" path="content" placeholder="Enter a comment.." />
			<input type="hidden" name="commentedPost" value="asd>" />
			<input type="hidden" name="commenter" value="asd" />
			<input type="submit" value="Create Comment" />
		</form:form>
	</c:forEach>
</body>
</html>