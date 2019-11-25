<?php
    include "../include/inc.php";
    
    try{
        $response = $client->leagueTable();
        $games = explode("\n", $response->return);
        // game, p1, p2, winner, created
        
        $players = array();
        $outRows = array();
        
        // For each game that was played
        foreach ($games as $row){
            // Split into array
            $game = explode(",", $row);
            
            // Find the winner
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
            
            /** 
             * Check if this player has been added to the leaderboards yet.
             * If not, add them to the players list and make a leaderboards entry.
             * (username, wins, draws, losses, isUser)
             * isUser is so I can highlight that row as the user in the leaderboards
             * strcmp() will return 0 if the stored username is the same as session username
             */
            if (!in_array($game[1], $players)) {
                array_push($players, $game[1]);
                array_push($outRows, array($game[1], 0, 0, 0, strcmp($_SESSION['username'], $game[1]))); 
            }
            if (!in_array($game[2], $players)) {
                array_push($players, $game[2]);
                array_push($outRows, array($game[2], 0, 0, 0, strcmp($_SESSION['username'], $game[2])));
            }
            
            /**
             * Cycle through leaderboards entries to find player 1 and 2.
             * Increment their win/draw/loss columns appropriately.
             * & (ampersand) is very important for maintaining the 2D array.
             */
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

        ECHO json_encode($outRows);
        
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>