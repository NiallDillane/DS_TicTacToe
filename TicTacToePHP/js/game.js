$(document).ready(function(){
    
    var interval = 1000;  // Refresh every 1 second
    function getBoard() {
        $.ajax({
            type: 'post',
            url: 'actions/getBoard.php',
            data: {},
            dataType: 'json',
            success: function (response) {
                var xo;
                var i = 0;
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
                // Schedule the next
                setTimeout(getBoard, interval);
            }
        });
    }
    setTimeout(getBoard, interval);
    
    
    $(".square").click(function checkSquare() {
        var loc = this.id;
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
    });
    
    
    function takeSquare(x, y) {
        $.ajax({
            type: 'post',
            url: 'actions/takeSquare.php',
            data: {x: x, y: y},
            success: function (response) {
                var msg = "";
                if(response == 1){
                    getBoard();
                    checkWin();
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
                if(response == 0){
                    setGameState(0);
                }
                else if(response == 1){
                    setGameState(1);
                }
                else if(response == 2){
                    setGameState(2);
                }
                else if(response == 3){
                    setGameState(3);
                }
                else {
                    // ERROR
                }
            }
        });
    }


//    function getGameState() {
//        $.ajax({
//            type: 'post',
//            url: 'actions/getGameState.php',
//            data: {},
//            success: function (response) {
//                if(response == 0){
//                    // setGameState(0);
//                }
//                else {
//                    msg = response;
//                }
//                $("#message").html(msg);
//            }
//        });
//    }
    
    
    function setGameState(gs) {
        $.ajax({
            type: 'post',
            url: 'actions/setGameState.php',
            data: {gs: gs},
            success: function (response) {
                var msg = "";
                if(response == 1){
                    // Gamestate set
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
                    window.location = "./home.php"
                }
                else{
                    msg = response;
                }
                $("#message").html(msg);
            }
        });
        
    });
    
});