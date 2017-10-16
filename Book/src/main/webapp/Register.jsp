<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Login Form</title>
        <meta name="description" content="javaBook">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        

        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/main.css">
        <link href="./css/style.css" rel="stylesheet" type="text/css" />
        <script src="js/vendor/modernizr-2.6.2.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
    </head>
    <body>
        
   
     

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.10.2.min.js"><\/script>')</script>
        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>
        
        <script type="text/javascript">
$(document).ready(function() {
    $(".username").focus(function() {
        $(".user-icon").css("left","-48px");
    });
    $(".username").blur(function() {
        $(".user-icon").css("left","0px");
    });
    
    $(".password").focus(function() {
        $(".pass-icon").css("left","-48px");
    });
    $(".password").blur(function() {
        $(".pass-icon").css("left","0px");
    });
});
</script>

       
        <script>
            (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=
            function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;
            e=o.createElement(i);r=o.getElementsByTagName(i)[0];
            e.src='//www.google-analytics.com/analytics.js';
            r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));
            ga('create','UA-XXXXX-X');ga('send','pageview');
        </script>
</head>
    
    <div id="wrapper">
    
    <div class="user-icon"></div>
    <div class="pass-icon"></div>
    

   
    <form name="login-form" class="login-form" action="./Register" method="post">

    
    <div class="header">
    <h1>Register</h1>

   <span><font size="3" color="#151414">Type your information to register.</font></span>
    </div>
    
    
    
    <div class="content">
    <input name="username" type="text" class="input username" value="Username" onfocus="this.value=''" />
    <input name="password" type="password" class="input password" value="Password" onfocus="this.value=''" />
    <input name="email" type="email" class="input email" value="Email" onfocus="this.value=''" />
    <input name="email" type="date" class="input birth date" value="Birth date" onfocus="this.value=''" />
    
    </div>
    
    
    
    <div class="footer">
        <input type="submit" name="submit" value="Register" class="button" />
       
    </div>
    

    </form>
    
     <form name="login-form" class="login-form" action="./index.html" method="get">
     
    
     
      <span><font size="3" color="#151414">You don't have registration? You can register here: </font></span>
     
     <input type="submit" name="submit" value="Login" class="login" />
    
     </form>
    

    </div>
    

        <div class="gradient"></div>

    </body>
</html>
  