<?php
require 'connect.php';
$sql = "SELECT username,scoresum FROM userinfo ORDER BY scoresum DESC LIMIT 10";
$result = mysqli_query($con,$sql);
$arr = array();
while($row = mysqli_fetch_assoc($result))
{
array_push($arr, $row);
}
echo json_encode($arr);

?>