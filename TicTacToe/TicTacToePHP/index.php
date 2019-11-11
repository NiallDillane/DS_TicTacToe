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
        <div class="content">
            <div id="message"></div>
            <h2>Register:</h2> 
            <p>
            <label for="username" >Username: </label> </br>
            <input type="text" name="username" id="username" />
            </p>
            <p>
            <label for="password" >Password: </label> </br>
            <input type="password" name="password" id="password" />
            </p>
            <p>
            <label for="passwordRe" >Re-enter Password: </label> </br>
            <input type="password" name="passwordRe" id="passwordRe" />
            </p>
            <p>
            <label for="firstname" >First Name: </label> </br>
            <input type="text" name="firstname" id="firstname" />
            </p>
            <p>
            <label for="surname" >Surname: </label> </br>
            <input type="text" name="surname" id="surname" />
            </p>
            <input type="submit" name="register" value="Register" id="but_submit" />
        </div>
        <br><br>
        
        <?php
            ECHO("testing 123 <br>");
            ECHO($base_url);
        ?>
        
    </body>
</html>
