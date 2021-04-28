<?php
if(isset($_POST['SUBMIT'])){
    require "connect.php";
    $question = $_POST['question'];
    $ans1 = $_POST['ans1'];
    $ans2 = $_POST['ans2'];
    $ans3 = $_POST['ans3'];
    $ans4 = $_POST['ans4'];
    $correct = $_POST['correct'];
    $sql = "INSERT INTO questions(question,answer1,answer2,answer3,answer4,correctanswer) VALUES('$question','$ans1','$ans2','$ans3','$ans4','$correct')";
    mysqli_query($con, $sql);
    mysqli_close($con);
    $_POST['success']="Question has been added successfully. Thank you.";
}
?>

<!DOCTYPE html>
<html>
<head>
  <title>Questions API</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">
</head>
<body>
  <div class="container">
  <h1>Questions API for the Lukas app</h1>
  <h2>Add your question to the app</h2> <br/>
  <?php
    if(isset($_POST['success']))
    {
      echo '<p style="color: green;">'.$_POST["success"].'</p>';
      unset($_POST['success']);
    }
  ?>
  <h4>Quick guidelines for submiting the questions:</h4>
  <ul>
    <li>Questions should be related to anatomy or human biology</li>
    <li>Simple, not very detailed. Think about the questions level. It should be easily answerable by the first year student in med school</li>
    <li>If you have any questions or want to submit the photo next to the question, contact the developer right here: <a href="https://www.facebook.com/arlauskaslukas/">Lukas </a></li>
  </ul>
  <form action="insertion.php" method="POST">
    <div class="row">
      <div class="form-group col-md-12">
        <label for="question"><b>QUESTION</b></label>
        <input type="text" class="form-control" name="question">
      </div>
      <div class="form-group col-md-3">
        <label for="ans1"><b>ANSWER 1</b></label>
        <input type="text" class="form-control" name="ans1">
      </div>
      <div class="form-group col-md-3">
        <label for="ans2"><b>ANSWER 2</b></label>
        <input type="text" class="form-control" name="ans2">
      </div>
      <div class="form-group col-md-3">
        <label for="ans3"><b>ANSWER 3</b></label>
        <input type="text" class="form-control" name="ans3">
      </div>
      <div class="form-group col-md-3">
        <label for="ans4"><b>ANSWER 4</b></label> 
        <input type="text" class="form-control" name="ans4">
      </div>
      <div class="form-group col-md-3">
        <label for="correct"><b>CORRECT ANSWER</b></label>
        <input type="text" class="form-control" name="correct">
      </div>
      <div class="form-group col-md-12">
        <input type="submit" class="btn btn-primary" name="SUBMIT"value="SUBMIT QUESTION TO THE DATABASE">
      </div>
    </div>
  </form>
  </div>
</body>
</html>
