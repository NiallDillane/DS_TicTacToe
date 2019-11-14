<!DOCTYPE html>
<?php
    include "include/inc.php";
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script type ="text/javascript" src="js/register.js"></script>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        
    </head>
    <body>
        <div class="container">
            
            <div class="toggleContainer">
                <button class="toggleBtnSelected" id="toggle_reg">Register</button>
                <button class="toggleBtn" id="toggle_login">Login</button>
            </div>
            
            <div id ="div_register">
                <h2>Register:</h2> 
                <label for="username" >Username: </label> </br>
                <input type="text" name="username" id="username" />

                <label for="password" >Password: </label> </br>
                <input type="password" name="password" id="password" />

                <label for="passwordRe" >Re-enter Password: </label> </br>
                <input type="password" name="passwordRe" id="passwordRe" />

                <label for="firstname" >First Name: </label> </br>
                <input type="text" name="firstname" id="firstname" />

                <label for="surname" >Surname: </label> </br>
                <input type="text" name="surname" id="surname" />

                <input type="submit" name="register" value="Register" class="registerBtn" id="but_register" />
            </div>
            
            <div id ="div_login" style="display:none">
                <h2>Login:</h2> 
                <label for="username" >Username: </label> </br>
                <input type="text" name="username" id="username_login" />

                <label for="password" >Password: </label> </br>
                <input type="password" name="password" id="password_login" />

                <input type="submit" name="login" value="Login" class="registerBtn" id="but_login" />
            </div>
            
            <div id="message"></div>
            
        </div>
        <br><br>
        
    </body>
</html>
