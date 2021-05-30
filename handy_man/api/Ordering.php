<?php 

include 'connection.php';

if(isset($_POST['service_id']))
{  
    @$id = $_POST['lid'];
    @$esubj = $_POST['service_id'];
    @$scodee = $_POST['scode'];

    $query2=mysqli_query($con,"SELECT * FROM `job_db` WHERE job_id='$esubj'"); 
    $real2=mysqli_fetch_assoc($query2);
    @$title2=$real2['job_due_date'];
    @$title3=$real2['worker_id'];
  if ($title3==0) {
     if($scodee==0){
      $query = mysqli_query($con, "UPDATE `job_db` SET `worker_id`='$id',`status`='requested' WHERE job_id='$esubj'");
          mysqli_query($con,"UPDATE `chat_db` SET `worker_id`='$id' WHERE job_id='$esubj'");
       }
     else{
        $query = mysqli_query($con, "UPDATE `job_db` SET `worker_id`='$id',`worker2_id`='$id',`status`='requested',`urgency`=1 WHERE job_id='$esubj'");
          mysqli_query($con,"UPDATE `chat_db` SET `worker_id`='$id' WHERE job_id='$esubj'");
     }
  }else{
    $query = mysqli_query($con, "UPDATE `job_db` SET `worker2_id`='$id',`status`='doing' WHERE job_id='$esubj'");
        mysqli_query($con,"UPDATE `chat_db` SET `worker2_id`='$id' WHERE job_id='$esubj'");
  }

    
    @$datee = date("Y-m-d");

    mysqli_query($con,"INSERT INTO `work_calender_db`(`date`, `job_id`, `due_date`) VALUES ('$datee','$esubj','$title2')");
    
if($query)
{
    $response['success'] = 1;
    $response['message'] = "Order successfully placed!";

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