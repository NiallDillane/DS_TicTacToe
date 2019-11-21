$(document).ready(function(){
    
    var interval = 1000;  // Refresh every 1 second
    function getBoard() {
        var i = 0;
        $.ajax({
            type: 'post',
            url: 'actions/getBoard.php',
            data: {},
            dataType: 'json',
            success: function (response) {
                var xo;
                for (var x in response) {
                    var move = response[x].split(",");
                    var loc = move[1] + "" + move[2];
                    if (i%2 == 0)
                        xo = 'x';
                    else
                        xo = 'o';
                    
                    $("#" + loc).html(xo);
                    i++;
                }
            },
            complete: function (response) {
                if(i > 2) {
                    checkWin();
                }
                setTimeout(getBoard, interval);
            }
        });
    }
    setTimeout(getBoard, interval);
    
    
    $(".square").click(function checkMove() {
        var loc = this.id;
        $.ajax({
            type: 'post',
            url: 'actions/checkMove.php',
            data: {},
            success: function (response) {
                if (response == 1) {
                    checkSquare(loc);
                }
                else if (response == 0) {
                    $("#message").html("Wait your turn");
                }
                else {
                    $("#message").html(response);
                }
            }
        });
    });
    
    
    function checkSquare(loc) {
        var x = loc.charAt(0);
        var y = loc.charAt(1);
        
        $.ajax({
            type: 'post',
            url: 'actions/checkSquare.php',
            data: {x: x, y: y},
            success: function (response) {
                var msg = "";
                if(response == 0){
                    takeSquare(x, y);
                }
                else{
                    msg = response;
                }
                $("#message").html(msg);
            }
        });
    }
    
    
    function takeSquare(x, y) {
        $.ajax({
            type: 'post',
            url: 'actions/takeSquare.php',
            data: {x: x, y: y},
            success: function (response) {
                var msg = "";
                if(response == 1){
                }
                else{
                    msg = response;
                }
                $("#message").html(msg);
            }
        });
    }
    
    
    function checkWin() {
        $.ajax({
            type: 'post',
            url: 'actions/checkWin.php',
            data: {},
            success: function (response) {
                if(!isNaN(response)) {
                    setGameState(response);
                }
                else {
                    $("#message").html(response);
                }
            }
        });
    }


    setTimeout(function getGameState() {
        $.ajax({
            type: 'post',
            url: 'actions/getGameState.php',
            data: {},
            success: function (response) {
                if(response == -1){
                    window.location = "./home.php";
                }
                else {
                }
            }
        });
    }, 10000);
    
    
    function setGameState(gs) {
        $.ajax({
            type: 'post',
            url: 'actions/setGameState.php',
            data: {user: false,
                gs: gs},
            success: function (response) {
                var msg = "";
                if(response == 1){
                    // delete game
                    window.location = "./home.php"
                }
                else if (response == 0) {
                    // continue
                }
                else{
                    msg = response;
                }
                $("#message").html(msg);
            }
        });
    }
    

    $("#but_quit").click(function deleteGame() {
        $.ajax({
            type: 'post',
            url: 'actions/deleteGame.php',
            data: {},
            success: function (response) {
                var msg = "";
                if(response == 1){
                    window.location = "./home.php";
                }
                else{
                    msg = response;
                }
                $("#message").html(msg);
            }
        });
        
    });
    
});

