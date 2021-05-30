<?php 

include 'connection.php';

if(isset($_POST['name']))
{
    @$ename = $_POST['name'];
    @$esubj = $_POST['place'];
    @$erev = $_POST['desc'];
    @$duedate = $_POST['due'];
    @$cusid = $_POST['regno'];
    @$datee = date("Y-m-d");


    $query = mysqli_query($con, "INSERT INTO `job_db`(`job_name`, `job_desc`, `job_location`, `job_date`, `job_due_date`, `customer_id`, `worker2_id`, `worker_id`, `status`) VALUES ('$ename','$erev','$esubj','$datee','$duedate','$cusid',0,0,'pending')");
    $roleid =mysqli_insert_id($con);

    mysqli_query($con,"INSERT INTO `chat_db` (`customer_id`,`job_id`) VALUES ('$cusid','$roleid')");
    
if($query)
{
    $response['success'] = 1;
    $response['message'] = "Job application submitted!";

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