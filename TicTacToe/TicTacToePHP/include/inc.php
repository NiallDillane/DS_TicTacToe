<?php
    session_start();
    
    $base_url = "/TicTacToePHP";
    
    $wsdl = "http://localhost:9090/TTTWebApplication/TTTWebService?WSDL";
    if (!isset($client)) {
        try {
            $client = new SoapClient($wsdl, array('trace' => true, 'exceptions' => true));
        }
        catch (Exception $ex) {
            alert('Web service unavailable :(');
        }
    } 
?>