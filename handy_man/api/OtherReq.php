<?php
include 'connection.php';

$data_list = array();	

@$roleid = $_POST['login_idd'];

$query=mysqli_query($con,"SELECT * FROM `job_db` where status='doing' and urgency=1 and worker2_id=0 and `worker_id`!='$roleid'" );


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

