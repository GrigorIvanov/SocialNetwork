<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<title>Book</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>

	<!-- Navbar (sit on top) -->
	<div class="w3-top">
		<div class="w3-bar w3-white w3-wide w3-padding w3-card">
			<a href="#home" class="w3-bar-item w3-button"> Book</a>
			<!-- Float links to the right. Hide them on small screens -->
			<div class="w3-right w3-hide-small">
				<a href="#projects" class="w3-bar-item w3-button">My wall</a> <a
					href="#about" class="w3-bar-item w3-button">Photos</a> <a
					href="#contact" class="w3-bar-item w3-button">Friends</a> <a
					href="#contact" class="w3-bar-item w3-button">Messages</a> <a
					href="#contact" class="w3-bar-item w3-button">Posts</a>
			</div>
		</div>
	</div>

	<!-- Header -->
	<header class="w3-display-container w3-content w3-wide"
		style="max-width: 1500px;" id="home">
		<img src="img/sn.jpg" style="width: 100%">
		<div class="w3-display-middle w3-margin-top w3-center"></div>
	</header>

	<!-- Page content -->
	<div class="w3-content w3-padding" style="max-width: 1564px">

		<!-- Project Section -->
		<div class="w3-container w3-padding-32" id="projects">
			<h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">Posts</h3>
		</div>
		<div class="w3-col l3 m6 w3-margin-bottom">
			<img src="img/net1.jpg" style="width: 100%">
		</div>
	</div>
	<div class="w3-col l3 m6 w3-margin-bottom">
		<img src="img/net2.jpg" style="width: 100%">
	</div>
	</div>
	<div class="w3-col l3 m6 w3-margin-bottom">
		<img src="img/net3.jpg" style="width: 100%">
	</div>


	<!-- About Section -->
	<div class="w3-container w3-padding-32" id="about">
		<h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">About</h3>

	</div>


	<!-- Footer -->
	<footer class="w3-center w3-black w3-padding-16">
		<p>Created by Emil & Grigor</p>
	</footer>
</body>
</html>
