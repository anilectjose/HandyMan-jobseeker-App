<?php 

include 'connection.php';

if(isset($_POST['name']))
{
	@$name = $_POST['name'];
	@$email = $_POST['email_id'];
	@$location = $_POST['location'];
	@$mobile = $_POST['mobile_num'];
	@$password = $_POST['password'];
	@$flag = $_POST['security'];


	if ($flag==1) 
	{
		$role="worker";

		mysqli_query($con, "INSERT INTO `role_db`(`email`, `password`, `role`) VALUES ('$email','$password','$role')");
	     $roleid =mysqli_insert_id($con);

	     $query = mysqli_query($con, "INSERT INTO `worker_registration_db`(`worker_name`, `phone`, `location`, `login_id`) VALUES ('$name','$mobile','$location','$roleid')");

	}
	else
	{

		$role="user";   
		 mysqli_query($con, "INSERT INTO `role_db`(`email`, `password`, `role`) VALUES ('$email','$password','$role')");
	     $roleid =mysqli_insert_id($con);                   
	                
		$query = mysqli_query($con, "INSERT INTO `customer_registration_db`(`customer_name`, `phone`, `place`, `login_id`) VALUES ('$name','$mobile','$location','$roleid')");
	   }

		if($query)
		{
			$response['success'] = 1;
			$response['message'] = "Your registration is successfully completed!";

			echo json_encode($response);

		}
		else
		{
			$response['success'] = 0;
			$response['message'] = "Error occured!";

			echo json_encode($response);

		}


}
else
{
	$response['success'] = 0;
	$response['message'] = "No access!";

	echo json_encode($response);
}



?>