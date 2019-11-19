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
            
            <div class="row">
                <div class="col-md">
                    <div class="dropdown">
                        <button class="btn btn-success btn-block dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Games
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <button class="dropdown-item" id="toggle_games">Open Games</button>
                            <button class="dropdown-item" id="myOpenGames">My Open Games</a>
                            <button class="dropdown-item" id="myOpenGames">My Running/Finished Games</a>
                        </div>
                    </div> 
                </div>
                <div class="col-md">
                    <div class="dropdown">
                        <button class="btn btn-success btn-block dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Leaderboards
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <button class="dropdown-item" id="toggle_leaderboards">Full History</button>
                            <button class="dropdown-item" id="showMyHistory">My History</a>
                            <button class="dropdown-item" id="showLeaderboard">Leaderboards</a>
                        </div>
                    </div> 
                </div>
<!--                <button class="toggleBtnSelected" id="toggle_games">Games</button>-->
                <!--<button class="toggleBtn" id="toggle_leaderboards">Leaderboards</button>-->
            </div>
            <br>
         
            <div id ="div_games">
                <table class="fixedHead" id="gamesTable">
                    <tr><th>Game ID</th><th>User</th><th>Created</th><th>Join</th></tr>
                </table>
                
                <input type="submit" name="newGame" value="New Game" class="registerBtn" id="but_newGame" />
            </div>
            
            <div id ="div_leaderboards" style="display:none">
                <table class="fixedHead" id="leaderboard"></table>

                <!--<input type="submit" name="refresh" value="Refresh" class="registerBtn" id="but_refresh" />-->
            </div>
            
            <div id="message"></div>
            
           
        </div>
        <br><br>
        
    </body>
</html>
