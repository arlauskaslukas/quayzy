<?php 
require "connect.php";
$uname=$_POST['username'];
$score=$_POST['score'];
$sql = "UPDATE userinfo SET scoresum='$score' WHERE username='$uname'";
if(mysqli_query($con,$sql))
{
    $status = 'ok';
}
else
{
    $status = 'failed';
}
echo $status;
mysqli_close($con);
?>