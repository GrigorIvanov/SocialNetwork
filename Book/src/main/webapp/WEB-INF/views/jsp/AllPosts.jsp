<%@ include file="meta.jsp"%>
<%@ include file="navbar.jsp"%>
<!-- Header -->
<header class="w3-display-container w3-content w3-wide"
	style="max-width: 1500px;" id="home">
	<img src="img/sn.jpg" style="width: 100%">
	<div class="w3-display-middle w3-margin-top w3-center"></div>
</header>
<br>
<body>
	<c:forEach items="${posts}" var="post">
		<table style="border: 1px solid; text-align: center">
			<p>Content: ${post.content}</p>
		</table>
	</c:forEach>
</body>
<%@ include file="footer.jsp"%>

<html>
</body>
</html>