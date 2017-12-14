<?php
	include 'query.php';
	$json = json_decode(file_get_contents('php://input'));
	$params[0] = new stdClass();
	$params[0]-> key="name";
	$params[0]-> value=$json->name;
	$params[1] = new stdClass();
	$params[1]-> key="url";
	$params[1]-> value=$json->url;
	$conn = createConnection();
	$query = getQuery("addDocLink",$params);
	$res = executeInsert($conn,$query);
	echo $res;
?>