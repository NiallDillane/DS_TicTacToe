<?php
    include "../include/inc.php";
    
    try{
        $response = $client->leagueTable();
        $games = explode("\n", $response->return);
        // game, p1, p2, winner, created
        
        // player, wins, draws, losses
        $players = array();
        $outRows = array();
        
        foreach ($games as $row){
            $game = explode(",", $row);
            
            $winner = $game[2];
            if ($game[3] == 0) {
                $winner = "NA";
            }
            else if ($game[3] == 1) {
                $winner = $game[1];
            }
            else if ($game[3] == 3) {
                $winner = "Draw";
            }
            
            if (!in_array($game[1], $players)) {
                array_push($players, $game[1]);
                array_push($outRows, array($game[1], 0, 0, 0));
            }
            
            if (!in_array($game[2], $players)) {
                array_push($players, $game[2]);
                array_push($outRows, array($game[2], 0, 0, 0));
            }
            
            foreach ($outRows as &$out) {
                if ($out[0] == $game[1]) { // when I find player 1
                    switch($winner) {
                        case $game[1]: // they were the winner
                            $out[1] += 1;
                            break;
                        case "Draw": // they drew
                            $out[2]+=1;
                            break;
                        case $game[2]: // other player was winner
                            $out[3]+=1;
                            break;
                    }
                }
                if ($out[0] == $game[2]) { // when I find player 2
                    switch($winner) {
                        case $game[2]:
                            $out[1]+=1;
                            break;
                        case "Draw": 
                            $out[2]+=1;
                            break;
                        case $game[1]:
                            $out[3]+=1;
                            break;
                    }
                }   
            }
        }
//        array_multisort( $outRows[1], SORT_ASC);
        ECHO json_encode($outRows);
        
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>