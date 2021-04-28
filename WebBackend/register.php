<?php
	require 'connect.php';
	$email = $_GET['email'];
	$username = $_GET['username'];
	$password = $_GET['password'];
	$sql = "SELECT * FROM userinfo WHERE username='$username'";
	$result = mysqli_query($con, $sql);
	if(mysqli_num_rows($result) > 0)
	{
		$status = 'exists';
	}
	else
	{
		$sql = "INSERT INTO userinfo(username,email,password) VALUES('$username','$email','$password')";
		if(mysqli_query($con, $sql))
		{
			$status = 'ok';
		}
		else
		{
			$status = 'err';
		}
	}
	echo json_encode(array("response"=>$status));
	mysqli_close($con);
?>
