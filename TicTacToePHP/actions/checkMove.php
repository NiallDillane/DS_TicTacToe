<?php
    include "../include/inc.php";
    $gid = $_SESSION['gid'];
    $xo = $_SESSION['xo'];
    
    try{
        $response = $client->getBoard(array('gid' => $gid));
        
        $moves = explode("\n", $response->return);
        
        $lastmove = explode(",", $moves[sizeof($moves) - 1]);
        $lastPlayer = $lastmove[0];
        
        if (is_numeric($lastPlayer)){
            if ($lastPlayer == $_SESSION['uid']) {
                ECHO 0; // Wait
            }
            else {
                ECHO 1; // Go
            }
        }
        else {
            if ($xo == 'o') {
                ECHO 0; // Wait
            }
            else {
                ECHO 1; // Go
            }
        }
        
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>