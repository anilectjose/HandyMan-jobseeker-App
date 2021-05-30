<?php 

include 'connection.php';

if(isset($_POST['name']))
{
    @$ename = $_POST['name'];
    @$esubj = $_POST['subject'];
    @$erev = $_POST['reason'];
    @$roleid = $_POST['regno'];
    @$woid = $_POST['wid'];


    $query = mysqli_query($con, "INSERT INTO `complaint_db`( `name`, `subject`, `complaint`, `login_id`,`worker_id`) VALUES ('$ename','$esubj','$erev','$roleid','$woid')");
    
if($query)
{
    $response['success'] = 1;
    $response['message'] = "Complaint application submitted!";

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