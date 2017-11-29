<?php
	include 'query.php';
	$id= $_GET["userId"];
	$conn = createConnection();
	$query = getQuery("queryGetId",null);
	$res = buildAndGetSingleResult($conn,$query);
	echo $res;
?>