<title>Book</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src='https://code.jquery.com/jquery-3.2.1.min.js'></script>
<script src='js/navbar.js'></script>
<body>
	<style>
body {
	font-family: "Times New Roman", Georgia, Serif;
}

.hello {
	positon: relative;
	right: 100px;
	top: 10px;
}

.searching {
	border: 2px solid grey;
	border-radius: 5px;
}
</style>

	<!-- Navbar (sit on top) -->
	<div class="w3-top">
		<div class="w3-bar w3-white w3-wide w3-padding w3-card">
			<img src="img/logo.png" width='200x' />
			<p class="hello">Hello ,${sessionScope.user.firstName} ${sessionScope.user.lastName}</p>
			<input class="searching" id='user' type="text"
				placeholder="Search User" list="users_list" />
			<datalist id='users_list'></datalist>

			<!-- Float links to the right. Hide them on small screens -->
			<div class="w3-right w3-hide-small">
				<a href="ShowAllUserPosts" class="w3-bar-item w3-button">My wall</a><a
					href="AllPosts" class="w3-bar-item w3-button">Posts</a> <a
					href="AllPhotos" class="w3-bar-item w3-button">Photos</a> <a
					href="AllFriends" class="w3-bar-item w3-button">Friends</a> <a
					href="AllMessages" class="w3-bar-item w3-button">Messages</a> <a
					href="#contact" class="w3-bar-item w3-button">My profile</a><img
					src="img/people.png" width='70px' /> <br> <br>
			</div>
		</div>
	</div>

	<!-- Header -->
	<header class="w3-display-container w3-content w3-wide"
		style="max-width: 1500px;" id="home">
		<img src="img/one.jpg" style="width: 100%">
		<div class="w3-display-middle w3-margin-top w3-center"></div>
	</header>

	<br>