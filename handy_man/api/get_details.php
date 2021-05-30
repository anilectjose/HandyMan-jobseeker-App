<?php
include 'connection.php';

$data_list = array();

if(isset($_POST['service_id']))
{
	@$id = $_POST['service_id'];

    $query=mysqli_query($con,"SELECT * FROM `job_db` WHERE job_id='$id' "); 
    $real=mysqli_fetch_assoc($query);

     @$title=$real['job_id']; 
     @$title2=$real['job_name'];
     @$title3=$real['job_location'];
     @$title4=$real['job_desc'];
     @$title5=$real['job_date'];
     @$title6=$real['job_due_date']; 


     if($query)
      {
        $response['success'] =1;
        $response['message'] = "Updation successful";
        $response['sid']=$title;
        $response['name']=$title2;
        $response['stype']=$title3;
        $response['sdesc']=$title4;
        $response['sprice']=$title5;
        $response['sworkers']=$title6;

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
	$response['success'] =0; 
	$response['result'] = "No Access!";
    
	echo json_encode($response);
   }

?>

