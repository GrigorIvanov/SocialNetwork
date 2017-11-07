<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login  Sign Up Form Concept</title>


<link rel='stylesheet prefetch'
	href = 'https://fonts.googleapis.com/css?family=Open+Sans:500,400'>
<link rel='stylesheet prefetch'
	href='https://fonts.googleapis.com/icon?family=Material+Icons'>

<link rel="stylesheet" href="css/style.css">


</head>

<body>
	<div class="cotn_principal">
		<div class="cont_centrar">

			<div class="cont_login">
				<div class="cont_info_log_sign_up">
					<div class="col_md_login">
						<div class="cont_ba_opcitiy">


							<h2>LOGIN</h2>
							<c:out value = "${loginError}"/>
							
							<button class="btn_login" onclick="cambiar_login()">LOGIN</button>
						</div>
					</div>
					<div class="col_md_sign_up">
						<div class="cont_ba_opcitiy">
							<h2>SIGN UP</h2>
							<c:out value = "${registerError}"/>
							<button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN
								UP</button>


						</div>
					</div>
				</div>


				<form:form commandName="user">
					<div class="cont_back_info">
						<div class="cont_img_back_grey">
							<img
								src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d"
								alt="" />
						</div>
			

					</div>
					<div class="cont_forms">
						<div class="cont_img_back_">
							<img
								src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d"
								alt="" />
						</div>
						<div class="cont_form_login">
							<a href="#" onclick="ocultar_login_sign_up()"><i
								class="material-icons">&#xE5C4;</i></a>
							<h2>LOGIN</h2>
							<c:out value = "${loginError}"/>
							<form:input name="email" type="text" placeholder="Email"
								path="email" />
						<form:input name="password" type="password"
								placeholder="Password" path="password" />
								<form:hidden path="firstName" value = "FirstNameExample" />
								<form:hidden path="lastName" value = "LastNameExample" />
							<form:button class="btn_login" onclick="cambiar_login()">LOGIN</form:button>
						</div>
				</form:form>

				<form:form commandName="user" action = "register">
					<div class="cont_form_sign_up">
						<a href="#" onclick="ocultar_login_sign_up()"><i
							class="material-icons">&#xE5C4;</i></a>
						<h2>SIGN UP</h2>
						<c:out value = "${registerError}"/>
						
						<form:input  name="firstName" type="text" placeholder="First Name"
							path="firstName" />
						<form:input  name="lastName" type="text" placeholder="Last Name"
							path="lastName" />


						<form:input name="email" type="text" placeholder="Email"
							path="email" />
							

						<form:input name="password" type="password" placeholder="Password"
							path="password" />


						
						

						<form:button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN
							UP</form:button>

					</div>

				</form:form>
			</div>

		</div>
	</div>
	</div>

	<script src="js/index.js"></script>

</body>
</html>
