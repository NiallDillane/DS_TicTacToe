<?php
    include "../include/inc.php";
    
    try{
        $uid = $_SESSION['uid'];
        $gid = $_SESSION['gid'];
        
        $response = $client->deleteGame(array('gid' => $gid,
                                            'uid' => $uid,));
        
        if ($response->return == 1) {
            echo 1;
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