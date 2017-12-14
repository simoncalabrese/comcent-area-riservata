<?php
	include 'query.php';
	$conn = createConnection();
	$query = getQuery("getDocs",null);
	$res = buildAndGetResultList($conn,$query);
	echo $res;
?>