<?php

// include db config class
require_once __DIR__ . '/../db_config.php';
$con=mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

$emailaddress = $_GET['emailaddress'];
    
$query = "SELECT * FROM users WHERE email = '$emailaddress';";

$result = mysqli_query($con, $query);

if ($result== true){
	$query_result = "SUCCESS";
} else {
	$query_result = "FAILURE";
} 

$num_rows = mysqli_num_rows($result);
if ($num_rows == 0){
	$message = "Username doesn't exist.";
} else {
	$message = "User found!";
}

$response = array();

// process user Json
$row = mysqli_fetch_assoc($result);	
$user = array(); 	
$user["id"] = $row["userid"];
$user["name"] = $row["username"];
$user["email"] = $row["email"];
$user["password"] = $row["userpassword"];
$user["phoneNumber"] = $row["phonenumber"];
$user["description"] = $row["description"];
$user["profilePict"] = $row["profilepict"];
$user["dateOfBirth"] = $row["dateofbirth"];

$userJson = json_encode($user);
$response["query_result"] = $query_result;
$response["message"] = $message;
$response["user"] = $userJson;

// echoing JSON response
echo json_encode($response);

mysqli_close($con);

?>



