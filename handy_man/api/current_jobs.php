<?php
include 'connection.php';

$data_list = array();
    @$roleid = $_POST['user_id'];

 	$query=mysqli_query($con,"SELECT * FROM `job_db` where customer_id='$roleid' and status='pending'");


if(mysqli_num_rows($query) > 0)
{
	while($real= mysqli_fetch_assoc($query))
	{

		$data_list[] = $real;
	}	

	$response['success'] =1; 
	$response['result'] = $data_list;
    
	echo json_encode($response);
}
else
{
	$response['success'] =0; 
	$response['result'] = "No data found!";
    
	echo json_encode($response);
}

?>

