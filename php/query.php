<?php
	
	function getQuery($querykey,$params) {
		$queries = new stdClass();
		$queries-> queryLogin ="SELECT * FROM anag_user u where u.EMAIL = '<username>' and u.PSW = '<password>'";
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
?>