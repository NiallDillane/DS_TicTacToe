<?php
    include "../include/inc.php";
    
    $gid = $_SESSION['gid'];
    $uid = $_SESSION['uid'];
    $gs = htmlspecialchars($_POST['gs']);
    
    try{
        $response = $client->setGameState(array('gid' => $gid,
                                                'gstate' => $gs));
        
        if($gs == 1 || $gs == 2 || $gs == 3){
            ECHO 1;
        }
        else {
            ECHO $gs;
        }
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>