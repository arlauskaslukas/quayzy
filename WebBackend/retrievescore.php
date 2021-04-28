<?php 
require "connect.php";
$uname=$_POST['username'];
$sql = "SELECT username,scoresum FROM userinfo WHERE username='$uname'";
$result = mysqli_query($con,$sql);
if(!mysqli_num_rows($result)>0)
{
    $status = 'failed';
    echo json_encode(array('response' => $status));
}
else
{
    $row = mysqli_fetch_assoc($result);
    $status = 'ok';
    echo json_encode(array('response' => $status, 'username'=>$row['username'], 'scoresum'=>$row['scoresum']));
}
mysqli_close($con);
?>