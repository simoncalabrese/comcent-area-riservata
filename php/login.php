<?php
	include 'query.php';
	$json = json_decode(file_get_contents('php://input'));
	$email = $json->username;
	$password = $json->password;
	$params[0] = new stdClass();
	$params[0]-> key="username";
	$params[0]-> value=$email;
	$params[1] = new stdClass();
	$params[1]-> key="password";
	$params[1]-> value=$password;
	$conn = createConnection();
	$query = getQuery("queryLogin",$params);
	$res = buildAndGetSingleResult($conn,$query);
	echo $res;
?>