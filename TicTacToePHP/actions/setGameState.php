<?php
    include "../include/inc.php";
    
    $gid = $_SESSION['gid'];
    $gs = htmlspecialchars($_POST['gs']);
    
    try{
        $response = $client->setGameState(array('gid' => $gid,
                                                'gstate' => $gs));
        
        if($gs == 0){
            ECHO 0;
        }
        else if($gs == -1){
            ECHO -1;
        }
        else if($gs == 1 || $gs == 2 || $gs == 3){
            unset($_SESSION['gid']);
            header("Location: ".$base_url."home.php");
        }
        else {
            ECHO $gs;
        }
        
        
        if (($response->return) == 1){
            ECHO 1;
        }
        else {
            ECHO $response->return;
        }
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>