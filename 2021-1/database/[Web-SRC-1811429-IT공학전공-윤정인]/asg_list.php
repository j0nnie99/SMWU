<html>
    <h2><a href="index.html">Home</a></h2>
</html>

<?php

ini_set("display_errors", 0);

    
$conn = mysqli_connect(
  '127.0.0.1', // 주소
  'root',
  'wjddls0307',
  'likelion'); // 데이터베이스 이름

$sql = "SELECT * FROM ASG, Members WHERE ASG.MeNum = Members.MeNum";
$result = mysqli_query($conn, $sql);

echo "<br><h1>과제 목록</h1><br>";

while($row = mysqli_fetch_array($result)){
    echo "[".$row['Week']."주차] ".$row['Link']."  | ".$row['MeName']."<br><hr>";
}

?>

