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
                }else{
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
                    msg = "Move made";
                }else{
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
            dataType: 'json',
            success: function (response) {
                if(response == 0){
                    // continue playing
                }
                else if(response == 1){
                    // player 1 wins, end game
                }
                else if(response == 2){
                    // player 2 wins, end game
                }
                else if(response == 3){
                    // draw, end game
                }
                else {
                    // ERROR
                }
            },
            complete: function (response) {
                // Schedule the next
                setTimeout(getBoard, interval);
            }
        });
    }
    setTimeout(getBoard, interval);
    
    
//    function getGameState() {
//        $.ajax({
//            type: 'post',
//            url: 'actions/getGameState.php',
//            data: {},
//            success: function (response) {
//                
//            },
//            complete: function (response) {
//                // Schedule the next
//                setTimeout(getGames, interval);
//            }
//        });
//        
//    }
//    setTimeout(getGameState, interval);

//    function deleteGame() {
//        $.ajax({
//            type: 'post',
//            url: 'actions/getGameState.php',
//            data: {},
//            success: function (response) {
//                    var msg = "";
//                    if(response == 1){
//                        window.location = "./home.php";
//                    }else{
//                        msg = response;
//                    }
//                    $("#message").html(msg);
//            }
//        });
        
//    }
    
});