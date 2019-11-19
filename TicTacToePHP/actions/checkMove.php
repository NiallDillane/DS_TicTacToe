<?php
    include "../include/inc.php";
    $gid = $_SESSION['gid'];
    
    try{
        $response = $client->getBoard(array('gid' => $gid));
        
        $moves = explode("\n", $response->return);
        
        $lastmove = explode(",", $moves[sizeof($moves) - 1]);
        $lastPlayer = $lastmove[0];
        
        if (($lastPlayer == "ERROR") && ($_SESSION['xo'] == 'x')) {
            ECHO 1;
        }
        else if($lastPlayer != $_SESSION['uid']){
            ECHO 1; // Go
        }
        else {
            ECHO 0; // Wait
        }
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>