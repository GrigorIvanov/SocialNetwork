<%@ include file="meta.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Conversations</title>
</head>
<body>

	<div id="clouds">
		<div class="cloud x1"></div>
		<!-- Time for multiple clouds to dance around -->
		<div class="cloud x2"></div>
		<div class="cloud x3"></div>
		<div class="cloud x4"></div>
		<div class="cloud x5"></div>
		</br> </br>

		<form method="get" action="showAllMyPosts">
			<input type="submit" value="Back to Homepage" />
		</form>
		<c:forEach items="${conversations}" var="conversation">
			<table>
				<tr>
					<th>Conversation ID</th>
					<th>Title</th>
				</tr>
				<tr>
					<td>${conversation.conversationId}</td>
					<td>${conversation.title}</td>
					<td>
						<form method="get" action="openConversation">
							<input type="submit" value="Open Conversation" /> <input
								type="hidden" name="conversationId"
								value="${conversation.conversationId}" />
						</form>
					</td>
				</tr>

			</table>
		</c:forEach>
		<div class="w3-dropdown-hover">
			<button class="w3-button">Choose friend for chat!</button>
			<div class="w3-dropdown-content w3-bar-block w3-border">
				<c:forEach items="${friends}" var="user">
					<form id="form1" action="createConversation" method="get">
						<a input type="hidden" name="friendId" value="${user.userId}"
							href="javascript:;" class="w3-bar-item w3-button"
							onclick="document.getElementById('form1').submit();">${user.firstName}</a>
					</form>
				</c:forEach>
			</div>
		</div>
	</div>

</body>
</html>