<?php
    include "../include/inc.php";
    
    $gid = $_SESSION['gid'];
    
    try{
        $response = $client->getGameState(array('gid' => $gid));
        $gs = $response->return;
        
        ECHO $gs;
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>