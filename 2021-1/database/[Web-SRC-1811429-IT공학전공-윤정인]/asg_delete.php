<?php

ini_set('error_reporting','E_ALL ^ E_NOTICE');

$conn = mysqli_connect(
  '127.0.0.1', // 주소
  'root',
  'wjddls0307',
  'likelion'); // 데이터베이스 이름

$sql = "DELETE FROM ASG WHERE Week='$_POST[w]' AND MeNum=$_POST[mn]";
$result = mysqli_query($conn, $sql);

echo "<h3>과제가 삭제되었습니다!</h3>"

?>

<html>
    <h2><a href="index.html">Home</a></h2>
</html>