<?php
	
	function getQuery($querykey,$params) {
		$queries = new stdClass();
		$queries-> queryLogin = "SELECT * FROM anag_user u where u.EMAIL = '<username>' and u.PSW = '<password>'";
		$queries-> querySignUp = "INSERT INTO anag_user(ID, NAME, SURNAME, COD_FISC, P_IVA, REFERENCE, READ_PERMISSION, WRITE_PERMISSION, EMAIL, PHONE, PSW) 
									VALUES (<ID>,'<NAME>','<SURNAME>','<COD_FISC>','<P_IVA>',<REFERENCE>,'<READ_PERMISSION>','<WRITE_PERMISSION>','<EMAIL>','<PHONE>','<PSW>')";
		$queries-> queryUser = "SELECT u.ID,u.NAME,u.SURNAME FROM anag_user u where u.ID = <id>";
		$queries-> queryGetId = "SELECT coalesce(max(ID),0) as id FROM app_transactions t";
		$queries-> queryGetIdActivation = "SELECT coalesce(max(ID),0) as id FROM app_activation t";
		$queries-> insertMove = "INSERT INTO app_transactions(ID,USER,DAT_MOV,AMOUNT) VALUES (<id>,<user>,'<date>',<amount>)";
		$queries-> insertActivation = "INSERT INTO app_activation(ID, USER, DES_ACTIVATION, AMNT_PLAFONT,DAT_ATT,USER_INSERT) VALUES (<id>,<user>,'<desActivation>',<amntPlafont>,'<datAtt>',<userInsert>)";
		$queries-> getPlafont = "SELECT SUM(amount) as amount FROM app_transactions WHERE USER = <userId> AND STR_TO_DATE( DAT_MOV,  '%d-%m-%y' ) BETWEEN STR_TO_DATE('<dateStart>',  '%d-%m-%y' ) AND  STR_TO_DATE('<dateEnd>',  '%d-%m-%y' )";
		$queries-> getPlafontPos = "SELECT SUM(amount) as amountPos FROM app_transactions WHERE USER = <userId> AND STR_TO_DATE( DAT_MOV,  '%d-%m-%y' ) BETWEEN STR_TO_DATE('<dateStart>',  '%d-%m-%y' ) AND  STR_TO_DATE('<dateEnd>',  '%d-%m-%y' ) and amount>0";
		$queries-> getPlafontNeg = "SELECT SUM(amount) as amountNeg FROM app_transactions WHERE USER = <userId> AND STR_TO_DATE( DAT_MOV,  '%d-%m-%y' ) BETWEEN STR_TO_DATE('<dateStart>',  '%d-%m-%y' ) AND  STR_TO_DATE('<dateEnd>',  '%d-%m-%y' ) and amount<0";
		$queries-> getUsers = "SELECT * FROM  `app_hierarchy` h WHERE h.center =<userId> OR h.top = <userId>";
		$queries-> getActivations = "SELECT a.ID as id, 
											a.DES_ACTIVATION as desActivation,
											a.AMNT_PLAFONT as amntPlafont,
											a.DAT_ATT as dateString from app_activation a where a.USER = <userId> and STR_TO_DATE( a.DAT_ATT,  '%d-%m-%y' ) BETWEEN STR_TO_DATE('<dateStart>',  '%d-%m-%y' ) AND  STR_TO_DATE('<dateEnd>',  '%d-%m-%y' )";
		$query = $queries->$querykey;
		if($params != null) {
			foreach ($params as $value) {
				$query = str_replace("<".($value->key).">", $value->value, $query);
			}
		}
		return $query;
	}

	function createConnection() {
		$connection = mysqli_connect("89.46.111.53","Sql1151252","1y71354203","Sql1151252_1") or die("Connessione non riuscita");
		return $connection;
	}

	function buildAndGetResultList($conn,$query) {
		$risultato = mysqli_query($conn,$query) or die("Query non valida: " . mysql_error());
		$output = [];
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