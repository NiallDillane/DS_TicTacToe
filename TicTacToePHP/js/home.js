$(document).ready(function(){
    var interval = 1000;  // 1000 = 1 second, 3000 = 3 seconds
    function getGames() {
    $.ajax({
            type: 'POST',
            url: 'actions/getGames.php',
            data: {},
            success: function (response) {
                    $('#openGames').html(response);// first set the value     
            },
            complete: function (response) {
                    // Schedule the next
                    setTimeout(getGames, interval);
            }
        });
    }
    setTimeout(getGames, interval);
    
    
    $("#but_newGame").click(function(){
        var username = $("#username").val().trim();
        var password = $("#password").val().trim();
        var firstname = $("#firstname").val().trim();
        var surname = $("#surname").val().trim();
        
        if( username != "" && password != "" ){
            $.ajax({
                url:'./actions/registerAction.php',
                type:'post',
                data:{username:username, 
                    password:password, 
                    firstname:firstname, 
                    surname:surname },
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
    
    $("#but_refresh").click(function(){
        var username = $("#username_login").val().trim();
        var password = $("#password_login").val().trim(); 
        
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