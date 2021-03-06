<?php
	
	function getQuery($querykey,$params) {
		$queries = new stdClass();
		$queries-> queryLogin = "SELECT * FROM anag_user u where u.EMAIL = '<username>' and u.PSW = '<password>'";
		$queries-> querySignUp = "INSERT INTO anag_user(ID, NAME, SURNAME, COD_FISC, P_IVA, REFERENCE, READ_PERMISSION, WRITE_PERMISSION, EMAIL, PHONE, PSW) 
									VALUES (<ID>,'<NAME>','<SURNAME>','<COD_FISC>','<P_IVA>',<REFERENCE>,'<READ_PERMISSION>','<WRITE_PERMISSION>','<EMAIL>','<PHONE>','<PSW>')";
		$queries-> queryUser = "SELECT u.ID,u.NAME,u.SURNAME FROM anag_user u where u.ID = <id>";
		$queries-> queryGetId = "SELECT coalesce(max(ID),0) as id FROM app_transactions t";
		$queries-> queryGetIdActivation = "SELECT coalesce(max(ID),0) as id FROM app_activation t";
		$queries-> insertMove = "INSERT INTO app_transactions(ID,USER,DAT_MOV,AMOUNT,USER_INSERT) VALUES (<id>,<user>,'<date>',<amount>,<userInsert>)";
		$queries-> insertActivation = "INSERT INTO app_activation(ID, USER, DES_ACTIVATION, AMNT_PLAFONT,DAT_ATT,USER_INSERT) VALUES (<id>,<user>,'<desActivation>',<amntPlafont>,'<datAtt>',<userInsert>)";
		$queries-> getPlafont = "SELECT SUM(amount) as amount FROM app_transactions WHERE USER = <userId>";
		$queries-> getPlafontList = "SELECT amount as amount, dat_mov as dateString, ID as id, USER_INSERT as userInsert
									from app_transactions 
									where user = <userId> 
										AND STR_TO_DATE( DAT_MOV,  '%d-%m-%Y' ) BETWEEN STR_TO_DATE('<dateStart>',  '%d-%m-%Y' ) 
										AND  STR_TO_DATE('<dateEnd>',  '%d-%m-%Y' ) AND AMOUNT > 0 
										ORDER BY DAT_MOV";
		$queries-> getPlafontPos = "SELECT SUM(amount) as amountPos FROM app_transactions WHERE USER = <userId> and amount>0";
		$queries-> getPlafontNeg = "SELECT SUM(amount) as amountNeg FROM app_transactions WHERE USER = <userId> and amount<0";
		$queries-> getUsers = "SELECT * FROM  `app_hierarchy` h
                               WHERE CASE WHEN (
                               	(SELECT center from app_hierarchy where bottom =<userId>) is null and (SELECT top from app_hierarchy where bottom =<userId>) is null and <userId> <> 0)
                               	THEN h.center =0 OR h.top = 0 ELSE h.center = <userId> OR h.top = <userId> END";
		$queries-> getActivations = "SELECT a.ID as id, 
											a.DES_ACTIVATION as desActivation,
											a.AMNT_PLAFONT as amntPlafont,
											a.DAT_ATT as dateString,
											a.USER_INSERT as userInsert from app_activation a where a.USER = <userId> and STR_TO_DATE( a.DAT_ATT,  '%d-%m-%Y' ) BETWEEN STR_TO_DATE('<dateStart>',  '%d-%m-%Y' ) AND  STR_TO_DATE('<dateEnd>',  '%d-%m-%Y' )";
		$queries-> getDocs = "SELECT NAME as name, URL as url FROM anag_docs";
		$queries-> addDocLink = "INSERT INTO anag_docs values ('<name>','<url>')";
		$queries-> removeDocLink = "DELETE FROM anag_docs WHERE NAME = '<name>'";
		$queries-> delActivation = "DELETE FROM app_activation WHERE ID = <id>";
		$queries-> delPlafont = "DELETE FROM app_transactions WHERE ID = <id>";
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

	function executeDelete($conn,$query) {
		mysqli_query($conn,$query) or die("Query non valida: " . mysql_error());
		return 1; 
	}
?>