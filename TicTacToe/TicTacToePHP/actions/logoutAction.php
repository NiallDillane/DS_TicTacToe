<?php
    include "../include/inc.php";
    session_destroy();
    header("Location: ".$base_url."index.php");
?>