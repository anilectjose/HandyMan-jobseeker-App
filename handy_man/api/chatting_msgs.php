<?php
include 'connection.php';

$data_list = array();
    @$roleid = $_POST['user_id'];
    @$datee = $_POST['chatid'];
    @$rolle = $_POST['role_id'];
 
 if ($rolle=='worker') {
 	$query=mysqli_query($con,"SELECT * FROM `chat_db` join message_db on chat_db.chat_id=message_db.chat_id where chat_db.worker_id='$roleid' or chat_db.worker2_id='$roleid' and chat_db.chat_id='$datee' ORDER BY message_id DESC LIMIT 10 ");
 }
 else{
 	$query=mysqli_query($con,"SELECT * FROM `chat_db` join message_db on chat_db.chat_id=message_db.chat_id where chat_db.customer_id='$roleid' and chat_db.chat_id='$datee' ORDER BY message_id DESC LIMIT 10 ");
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

