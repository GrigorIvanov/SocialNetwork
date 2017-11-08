
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div id="clouds">
		<div class="cloud x1"></div>
		<!-- Time for multiple clouds to dance around -->
		<div class="cloud x2"></div>
		<div class="cloud x3"></div>
		<div class="cloud x4"></div>
		<div class="cloud x5"></div>

		<form method="get" action="showAllMyConversations">
			<input type="hidden" name=conversationId
				value="${message.conversationId}" /> <input type="submit"
				value="Back to Conversations" />
		</form>
		<c:forEach items="${messages}" var="message">
			<table>
				<tr>
					<th>Message ID</th>
					<th>Conversation ID</th>
					<th>Content</th>
					<th>Date</th>
					<th>UserId</th>
				</tr>
				<tr>
					<td>${message.messageId}</td>
					<td>${message.conversationId}</td>
					<td>${message.content}</td>
					<td>${message.date}</td>
					<td>${message.userId}</td>
				</tr>
			</table>
		</c:forEach>
		<form:form commandName="message" action="newMessage" method="get">
			<form:input type="text" path="content"
				placeholder="Enter a message.." />
			<input type="hidden" name=conversationId
				value="${message.conversationId}" />
			<input type="submit" value="Send" />
		</form:form>
	</div>
</body>
</html>