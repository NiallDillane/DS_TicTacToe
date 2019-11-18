<?php
    include "../include/inc.php";
    
    try{
        $uid = $_SESSION['uid'];
        $response = $client->newGame(array('uid' => $uid));
        
        if (is_numeric($response->return)) {
            echo 1;
            $_SESSION['gid'] = $response->return;
        }
        else {
            echo $response->return;
        }
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>