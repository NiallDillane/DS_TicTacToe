$(document).ready(function(){
    
    
    $(".dropdown-toggle").dropdown();
    
    var interval = 1000;  // Refresh every 1 second
    function getGames() {
        $.ajax({
            type: 'post',
            url: 'actions/getGames.php',
            data: {},
            dataType: 'json',
            success: function (response) {
                var output;
                output += '<thead><tr><th>Game ID</th><th>User</th><th>Created</th><th>Join</th></tr></thead>';
                
                if (response[0] === "ERROR-NOGAMES") {
                    output += 'No games found :(';
                }
                else{
                    for (var x in response) {
                        var line = response[x].split(",");
                        output += '<tr><td>' + line[0] + '</td><td>' + line[1] + '</td><td>'+ line[2] + '</td><td>' 
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
                output += '<thead><tr><th>Game ID</th><th>User</th><th>Started</th><th>Join</th></tr></thead>';
                
                if (response[0] === "ERROR-NOGAMES") {
                    output += 'No games found :(';
                }
                else{
                    for (var x in response) {
                        var line = response[x].split(",");
                        output += '<tr><td>' + line[0] + '</td><td>' + line[1] + '</td><td>'+ line[2] + '</td><td>' 
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
    
    
    function getLeaderboard() {
        $.ajax({
            type: 'post',
            url: 'actions/getLeagueTable.php',
            data: {},
            dataType: 'json',
            success: function (response) {
                var output;
                output += '<tr><th>Game</td><th>Player 1</th><th>Player 2</th><th>Winner</th><th>Created</th></tr>'
                
                if (response[0] === "ERROR-NOGAMES") {
                    output += '<tr><td>No Games Found :( </td><td></td><td></td><td></td><td></td></tr>';
                }
                else{
                    for (var i = response.length-1; i >= 0; i--) { // Reverse order
                        var line = response[i].split(",");
                        var winner = line[2];
                        if(line[3] == 1)
                            winner = line[1];
                        
                        output += '<tr><td>' + line[0] + '</td><td>' + line[1] + '</td><td>'+ line[2] + '</td>' 
                                + '<td>' + winner + '</td><td>' + line[4] + '</td></tr>';
                    }
                }
                $("#leaderboard").html(output);
            },
            complete: function (response) {
                // Schedule the next
                setTimeout(getLeaderboard, interval);
            }
        });
    }
    setTimeout(getLeaderboard, interval);
    
    
    $("#toggle_games").click(function(){
        $("#gamesDropdown").addClass("active");
        $("#leaderboardsDropdown").removeClass("active");
        $("#div_games").show();
        $("#div_leaderboards, #div_myOpenGames").hide();
    });
    
    $("#toggle_myOpenGames").click(function(){
        $("#gamesDropdown").addClass("active");
        $("#leaderboardsDropdown").removeClass("active");
        $("#div_myOpenGames").show();
        $("#div_leaderboards, #div_games").hide();
    });
    
    
    $("#toggle_leaderboards").click(function(){
        $("#gamesDropdown").removeClass("active");
        $("#leaderboardsDropdown").addClass("active");
        $("#div_leaderboards").show();
        $("#div_games, #div_myOpenGames").hide();
    });
});