<?php 

include 'connection.php';

if (isset($_POST["username"]))
{
  @$usrname = $_POST['username'];
  @$epassword = $_POST['password'];

  $sql=mysqli_query($con,"SELECT * FROM role_db WHERE email='$usrname' AND password='$epassword'");

 
	
if(mysqli_num_rows($sql) >0)
{
	$userdata = mysqli_fetch_assoc($sql);

	$response['success'] = 1;
	$response['message'] = "Your login is successfully completed!";
	$response['data']	 = $userdata;

	echo json_encode($response);

}
else
{
	$response['success'] = 0;
	$response['message'] = "Invalid username or password!";

	echo json_encode($response);

}



}
else
{
	$response['success'] = 0;
	$response['message'] = "No Access!";

	echo json_encode($response);

}


?>