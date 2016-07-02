<?php

require_once __DIR__ . '/../db_config.php';
$con=mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

$email = $_GET['emailaddress'];
$password = $_GET['password'];
$name = $email;
$phonenumber = "000";
$description = "000";
$profilepict = "000";
$dateofbirth = "000";

$query = "INSERT INTO users (username, email, userpassword, phonenumber, description, profilepict, dateofbirth)
	VALUES ('$name', '$email', '$password', '$phonenumber', '$description', '$profilepict', '$dateofbirth');";

$result = mysqli_query($con, $query);

if($result == true){
	echo '{"query_result":"SUCCESS"}';
}
else {
	echo '{"query_result":"FAILURE"}';
	echo $query;
}

mysqli_close($con);

?>