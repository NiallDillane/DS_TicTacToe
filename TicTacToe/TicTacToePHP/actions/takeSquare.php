<?php
    include "../include/inc.php";
    
    $gid = $_SESSION['gid'];
    $pid = $_SESSION['uid'];
    $x = htmlspecialchars($_POST['x']);
    $y = htmlspecialchars($_POST['y']);
    
    try{
        $response = $client->takeSquare(array('x' => $x,
                                            'y' => $y, 
                                            'gid' => $gid, 
                                            'pid' => $pid));
        
        if (($response->return)>0){
            ECHO 1;
        }
        else if (($response->return) == 0){
            ECHO "Unsuccessful";
        }
        else {
            ECHO "Databse Error!";
        }
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>