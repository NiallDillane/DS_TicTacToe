<?php
    include "../include/inc.php";
    $gid = $_SESSION['gid'];
    
    try{
        $response = $client->getBoard(array('gid' => $gid));
        
        $moves = explode("\n", $response->return);
        echo json_encode($moves);
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>