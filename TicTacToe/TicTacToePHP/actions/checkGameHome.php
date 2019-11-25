<?php
    include "../include/inc.php";
    
    /**
     * This is primarily used at the home screen, for checking the status
     * of a player's last-played game.
     * Determines if that game was in progress, and gives them a loss if so,
     * since they were the first to quit.
     * If game wasn't yet started, it is deleted.
     * Otherwise, simply reflects if the game was a win/draw/loss.
     */
    
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
            
            // Still waiting on player 2, nobody wants to play
            if ($gs == -1) {
                $response = $client->deleteGame(array('gid' => $gid,
                                            'uid' => $uid,));
                unset($_SESSION['gid']);
                unset($_SESSION['xo']);
                ECHO "Nobody wants to play with you :'(";
            }
            
            // If the game was already deleted for being inactive
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