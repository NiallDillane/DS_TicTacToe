<?php
    session_start();
    
    $base_url = "/TicTacToePHP";
    
    $wsdl = "http://localhost:8080/TTTWebApplication/TTTWebService?wsdl";
    ini_set('default_socket_timeout', 600);
    
    if (!isset($client)) {
        try {
            $client = new SoapClient($wsdl, array('trace' => true, 'exceptions' => true));
        }
        catch (Exception $ex) {
            alert('Web service unavailable :(');
        }
    } 
?>