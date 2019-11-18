<?php
    include "../include/inc.php";
    
    $gid = $_SESSION['gid'];
    $x = htmlspecialchars($_POST['x']);
    $y = htmlspecialchars($_POST['y']);
    
    try{
        $response = $client->checkSquare(array('x' => $x,
                                            'y' => $y, 
                                            'gid' => $gid));
        
        if (($response->return)>0){
            ECHO "Square is taken, please try another";
        }
        else if (($response->return) == 0){
            ECHO 0;
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