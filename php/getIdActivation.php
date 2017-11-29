<?php
	include 'query.php';
	$id= $_GET["userId"];
	$conn = createConnection();
	$query = getQuery("queryGetIdActivation",null);
	$res = buildAndGetSingleResult($conn,$query);
	echo $res;
?>