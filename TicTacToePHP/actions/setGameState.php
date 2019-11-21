<?php
    include "../include/inc.php";
    
    $gid = $_SESSION['gid'];
    $uid = $_SESSION['uid'];
    
    /**
     * Checking if I was passed a user, to set as the loser, in case of quitting
     * Otherwise just set the gamestate provided
     */
//    if ($_POST['user'] == true) {
//        if ($_SESSION['xo'] == 'x') {
//            $gs = 2; // If the player is x (player 1) then player 2 wins!
//        }
//        else {
//            $gs = 1;
//        }
//    }
//    else {
        $gs = htmlspecialchars($_POST['gs']);
//    }
    
    try{
        $response = $client->setGameState(array('gid' => $gid,
                                                'gstate' => $gs));
        
        if($gs == 1 || $gs == 2 || $gs == 3){
            unset($_SESSION['gid']);
            unset($_SESSION['xo']);
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