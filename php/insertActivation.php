<?php
	include 'query.php';
	$json = json_decode(file_get_contents('php://input'));
	$params[0] = new stdClass();
	$params[0]-> key="id";
	$params[1] = new stdClass();
	$params[1]-> key="user";
	$params[2] = new stdClass();
	$params[2]-> key="desActivation";
	$params[3] = new stdClass();
	$params[3]-> key="amntPlafont";
	$params[4] = new stdClass();
	$params[4]-> key="datAtt";
	$params[5] = new stdClass();
	$params[5]-> key="userInsert";
	$params[0]-> value=$json->id;
	$params[1]-> value=$json->user;
	$params[2]-> value=$json->desActivation;
	$params[3]-> value=$json->amntPlafont;
	$params[4]-> value=$json->dateString;
	$params[5]-> value=$json->userInsert;
	$conn = createConnection();
	$query = getQuery("insertActivation",$params);
	$res = executeInsert($conn,$query);
	echo $res;
?>