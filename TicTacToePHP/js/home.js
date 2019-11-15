$(document).ready(function(){
    var interval = 1000;  // Refresh every 3 seconds
    function getGames() {
    $.ajax({
            type: 'post',
            url: 'actions/getGames.php',
            data: {},
            dataType: 'json',
            success: function (response) {
                var output;
                output += '<tr><th>Game ID</td><th>User</td><th>Created</td><th>Join</td></tr>' 
                for (var x in response) {
                    var line = response[x].split(",");
                    output += '<tr><td>' + line[0] + '</td><td>' + line[1] + '</td><td>'+ line[2] + '</td><td>' 
                            + '<button type="button" id="but_joinGame">Join</button></td></tr>';
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
    
    
    $("#but_newGame").click(function(){
        $.ajax({
            url:'./actions/newGame.php',
            type:'post',
            data:{},
            success:function(response){
                var msg = "";
                if(response == 1) {
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
    
    
    $("#but_refresh").click(function(){
        
        if( username != "" && password != "" ){
            $.ajax({
                url:'./actions/loginAction.php',
                type:'post',
                data:{username:username, 
                    password:password},
                success:function(response){
                    var msg = "";
                    if(response == 1){
                        window.location = "./home.php";
                    }else{
                        msg = response;
                    }
                    $("#message").html(msg);
                }
            });
        }
        else {
            $("#message").html("Please enter a username and password.")
        }
    });
    
    $("#toggle_games").click(function(){
        $("#toggle_games").removeClass().addClass("toggleBtnSelected");
        $("#toggle_leaderboards").removeClass().addClass("toggleBtn");
        $("#div_games").show(); 
        $("#div_leaderboards").hide();
    });
    
    $("#toggle_leaderboards").click(function(){
        $("#toggle_leaderboards").removeClass().addClass("toggleBtnSelected");
        $("#toggle_games").removeClass().addClass("toggleBtn");
        $("#div_leaderboards").show(); 
        $("#div_games").hide();
    });
});