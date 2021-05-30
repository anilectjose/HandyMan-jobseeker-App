<?php 

include 'connection.php';

if(isset($_POST['service_id']))
{
	@$jobid = $_POST['service_id'];
	@$reqsts = $_POST['resultant'];

    if($reqsts=="Accepted"){
        $query = mysqli_query($con, "UPDATE `job_db` SET `status`='doing' WHERE job_id='$jobid'");
    }elseif($reqsts=="Rejected") {
        $query = mysqli_query($con, "UPDATE `job_db` SET `status`='pending',`worker_id`=0 WHERE job_id='$jobid'");
    }

	     


		if($query)
		{
			$response['success'] = 1;
			$response['message'] = "Your Job completed!";

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