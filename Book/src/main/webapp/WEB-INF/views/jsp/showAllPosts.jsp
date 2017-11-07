<%@ include file="meta.jsp"%>
<%@ include file="navbar.jsp"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
				<form name="test" method="get" action="upload">
			
			<form:form commandName="post">
								<form:hidden path="content" value = "$={post.content}" />
								<form:hidden path="postedBy" value = "$={post.postedBy}" />
								<form:hidden path="date" value = "$={post.date}" />
								<form:hidden path="urlPicture" value = "$={post.urlPicture}" />			
			</form:form>
			
			
			
			
					<input type="submit" value="Upload Picture" />
				</form>

			</tr>


		</table>
	</c:forEach>
</body>
<%@ include file="footer.jsp"%>

</body>
</html>