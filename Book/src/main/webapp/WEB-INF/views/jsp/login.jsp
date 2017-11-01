<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Login & Sign Up Form Concept</title>
  
  
  <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans:400,300'>
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>

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
  <p>Enter your credentials to log in.</p> 
  <button class="btn_login" onclick="cambiar_login()">LOGIN</button>
  </div>
  </div>
<div class="col_md_sign_up">
<div class="cont_ba_opcitiy">
  <h2>SIGN UP</h2>
  
 
  <button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN UP</button>
 
  
</div>
  </div>
       </div>

    
<form action = "./Login" method = "post">
    <div class="cont_back_info">
       <div class="cont_img_back_grey">
       <img src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d" alt="" />
       </div>
       
    </div>
<div class="cont_forms" >
    <div class="cont_img_back_">
       <img src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d" alt="" />
       </div>
 <div class="cont_form_login">
<a href="#" onclick="ocultar_login_sign_up()" ><i class="material-icons">&#xE5C4;</i></a>
   <h2>LOGIN</h2>
 <input name = "email" type="text" placeholder="Email" />
<input name = "password" type="password" placeholder="Password" />
<button class="btn_login" onclick="cambiar_login()">LOGIN</button>
  </div>
</form>
  
<form action = "./Register" method = "post">
   <div class="cont_form_sign_up">
<a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons">&#xE5C4;</i></a>
     <h2>SIGN UP</h2>
<input name = "email" type="text" placeholder="Email" />
<input name = "username "type="text" placeholder="User" />
<input name = "password"type="password" placeholder="Password" />
<input name = "confPassword"type="password" placeholder="Confirm Password" />
<button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN UP</button>

  </div>

</form>
    </div>
    
  </div>
 </div>
</div>
  
    <script  src="js/index.js"></script>

</body>
</html>
