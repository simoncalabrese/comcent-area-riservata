<?php
	include 'query.php';
	$json = json_decode(file_get_contents('php://input'));
	$params[0] = new stdClass();
	$params[0]-> key="userId";
	$params[1] = new stdClass();
	$params[1]-> key="dateStart";
	$params[2] = new stdClass();
	$params[2]-> key="dateEnd";
	$params[0]-> value= $json->userId;
	$params[1]-> value= $json->dateStart;
	$params[2]-> value= $json->dateEnd;
	$conn = createConnection();
	$query = getQuery("getPlafontList",$params);
	$res = buildAndGetResultList($conn,$query);
	echo $res;
?>