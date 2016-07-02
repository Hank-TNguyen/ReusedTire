<?php

require_once __DIR__ . '/../db_config.php';
$con=mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

require_once __DIR__ . '/../newguid.php';

$width = $_GET['width'];
$ratio = $_GET['ratio'];
$diameter = $_GET['diameter'];
$brand = $_GET['brand'];
$season = $_GET['season'];
$rating = $_GET['rating'];

$active = (bool) 1;
$id = NewGuid();

$query = "INSERT INTO `inventory`
(tireid, 
`width`,
`ratio`,
`diameter`,
`brand`,
`season`,
`rating`)
VALUES
('$id', '$width', '$ratio', '$diameter', '$brand', '$season', '$rating');";

$result = mysqli_query($con, $query);

$response = array();

if ($result== true){
	$query_result = "SUCCESS";
} else {
	$query_result = "FAILURE";
} 

$response['query_result'] = $query_result;
$response['query'] = $query; 
$response['tireid'] = $id; 

echo json_encode($response);

mysqli_close($con);

?>