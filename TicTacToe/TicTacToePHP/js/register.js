$(document).ready(function(){
    $("#but_submit").click(function(){
        var username = $("#username").val().trim();
        var password = $("#password").val().trim();
        var firstname = $("#firstname").val().trim();
        var surname = $("#surname").val().trim();
        
        if( username != "" && password != "" ){
            $.ajax({
                url:'http://localhost:8888/TicTacToePHP/actions/registerAction.php',
                type:'post',
                data:{username:username, 
                    password:password, 
                    firstname:firstname, 
                    surname:surname },
                success:function(response){
                    var msg = "";
                    if(response == 1){
                        console.log("3");
                        window.location = "http://localhost:8888//TicTacToePHP/user.php";
                    }else{
                        msg = "Invalid username and password!";
                    }
                    $("#message").html(msg);
                }
            });
        }
    });
});