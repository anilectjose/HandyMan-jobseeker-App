<?php
include 'connection.php';

$data_list = array();

if(isset($_POST['log_id']))
{
	@$id = $_POST['log_id'];
    @$erole = $_POST['role'];


  if ($erole=='worker') 
 {
    $query=mysqli_query($con,"SELECT * FROM `role_db` WHERE role_id='$id' "); 
    $query2=mysqli_query($con,"SELECT * FROM `worker_registration_db` WHERE login_id='$id' ");
    $real=mysqli_fetch_assoc($query);
    $real2=mysqli_fetch_assoc($query2);
 }
 else
 {
    $query=mysqli_query($con,"SELECT * FROM `role_db` WHERE role_id='$id' "); 
    $query3=mysqli_query($con,"SELECT * FROM `customer_registration_db` WHERE login_id='$id' "); 
    $real=mysqli_fetch_assoc($query);
    $real3=mysqli_fetch_assoc($query3);
 }

 if ($erole=='worker') 
   {

     @$title2=$real2['worker_name'];
     @$title3=$real2['phone'];
     @$title4=$real['email'];
     @$title5=$real2['location'];

        @$work=mysqli_query($con,"SELECT COUNT(worker_id) FROM job_db WHERE worker_id='$id' or worker2_id='$id' and status='completed'");
        @$work2=mysqli_query($con,"SELECT COUNT(worker_id) FROM job_db WHERE worker_id='$id' or worker2_id='$id' and status!='completed'");
        @$comp=mysqli_query($con,"SELECT COUNT(login_id) FROM `complaint_db` where worker_id='$id'");
        @$sub3=mysqli_fetch_assoc($comp);
     @$title6=$sub3['COUNT(login_id)'];
        @$sub1=mysqli_fetch_assoc($work);
        @$sub2=mysqli_fetch_assoc($work2);
     @$title7=$sub1['COUNT(worker_id)'];
     @$title8=$sub2['COUNT(worker_id)'];

     if($query)
      {
        $response['success'] =1;
        $response['message'] = "Updation successful";
        $response['name']=$title2;
        $response['phonenum']=$title3;
        $response['email']=$title4;
        $response['plce']=$title5;
        $response['wcount']=$title7;
        $response['agee']=$title8;
        $response['ccount']=$title6;

        echo json_encode($response);
      }

     else
      {
       $response['success'] =0; 
       $response['result'] = "No data found!";
    
       echo json_encode($response);

      }
   }
 else if ($erole=='user') 
   {

     @$title2=$real3['customer_name'];
     @$title3=$real3['phone'];
     @$title4=$real['email'];
     @$title5=$real3['place'];

        @$work=mysqli_query($con,"SELECT COUNT(customer_id) FROM job_db WHERE customer_id='$id' and status='completed'");
        @$work2=mysqli_query($con,"SELECT COUNT(customer_id) FROM job_db WHERE customer_id='$id' and status!='completed'");
        @$comp=mysqli_query($con,"SELECT COUNT(login_id) FROM `complaint_db` WHERE login_id='$id'");
        @$sub1=mysqli_fetch_assoc($work);
        @$sub3=mysqli_fetch_assoc($work2);
        @$sub2=mysqli_fetch_assoc($comp);
     @$title6=$sub1['COUNT(customer_id)'];
     @$title7=$sub2['COUNT(login_id)'];
     @$title8=$sub3['COUNT(customer_id)'];

     if($query)
      {
        $response['success'] =2;
        $response['message'] = "Updation successful";
        $response['name']=$title2;
        $response['phonenum']=$title3;
        $response['email']=$title4;
        $response['plce']=$title5;
        $response['ocount']=$title6;
        $response['ccount']=$title7;
        $response['cage']=$title8;

        echo json_encode($response);
      }

     else
      {
       $response['success'] =0; 
       $response['result'] = "No data found!";
    
       echo json_encode($response);

      }
   }
}
else
   {
	$response['success'] =0; 
	$response['result'] = "No Access!";
    
	echo json_encode($response);
   }

?>

