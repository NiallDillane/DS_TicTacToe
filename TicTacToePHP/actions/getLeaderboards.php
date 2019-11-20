<?php
    include "../include/inc.php";
    
    try{
        $response = $client->leagueTable();
        $games = explode("\n", $response->return);
        // game, p1, p2, winner, created
        
        // player, wins, draws, losses
        $players = array();
        $outRows = array($players);
        
        
        foreach ($games as $game){
            $winner = $game[2];
            if ($game[3] == 0) {
                $winner = "In Progress";
            }
            else if ($game[3] == 1) {
                $winner = $game[1];
            }
            else if ($game[3] == 3) {
                $winner = "Draw";
            }
            
            if (!in_array($game[1], $players)) {
                array_push($players, $game[1]);
                array_push($outRows['user'], $game[1]);
            }
            
            if (!in_array($game[2], $players)) {
                array_push($players, $game[2]);
                array_push($outRows['user'], $game[2]);
            }
            
            ECHO json_encode($outRows);
            
        }
        
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>