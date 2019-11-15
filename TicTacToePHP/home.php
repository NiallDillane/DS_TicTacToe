<?php
    include "./include/inc.php";
?>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <?php include "include/imports.php"; ?>
    </head>
    <body>
        <button class="logoutBtn" id="logoutBtn"
                onClick="window.location = 'actions/logoutAction.php'">Logout</button>
        
        <div class="container">
            
            <div class="toggleContainer">
                <button class="toggleBtnSelected" id="toggle_games">Games</button>
                <button class="toggleBtn" id="toggle_leaderboards">Leaderboards</button>
            </div>
            
            <div id ="div_games">
                <div id ="openGames"></div>

                <input type="submit" name="newGame" value="New Game" class="registerBtn" id="but_newGame" />
            </div>
            
            <div id ="div_leaderboards" style="display:none">
                <label for="username" >Username: </label> </br>
                <input type="text" name="username" id="username_login" />

                <label for="password" >Password: </label> </br>
                <input type="password" name="password" id="password_login" />

                <input type="submit" name="refresh" value="Refresh" class="registerBtn" id="but_refresh" />
            </div>
            
            <div id="message"></div>
            
        </div>
        <br><br>
        
    </body>
</html>
