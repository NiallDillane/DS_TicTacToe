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
        <nav class="navbar navbar-expand-lg sticky-top navbar-light bg-light">
            <span class="navbar-brand" href="#">Tic Tac Toe</span>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle active" href="#games" id="gamesDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Games
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuButton">
                            <button class="dropdown-item" id="toggle_games">Open Games</button>
                            <button class="dropdown-item" id="toggle_myOpenGames">My Open Games</button>
                            <button class="dropdown-item" id="toggle_myGames">All My Games</button>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#leaderboards" id="leaderboardsDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Leaderboards
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuButton">
                            <button class="dropdown-item" id="toggle_leaderboards">Leaderboards</button>
                            <button class="dropdown-item" id="toggle_history">Full History</button>
                        </div>
                    </li>
                </ul>
                
                
                <span class="navbar-text">Logged in as: <?php ECHO $_SESSION['username']; ?>&nbsp;&nbsp;</span>
                
                <span class="navbar-text">
                    <button class="btn btn-sm btn-outline-danger" id="logoutBtn" type="button"
                onClick="window.location = 'actions/logoutAction.php'">Logout</button>
                </span>
            </div>
        </nav>
        <br>
        <div class="container">
            <div id="currMode"><h2>Open Games</h2></div>
            
            <div id ="div_games">
                <table class="table thead-light table-hover" id="gamesTable">
                    <tr><th>Game ID</th><th>Host</th><th>Created</th><th>Join</th></tr>
                </table>
                
                <input type="submit" name="newGame" value="New Game" class="registerBtn" id="but_newGame" />
            </div>
            
            <div id ="div_myOpenGames" style="display:none">
                <table class="table thead-light table-hover" id="myOpenGamesTable">
                    <tr><th>Game ID</th><th>User</th><th>Started</th></tr>
                </table>
                
                <input type="submit" name="newGame" value="New Game" class="registerBtn" id="but_newGame" />
            </div>
            
            <div id ="div_myGames" style="display:none">
                <table class="table thead-light table-hover" id="myGamesTable">
                    <tr><th>Game ID</th><th>Player 1</th><th>Player 2</th><th>Started</th></tr>
                </table>
                
                <input type="submit" name="newGame" value="New Game" class="registerBtn" id="but_newGame" />
            </div>
            
            
            <div id ="div_leaderboards" style="display:none">
                <table class="table thead-light table-hover" id="leaderboardsTable">
                    <tr><th>Rank</th><th>Username</th><th>Wins</th><th>Draws</th><th>Losses</th></tr>
                </table>
            </div>
            
            <div id ="div_history" style="display:none">
                <table class="table thead-light table-hover" id="historyTable">
                    <tr><th>Game</th><th>Player 1</th><th>Player 2</th><th>Winner</th><th>Created</th></tr>
                </table>
            </div>
            
            <div id="message"></div>
            
        </div>
        
    </body>
</html>
