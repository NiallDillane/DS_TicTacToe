<?php
    include "../include/inc.php";

    $username = htmlspecialchars($_POST['username']);
    $password = htmlspecialchars($_POST['password']);
    
    try{
        $response = $client->login(array(
            'username' => $username,
            'password' => $password
        ));
    
        if (($response->return)>0){
            ECHO 1;
            $_SESSION['uid'] = $response->return;
        }
        else if (($response->return) == 0){
            ECHO "Error - Incorrect details";
        }
        else {
            ECHO "Error";
        }
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }

?>