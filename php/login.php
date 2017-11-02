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
	$risultato = mysqli_query($conn,$query) or die("Query non valida: " . mysql_error());
	while($e=mysqli_fetch_assoc($risultato))
        $output[]=$e;
    mysqli_close($conn);
	$res = json_encode($output);
	echo $res;
?>