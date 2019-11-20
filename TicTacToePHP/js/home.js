$(document).ready(function(){
    
    
    $(".dropdown-toggle").dropdown();
    
    /**
     * Gets the difference between current time and game created time
     * @param gameTime : datetime game was created, as a regular string var 
     * 
     * Formula based on: 
     * https://stackoverflow.com/a/42455473
     */
    function timeDiff(gameTime) {
        var now = new Date();
        var game = new Date(gameTime);
        var diff = now - game; 
        
        // diff returned in milliseconds, so for formatting:
        var hours   = Math.floor(diff / 3.6e6);
        var minutes = Math.floor((diff % 3.6e6) / 6e4);
        var seconds = Math.floor((diff % 6e4) / 1000);
        return hours + 'h, ' +  minutes + 'm and ' + 
                     seconds + 's ago';
    }
    
    
    var interval = 1000;  // Refresh every 1 second
    function getGames() {
        $.ajax({
            type: 'post',
            url: 'actions/getGames.php',
            data: {},
            dataType: 'json',
            success: function (response) {
                var output;
                output += '<thead><tr><th>Game ID</th><th>Host</th><th>Created</th><th>Join</th></tr></thead>';
                
                if (response[0] === "ERROR-NOGAMES") {
                    output += 'No games found :(';
                }
                else{
                    for (var i = response.length-1; i >= 0; i--) {
                        var line = response[i].split(",");
                        var time = timeDiff(line[2]);
                        output += '<tr><td>' + line[0] + '</td><td>' + line[1] + '</td><td>'+ time + '</td><td>' 
                                + '<button class="btn btn-outline-success btn-sm" type="button" id="but_joinGame">Join</button></td></tr>';
                    }
                }
                $("#gamesTable").html(output);
            },
            complete: function (response) {
                // Schedule the next
                setTimeout(getGames, interval);
            }
        });
    }
    setTimeout(getGames, interval);
    
    
    function getMyOpenGames() {
        $.ajax({
            type: 'post',
            url: 'actions/getMyOpenGames.php',
            data: {},
            dataType: 'json',
            success: function (response) {
                var output;
                output += '<thead><tr><th>Game ID</th><th>User</th><th>Started</th><th>Rejoin</th></tr></thead>';
                
                if (response[0] === "ERROR-NOGAMES") {
                    output += 'No games found :(';
                }
                else{
                    for (var i = response.length-1; i >= 0; i--) {
                        var line = response[i].split(",");
                        var time = timeDiff(line[2]);
                        output += '<tr><td>' + line[0] + '</td><td>' + line[1] + '</td><td>'+ time + '</td><td>' 
                                + '<button class="btn btn-outline-success btn-sm" type="button" id="but_joinGame">Join</button></td></tr>';
                    }
                }
                $("#myOpenGamesTable").html(output);
            },
            complete: function (response) {
                // Schedule the next
                setTimeout(getMyOpenGames, interval);
            }
        });
    }
    setTimeout(getMyOpenGames, interval);
    
    
    function getMyGames() {
        $.ajax({
            type: 'post',
            url: 'actions/getMyGames.php',
            data: {},
            dataType: 'json',
            success: function (response) {
                var output;
                output += '<thead><tr><th>Game ID</th><th>Player 1</th><th>Player 2</th><th>Started</th><th>Rejoin</th></tr></thead>';
                
                if (response[0] === "ERROR-NOGAMES") {
                    output += 'No games found :(';
                }
                else {
                    for (var i = response.length-1; i >= 0; i--) {
                        var line = response[i].split(",");
                        var time = timeDiff(line[3]);
                        output += '<tr><td>' + line[0] + '</td><td>' + line[1]+ '</td><td>' + line[2] + '</td><td>'+ time + '</td><td>' 
                                + '<button class="btn btn-outline-success btn-sm" type="button" id="but_joinGame">Join</button></td></tr>';
                    }
                }
                $("#myGamesTable").html(output);
            },
            complete: function (response) {
                // Schedule the next
                setTimeout(getMyGames, interval);
            }
        });
    }
    setTimeout(getMyGames, interval);
    
    
    $("#but_newGame").click(function(){
        $.ajax({
            url:'./actions/newGame.php',
            type:'post',
            data:{},
            success:function(response){
                var msg = "";
                if(response == 1) {
                    window.location = "./game.php"
                } 
                else{
                    msg = response;
                }
                $("#message").html(msg);
            }
        });
    });
    
    
    $(document).on("click", '#but_joinGame', (function(event){
        event.preventDefault();
        
        var gid = $(this).closest('tr').find('td:first').text();
        $.ajax({
            url:'./actions/joinGame.php',
            type:'post',
            data:{gid:gid},
            success:function(response){
                var msg = "";
                if(response == 1) {
                    window.location = "./game.php";
                } 
                else{
                    msg = response;
                }
                $("#message").html(msg);
            }
        });
    }));
    
    
    function getHistory() {
        $.ajax({
            type: 'post',
            url: 'actions/getLeagueTable.php',
            data: {},
            dataType: 'json',
            success: function (response) {
                var output;
                output += '<tr><th>Game</th><th>Player 1</th><th>Player 2</th><th>Winner</th><th>Created</th></tr>'
                
                if (response[0] === "ERROR-NOGAMES") {
                    output += '<tr><td>No Games Found :( </td><td></td><td></td><td></td><td></td></tr>';
                }
                else{
                    for (var i = response.length-1; i >= 0; i--) { // Reverse order
                        var line = response[i].split(",");
                        var winner = line[2];
                        if (line[3] == 0)
                            winner = "In Progress";
                        else if (line[3] == 1)
                            winner = line[1];
                        else if (line[3] == 3)
                            winner = "Draw";
                        
                        output += '<tr><td>' + line[0] + '</td><td>' + line[1] + '</td><td>'+ line[2] + '</td>' 
                                + '<td>' + winner + '</td><td>' + line[4] + '</td></tr>';
                    }
                }
                $("#historyTable").html(output);
            },
            complete: function (response) {
                // Schedule the next
                setTimeout(getHistory, interval);
            }
        });
    }
    setTimeout(getHistory, interval);
    
    
    function getLeaderboards() {
        console.log("leaderboards boiii");
        $.ajax({
            type: 'post',
            url: 'actions/getLeaderboards.php',
            data: {},
//            dataType: 'json',
            success: function (response) {
                console.log("yo here");
                console.log(response);
//                    var output;
//                    output += '<tr><th>Rank</th><th>Username</th><th>Wins</th><th>Draws</th><th>Losses</th></tr>'
//
//                    if (response[0] === "ERROR-NOGAMES") {
//                        output += '<tr><td>No Games Found :( </td><td></td><td></td><td></td><td></td></tr>';
//                    }
//                    else{
//                        for (var i = response.length-1; i >= 0; i--) { // Reverse order
//                            var line = response[i].split(",");
//                            output += '<tr><td>' + line[0] + '</td><td>' + line[1] + '</td><td>'+ line[2] + '</td>' 
//                                    + '<td>' + line[4] + '</td><td>' + line[5] + '</td></tr>';
//                        }
//                    }
//                    $("#leaderboardsTable").html(output);
            }
        });
    }
    
    
    $("#toggle_games").click(function(){
        $("#gamesDropdown").addClass("active");
        $("#leaderboardsDropdown").removeClass("active");
        $("#div_games").show();
        $("#div_history, #div_leaderboards, #div_myOpenGames, #div_myGames").hide();
        $("#currMode").html("<h2>Open Games</h2>");
    });
    
    $("#toggle_myOpenGames").click(function(){
        $("#gamesDropdown").addClass("active");
        $("#leaderboardsDropdown").removeClass("active");
        $("#div_myOpenGames").show();
        $("#div_history, #div_leaderboards, #div_games, #div_myGames").hide();
        $("#currMode").html("<h2>My Open Games</h2>");
    });
    
    $("#toggle_myGames").click(function(){
        $("#gamesDropdown").addClass("active");
        $("#leaderboardsDropdown").removeClass("active");
        $("#div_myGames").show();
        $("#div_history, #div_leaderboards, #div_games, #div_myOpenGames").hide();
        $("#currMode").html("<h2>All My Games</h2>");
    });
    
    
    $("#toggle_history").click(function(){
        $("#gamesDropdown").removeClass("active");
        $("#leaderboardsDropdown").addClass("active");
        $("#div_history").show();
        $("#div_games, #div_myOpenGames, #div_myGames").hide();
        $("#currMode").html("<h2>Full History</h2>");
    });
    
    $("#toggle_leaderboards").click(function(){
        $("#gamesDropdown").removeClass("active");
        $("#leaderboardsDropdown").addClass("active");
        $("#div_leaderboards").show();
        $("#div_history, #div_games, #div_myOpenGames, #div_myGames").hide();
        $("#currMode").html("<h2>Leaderboards</h2>");
        
        getLeaderboards();
    });
    
});