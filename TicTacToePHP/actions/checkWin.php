<?php
    include "../include/inc.php";
    
    $gid = $_SESSION['gid'];
    
    try{
        $response = $client->checkWin(array('gid' => $gid));
        
        ECHO $response->return;
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>