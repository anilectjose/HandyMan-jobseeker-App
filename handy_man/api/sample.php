<?php
include 'connection.php';

$data_list = array();


  @$id = 1;
    @$urole = 'user';
if($urole=='worker'){

      $query=mysqli_query($con,"SELECT * FROM `job_db` join customer_registration_db on customer_registration_db.login_id=job_db.customer_id WHERE job_id='$id' "); 
    $real=mysqli_fetch_assoc($query);

     @$title=$real['job_id']; 
     @$title2=$real['job_name'];
     @$title3=$real['job_location'];
     @$title4=$real['phone'];
     @$title5=$real['customer_name'];
     @$title6=$real['place']; 


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
}else{

        $query=mysqli_query($con,"SELECT * FROM `job_db` join worker_registration_db on worker_registration_db.login_id=job_db.worker_id WHERE job_id='$id' "); 
    $real=mysqli_fetch_assoc($query);

     @$title=$real['job_id']; 
     @$title2=$real['worker_name'];
     @$title3=$real['location'];
     @$title4=$real['phone'];
     @$title5=$real['qualification'];
     @$title6=$real['specialization']; 
     @$title7=$real['link']; 


     if($query)
      {
        $response['success'] =2;
        $response['message'] = "Updation successful";
        $response['sid']=$title;
        $response['name']=$title2;
        $response['stype']=$title3;
        $response['sdesc']=$title4;
        $response['sprice']=$title5;
        $response['sworkers']=$title6;
        $response['slink']=$title7;

        echo json_encode($response);
      }

     else
      {
       $response['success'] =0; 
       $response['result'] = "No data found!";
    
       echo json_encode($response);

      }
}




?>

