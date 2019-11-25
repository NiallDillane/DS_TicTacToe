<?php
    include "../include/inc.php";
    
    try{
        $uid = $_SESSION['uid'];
        $gid = htmlspecialchars($_POST['gid']);
        
        $response = $client->joinGame(array('uid' => $uid,
                                            'gid' => $gid));
        
        if (is_numeric($response->return)) {
            $_SESSION['gid'] = $gid;
            $_SESSION['xo'] = 'o';
            echo 1;
        }
        else {
            echo $response->return;
        }
    }
    catch(Exception $e){
        echo "<h2>Exception Error!</h2>"; 
        echo $e->getMessage(); 
    }   
?>