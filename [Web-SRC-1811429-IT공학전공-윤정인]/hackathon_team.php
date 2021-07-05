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

$sql = "SELECT * FROM Team, Managers, Members WHERE Team.Manager = Managers.MaNum AND Team.TNum = Members.TNum" ;
$result = mysqli_query($conn, $sql);

echo "<br><h1>2021 해커톤 팀 목록</h1><br>";

while($row = mysqli_fetch_array($result)){
    echo "<h2>".$row[TName]."</h2> 설명: ".$row[Info]."<br>팀원: ".$row[MeName]."<br>담당 운영진: ".$row[MaName]."<br><hr>";        
}

?>

