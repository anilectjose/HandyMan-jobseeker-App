<?php 

include 'db2.php';

$sql=mysqli_query($con,"SELECT * from notification_db");

while ($row=mysqli_fetch_assoc($sql)) {
	echo $row["content"];
}
?>




