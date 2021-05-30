<?php 

include 'connection.php';

if(isset($_POST['chatid']))
{
    @$ename = $_POST['chatid'];
    @$esubj = $_POST['subject'];
    @$roleid = $_POST['regno'];


    $query = mysqli_query($con, "INSERT INTO `message_db`(`chat_id`, `message`, `login_id`) VALUES ('$ename','$esubj','$roleid')");
    
if($query)
{
    $response['success'] = 1;
    $response['message'] = "Msg
     submitted!";

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