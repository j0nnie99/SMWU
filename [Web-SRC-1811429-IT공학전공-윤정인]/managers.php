<html>
    <h2><a href="index.html">Home</a></h2>
</html>

<?php

ini_set('error_reporting','E_ALL ^ E_NOTICE');


$conn = mysqli_connect(
  '127.0.0.1', // 주소
  'root',
  'wjddls0307',
  'likelion'); // 데이터베이스 이름

$sql = "SELECT * FROM Managers";
$result = mysqli_query($conn, $sql);

echo "<br><h1>운영진 목록</h1><br>";

while($row = mysqli_fetch_array($result)){
    echo "<h2>".$row[MaName]."</h2> 전공: ".$row[Major]."<br> 학번: ".$row[ID]."<br> 담당 업무: ".$row['work']."<hr>";        
}

?>

