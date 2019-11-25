<!DOCTYPE html>
<?php
    include "include/inc.php";
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <?php include "include/imports.php"; ?>
        
    </head>
    <body>
        <nav class="navbar navbar-expand-lg sticky-top navbar-light bg-light">
            <span class="navbar-brand" href="#">Tic Tac Toe</span>
        </nav>
        <br>
        <div class="container">
            
            <div class="toggleContainer">
                <button class="toggleBtnSelected" id="toggle_reg">Register</button>
                <button class="toggleBtn" id="toggle_login">Login</button>
            </div>
            
            <div id ="div_register">
                <br>
                <input type="text" name="username" id="username" placeholder="Username" />

                <input type="password" name="password" id="password"  placeholder="Password"/>

                <input type="password" name="passwordRe" id="passwordRe"  placeholder="Re-Enter Password"/>

                <input type="text" name="firstname" id="firstname"  placeholder="First Name"/>

                <input type="text" name="surname" id="surname"  placeholder="Surname"/>

                <input type="submit" name="register" value="Register" class="registerBtn" id="but_register" />
            </div>
            
            <div id ="div_login" style="display:none">
                <br>
                <input type="text" name="username" id="username_login" placeholder="Username" />

                <input type="password" name="password" id="password_login" placeholder="Password" />

                <input type="submit" name="login" value="Login" class="registerBtn" id="but_login" />
            </div>
            
            <div id="message"></div>
            
        </div>
        <br><br>
        
    </body>
</html>
