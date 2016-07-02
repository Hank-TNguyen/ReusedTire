<?php

require_once __DIR__ . '/../db_config.php';
$con=mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

require_once __DIR__ . '/../newguid.php';

$postid = NewGuid();  // `postid` varchar(128) NOT NULL,
$tireid = $_GET['tireId'];  // `tireid` varchar(128) NOT NULL,
$userid = $_GET['userId'];  // `userid` int(8) NOT NULL,
$quantity = $_GET['quantity'];  // `quantity` text NOT NULL,
$location = $_GET['location'];
$notes = $_GET['notes'];

$active = 1;  // `active` tinyint(1) NOT NULL,



$query = "INSERT INTO `post` (postid, tireid, userid, quantity, location, notes, active) VALUES ('$postid', '$tireid', '$userid', '$quantity', '$location', '$notes', '$active');";

$result = mysqli_query($con, $query);

$response = array();

if ($result== true){
	$query_result = "SUCCESS";
} else {
	$query_result = "FAILURE";
} 

// return postid so that the app can update images table

$response['query_result'] = $query_result;
$response['query'] = $query; 
$response['postid'] = $postid; 

echo json_encode($response);

mysqli_close($con);
?>