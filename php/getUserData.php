<?php
	include 'query.php';
	$id= $_GET["userId"];
	$params[0] = new stdClass();
	$params[0]-> key="id";
	$params[0]-> value=$id;
	$conn = createConnection();
	$query = getQuery("queryUser",$params);
	$res = buildAndGetSingleResult($conn,$query);
	echo $res;
?>