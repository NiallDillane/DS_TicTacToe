<?php
    /* Get the page name to fetch the associated .js file */
    $base_url = "/TicTacToePHP/";
    $page = rtrim(str_replace($base_url, "", $_SERVER['REQUEST_URI']), ".php");
    
    ECHO ('
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="js/'.$page.'.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    ');
?>