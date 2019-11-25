<?php
    session_start();
    
    /* Set up SOAP client */
    $wsdl = "http://localhost:8080/TTTWebApplication/TTTWebService?wsdl";
    ini_set('default_socket_timeout', 600);
    
    if (!isset($client)) {
        try {
            $client = new SoapClient($wsdl, array('trace' => true, 'exceptions' => true));
        }
        catch (Exception $ex) {
            ECHO 'Web service unavailable :(';
        }
    } 
    
    /* Check if user is logged on; if so they can advance to other pages */
    $base_url = "/TicTacToePHP/";
    $url = str_replace($base_url, "", $_SERVER['REQUEST_URI']);
    
    $newAccess = array("",
        "index.php", 
        "actions/loginAction.php", 
        "actions/registerAction.php");
    
    if (!isset($_SESSION['uid'])) {
        if(!in_array($url, $newAccess)){
            header("Location: ".$base_url."index.php");
            die();
        }
    }
    else {
        if(in_array($url, $newAccess)){
            header("Location: ".$base_url."home.php");
            die();
        }
    }
?>