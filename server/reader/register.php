<?php
  $username=$_REQUEST["username"];
  $password=$_REQUEST["password"];
  $sex=$_REQUEST["sex"];
  $category=$_REQUEST["category"];
  $mailbox=$_REQUEST["mailbox"];
  $con=mysql_connect("localhost","root","20040132");
  if(!$con)
  {
	  die('Could not connect:'.mysql_error());
  }
  mysql_select_db("reader",$con);
  mysql_query("set names utf8");
  $sql="insert into user (username,password,sex,category,mailbox) values ('$username','$password','$sex','$category','$mailbox')";
  if (!mysql_query($sql,$con))
  {
	  die('Error: ' . mysql_error());
  }
  echo "注册成功";
  $path="onlineshelf/$username";
  mkdir($path,0777);
  $path="onlineshelf/$username/默认书架";
  mkdir(iconv('utf-8', 'gbk', $path),0777);
  mysql_close($con);
?>