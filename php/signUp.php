<?php
	include 'query.php';
	$json = json_decode(file_get_contents('php://input'));
	$params[0] = new stdClass();
	$params[0]-> key="ID";
	$params[0]-> value=$json->id;
	$params[1] = new stdClass();
	$params[1]-> key="NAME";
	$params[1]-> value=$json->name;
	$params[2] = new stdClass();
	$params[2]-> key="SURNAME";
	$params[2]-> value=$json->surname;
	$params[3] = new stdClass();
	$params[3]-> key="COD_FISC";
	$params[3]-> value=$json->codFisc;
	$params[4] = new stdClass();
	$params[4]-> key="P_IVA";
	$params[4]-> value=$json->partIva;
	$params[5] = new stdClass();
	$params[5]-> key="REFERENCE";
	$ref = $json->referenceId;
	if($ref == null) {
		$ref = "null";
	}
	$params[5]-> value=$ref;
	$params[6] = new stdClass();
	$params[6]-> key="READ_PERMISSION";
	$params[6]-> value=$json->readPermissionString;
	$params[7] = new stdClass();
	$params[7]-> key="WRITE_PERMISSION";
	$params[7]-> value=$json->writePermissionString;
	$params[8] = new stdClass();
	$params[8]-> key="EMAIL";
	$params[8]-> value=$json->email;
	$params[9] = new stdClass();
	$params[9]-> key="PHONE";
	$params[9]-> value=$json->phone;
	$params[10] = new stdClass();
	$params[10]-> key="PSW";
	$params[10]-> value=$json->psw;
	$conn = createConnection();
	$query = getQuery("querySignUp",$params);
	$res = executeInsert($conn,$query);
	echo $res;
?>