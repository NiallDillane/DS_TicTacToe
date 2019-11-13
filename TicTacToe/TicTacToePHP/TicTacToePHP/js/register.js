$(document).ready(function(){
    $("#but_submit").click(function(){
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
                        window.location = "./user.php";
                    }else{
                        msg = response;
                    }
                    $("#message").html(msg);
                }
            });
        }
    });
});