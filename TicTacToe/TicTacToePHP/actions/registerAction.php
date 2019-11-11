<?php
    include "../include/inc.php";

    $username = mysqli_real_escape_string($con,$_POST['username']);
    $password = mysqli_real_escape_string($con,$_POST['password']);
    $firstname = mysqli_real_escape_string($con,$_POST['firstname']);
    $surname = mysqli_real_escape_string($con,$_POST['surname']);
    
    $response = $client->register(array(
        'username' => $username,
        'password' => $password,
        'firstname' => $firstname,
        'surname' => $surname
    )); 

?>