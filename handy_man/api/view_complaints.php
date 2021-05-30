<?php
include 'connection.php';

$data_list = array();
    @$roleid = $_POST['user_id'];
    @$role = $_POST['user_role'];

    if ($role=='worker') {
    	$query=mysqli_query($con,"SELECT * FROM `complaint_db`WHERE worker_id='$roleid'");
    }else{
    	$query=mysqli_query($con,"SELECT * FROM `complaint_db` WHERE login_id='$roleid'");
    }
 	


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

