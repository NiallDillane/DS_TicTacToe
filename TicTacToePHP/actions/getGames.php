<?php
    include "../include/inc.php";
    
    try{
        $response = $client->showOpenGames();
        
        
        $games = explode("\n", $response->return);
        foreach ($games as $val) {
            $game = explode(",", $val);
            foreach ($game as $gval) {
                if($gval instanceof DateTime) {
                    echo "datetime";
                }
                echo $gval."&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
            }
            echo "<br>";
        }
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>