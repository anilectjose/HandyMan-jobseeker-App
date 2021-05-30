<?php 

include 'connection.php';

if(isset($_POST['name']))
{
	@$location = $_POST['name'];
	@$name = $_POST['email_id'];
	@$email = $_POST['mobile_num'];
	@$sp = $_POST['ser_price'];
	@$wo_id = $_POST['w_id'];


	$query =mysqli_query($con, "UPDATE `worker_registration_db` SET `qualification`='$name',`specialization`='$email',`link`='$location', `price`='$sp' WHERE login_id='$wo_id'");


		if($query)
		{
			$response['success'] = 1;
			$response['message'] = "Updated successfully!";

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