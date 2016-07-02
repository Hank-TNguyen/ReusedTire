<?php

// include db config class
require_once __DIR__ . '/../db_config.php';
$con=mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    
$query = "SELECT * FROM post WHERE active = 1;";

$result = mysqli_query($con, $query);

$num_rows = mysqli_num_rows($result);

$response = array();

// process each post 
for ($i = 0; $i < $num_rows; $i++){
	$row = mysqli_fetch_assoc($result);
	$post = array();
	// each row is a post 
	$post['postid'] = $row["postid"];
	$post['tireid'] = $row["tireid"];
	$post['userid'] = $row["userid"];
	$post['quantity'] = $row["quantity"];
	$post['location'] = $row["location"];
	$post['notes'] = $row["notes"];

	$tire_query = "SELECT * FROM userid WHERE tireid = ". $post['tireid'] . " ;";	
	$tire_result = mysqli_query($con, $query);
	$tire_row = mysqli_fetch_assoc($tire_result);
	echo $tire_row['tireid'];
}
$response["num_rows"] = $num_rows;
$response["user"] = $userJson;

// echoing JSON response
echo json_encode($response);

mysqli_close($con);

?>



