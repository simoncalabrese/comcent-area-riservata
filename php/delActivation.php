<?php
	    include 'query.php';
    	$id= $_GET["id"];
    	$params[0] = new stdClass();
    	$params[0]-> key="id";
    	$params[0]-> value=$id;
    	$conn = createConnection();
    	$query = getQuery("delActivation",$params);
    	$res = executeDelete($conn,$query);
    	echo $res;
?>