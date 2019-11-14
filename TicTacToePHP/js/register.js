$(document).ready(function(){
    $("#but_register").click(function(){
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
        else {
            $("#message").html("Please enter a username and password.")
        }
    });
    
    $("#but_login").click(function(){
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
                        window.location = "./user.php";
                    }else{
                        msg = response;
                    }
                    $("#message").html(msg);
                }
            });
        }
    });
    
    $("#toggle_reg").click(function(){
        $("#toggle_reg").removeClass().addClass("toggleBtnSelected");
        $("#toggle_login").removeClass().addClass("toggleBtn");
        $("#div_register").show(); 
        $("#div_login").hide();
    });
    
    $("#toggle_login").click(function(){
        $("#toggle_login").removeClass().addClass("toggleBtnSelected");
        $("#toggle_reg").removeClass().addClass("toggleBtn");
        $("#div_login").show(); 
        $("#div_register").hide();
    });
});