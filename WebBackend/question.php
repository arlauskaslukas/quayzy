<?php
    require "connect.php";
    $id = $_GET['id'];
    $sql = "SELECT * FROM questions WHERE id='$id'";
    $result = mysqli_query($con,$sql);
    if(!mysqli_num_rows($result)>0)
    {
        $status = 'failed';
        echo json_encode(array('response' => $status));
    }
    else {
        $row = mysqli_fetch_assoc($result);
        $status = 'ok';
        echo json_encode(array("response"=>$status, "question"=>$row['question'], "answer1"=>$row['answer1'],
        "answer2"=>$row['answer2'],"answer3"=>$row['answer3'],"answer4"=>$row['answer4'],"correctanswer"=>$row['correctanswer']));
    }
    mysqli_close($con);
?>