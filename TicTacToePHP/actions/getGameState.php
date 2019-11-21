<?php
    include "../include/inc.php";
    
    if (!isset($_SESSION['gid'])) {
        ECHO "";
    }
    
    else {
    
        $gid = $_SESSION['gid'];
        $uid = $_SESSION['uid'];
        $xo = $_SESSION['xo'];
        
        // Opponent PID and your PID
        $oPid = 1;
        $pid = 2;
        if ($xo == 'x') {
            $oPid = 2;
            $pid = 1;
        }
        
        try{
            $response = $client->getGameState(array('gid' => $gid));
            $gs = $response->return;
            
            // Still waiting on player 2
            if ($gs == 0) {
                ECHO 0;
            }
            
            else if ($gs == -1) {
                $response = $client->deleteGame(array('gid' => $gid,
                                            'uid' => $uid,));
                unset($_SESSION['gid']);
                unset($_SESSION['xo']);
                ECHO "Nobody wants to play with you :'(";
            }
            
            else if ($gs == "ERROR-NOGAME") {
                unset($_SESSION['gid']);
                unset($_SESSION['xo']);
                ECHO "Nobody wants to play with you :'(";
            }
            
            else {
                // If winner already declared
                if ($gs == 1 || $gs == 2 || $gs == 3) {
                    unset($_SESSION['gid']);
                    unset($_SESSION['xo']);
                    if ($gs == 3) {
                        ECHO "You drew :|";
                    }
                    else if ($gs == $pid) {
                        ECHO "You won :D";
                    }
                    else {
                        ECHO "You lost :(";
                    }
                }
                // You quit!
                else {
                    $response = $client->setGameState(array('gid' => $gid,
                                                    'gstate' => $oPid));
                    ECHO "You lost, quitter >:(";
                    ECHO $gs;
                    unset($_SESSION['gid']);
                    unset($_SESSION['xo']);
                }
            }
        }
        catch(Exception $e){
            echo "<h2>Exception Error!</h2>"; 
            echo $e->getMessage(); 
        }   
    }
?>