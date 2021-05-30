<?php 

include 'connection.php';

if(isset($_POST['jobid']))
{
	@$id = $_POST['jobid'];
	

	$query = mysqli_query($con, "UPDATE `job_db` SET `urgency`=1 WHERE `job_id`='$id'");
	
if($query)
{
	$response['success'] = 1;
	$response['message'] = "Applied!";

	echo json_encode($response);

}
else
{
	$response['success'] = 0;
	$response['message'] = "Error occured!";

	echo json_encode($response);

}



}else
{
	$response['success'] = 0;
	$response['message'] = "No Access!";

	echo json_encode($response);

}


?>