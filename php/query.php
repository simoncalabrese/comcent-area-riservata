<?php
	
	function getQuery($querykey,$params) {
		$queries = new stdClass();
		$queries-> queryLogin = "SELECT * FROM anag_user u where u.EMAIL = '<username>' and u.PSW = '<password>'";
		$queries-> queryGetId = "SELECT coalesce(max(ID),0) as id FROM app_transactions t where t.USER = <userId>";
		$queries-> insertMove = "INSERT INTO app_transactions(ID,USER,DAT_MOV,AMOUNT) VALUES (<id>,<user>,'<date>',<amount>)";
		$queries-> getPlafont = "SELECT SUM(amount) as amount FROM app_transactions WHERE USER = <userId> AND DAT_MOV BETWEEN  '<dateStart>' AND  '<dateEnd>'";
		$queries-> getPlafontPos = "SELECT SUM(amount) as amountPos FROM app_transactions WHERE USER = <userId> AND DAT_MOV BETWEEN  '<dateStart>' AND  '<dateEnd>' and amount>0";
		$queries-> getPlafontNeg = "SELECT SUM(amount) as amountNeg FROM app_transactions WHERE USER = <userId> AND DAT_MOV BETWEEN  '<dateStart>' AND  '<dateEnd>' and amount<0";
		$query = $queries->$querykey;
		foreach ($params as $value) {
			$query = str_replace("<".($value->key).">", $value->value, $query);
		}
		return $query;
	}

	function createConnection() {
		$connection = mysqli_connect("89.46.111.53","Sql1151252","1y71354203","Sql1151252_1") or die("Connessione non riuscita");
		return $connection;
	}

	function buildAndGetResultList($conn,$query) {
		$risultato = mysqli_query($conn,$query) or die("Query non valida: " . mysql_error());
		while($e=mysqli_fetch_assoc($risultato))
        	$output[]=$e;
    	mysqli_close($conn);
    	return json_encode($output);;
	}

	function buildAndGetSingleResult($conn,$query) {
		$risultato = mysqli_query($conn,$query) or die("Query non valida: " . mysql_error());
		while($e=mysqli_fetch_assoc($risultato))
        	$output[]=$e;
    	mysqli_close($conn);
    	return json_encode($output[0]);;
	}

	function executeInsert($conn,$query) {
		mysqli_query($conn,$query) or die("Query non valida: " . mysql_error());
		$id = mysqli_insert_id($conn);
		return $id;
	}
?>