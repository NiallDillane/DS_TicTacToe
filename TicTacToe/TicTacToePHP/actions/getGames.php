<?php
    include "../include/inc.php";
//    unset($_SESSION['gid']);
    
    try{
        $response = $client->showOpenGames();
        
        $games = explode("\n", $response->return);
        echo json_encode($games);
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>