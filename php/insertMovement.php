<?php
	include 'query.php';
	$json = json_decode(file_get_contents('php://input'));
	$params[0] = new stdClass();
	$params[0]-> key="id";
	$params[1] = new stdClass();
	$params[1]-> key="user";
	$params[2] = new stdClass();
	$params[2]-> key="date";
	$params[3] = new stdClass();
	$params[3]-> key="amount";
	$params[0]-> value=$json->id;
	$params[1]-> value=$json->userId;
	$date = $json->dateString;
	$params[2]-> value=$date;
	$params[3]-> value=$json->amount;
	$conn = createConnection();
	$query = getQuery("insertMove",$params);
	$res = executeInsert($conn,$query);
	echo $res;
?>