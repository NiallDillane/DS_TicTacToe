<?php
    include "../include/inc.php";

    $username = htmlspecialchars($_POST['username']);
    $password = htmlspecialchars($_POST['password']);
    $firstname = htmlspecialchars($_POST['firstname']);
    $surname = htmlspecialchars($_POST['surname']);
    
    try{
        $response = $client->register(array(
            'username' => $username,
            'password' => $password,
            'name' => $firstname,
            'surname' => $surname
        ));
    
        if (ctype_digit($response->return)){
            ECHO 1;
            $_SESSION['uid'] = $response->return;
        }
        else{
            ECHO $response->return;
        }
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }

?>