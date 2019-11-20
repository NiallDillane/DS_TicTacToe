<?php
    include "../include/inc.php";
    $uid = $_SESSION['uid'];
//    unset($_SESSION['gid']);
    
    try{
        $response = $client->showMyOpenGames(array('uid'=>$uid));
        
        $games = explode("\n", $response->return);
        echo json_encode($games);
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>