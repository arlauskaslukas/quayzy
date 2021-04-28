<?php
require 'connect.php';
$username = $_GET['username'];
$password = $_GET['password'];
$sql = "SELECT username FROM userinfo WHERE username='$username' AND password='$password'";
$result = mysqli_query($con,$sql);
if(!mysqli_num_rows($result)>0)
{
  $status = 'failed';
  echo json_encode(array('response' => $status));
}
else {
  $row = mysqli_fetch_assoc($result);
  $name = $row['username'];
  $status = 'ok';
  echo json_encode(array("response"=>$status, "name"=>$name));
}
mysqli_close($con);
?>
