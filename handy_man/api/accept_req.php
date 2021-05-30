<?php
include 'connection.php';

$data_list = array();
    @$roleid = $_POST['user_id'];
    @$role = $_POST['user_role'];
if ($role=='worker') 
  {
	$query=mysqli_query($con,"SELECT * FROM `job_db` join customer_registration_db on customer_registration_db.login_id=job_db.customer_id where job_db.worker_id='$roleid' or job_db.worker2_id='$roleid'");

	if(mysqli_num_rows($query) > 0)
    {
	while($real= mysqli_fetch_assoc($query))
	{

		$data_list[] = $real;
	}	

	$response['success'] =2; 
	$response['result'] = $data_list;
    
	echo json_encode($response);
   }
  else
  {
	$response['success'] =0; 
	$response['result'] = "No data found!";
    
	echo json_encode($response);
   }
 }

else
  {
	$query=mysqli_query($con,"SELECT * FROM `job_db` join worker_registration_db on worker_registration_db.login_id=job_db.worker_id where job_db.customer_id='$roleid' and job_db.status='requested'");
 	


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
}

?>

