<?php
    include "../include/inc.php";
    
    try{
        $response = $client->leagueTable();
        
        $games = explode("\n", $response->return);
        echo json_encode($games);
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>