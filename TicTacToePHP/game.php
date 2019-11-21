<?php
    include "./include/inc.php";
?>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Play</title>
        <?php include "include/imports.php"; ?>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg sticky-top navbar-light bg-light">
            <span class="navbar-brand" href="#">Tic Tac Toe</span>
        </nav>
        <br>        
        <div class="container">
            <div class="board">
                <button class="square" id="00"></button>
                <button class="square" id="01"></button>
                <button class="square" id="02"></button><br>

                <button class="square" id="10"></button>
                <button class="square" id="11"></button>
                <button class="square" id="12"></button><br>

                <button class="square" id="20"></button>
                <button class="square" id="21"></button>
                <button class="square" id="22"></button><br>
            </div>
            <br>
            
            <input type="submit" name="quit" value="Quit" class="registerBtn" id="but_quit" />
            
            <div id="message"></div>
            
        </div>
        <br><br>
        
    </body>
</html>
