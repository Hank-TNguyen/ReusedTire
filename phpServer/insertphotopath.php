<?php

require_once __DIR__ . '/../db_config.php';
$con=mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

$imagepath = $_GET['imagePath'];
$postid = $_GET['postId'];

$query = "INSERT INTO `images`
(postid, imagepath)
VALUES
('$postid', '$imagepath');";

$result = mysqli_query($con, $query);

$response = array();

if ($result== true){
	$query_result = "SUCCESS";
} else {
	$query_result = "FAILURE";
	echo $query; 
} 
$response['query_result'] = $query_result;
$response['query'] = $query;

echo json_encode($response); 

mysqli_close($con);

?>