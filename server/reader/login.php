<?php
  session_start();
  $username=$_REQUEST["username"];
  $password=$_REQUEST["password"];
  $flag=0;
  $con=mysql_connect("localhost","root","20040132");
  if(!$con)
  {
	  die('Could not connect:'.mysql_error());
  }
  mysql_select_db("reader",$con);
  mysql_query("set names utf8");
  $result=mysql_query("select * from user");
  while($row=mysql_fetch_array($result))
  {
	  if(($row['username']==$username)&&($row['password']==$password))
	  {
		  $flag=1;
		  break;
	  }
  }
  if($flag==1)
  {
	  $_SESSION['username']=$username;
      echo "登录成功";
  }
  else
      echo "用户名密码错误";
  mysql_close($con);
?>