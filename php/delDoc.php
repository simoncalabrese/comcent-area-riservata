<?php
	include 'query.php';
	$name= $_GET["name"];
	$params[0] = new stdClass();
	$params[0]-> key="name";
	$params[0]-> value=$name;
	$conn = createConnection();
	$query = getQuery("removeDocLink",$params);
	$res = executeDelete($conn,$query);
	echo $res;
?>