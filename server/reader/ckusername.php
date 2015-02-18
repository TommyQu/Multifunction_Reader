<?php
  $username=$_REQUEST["username"];
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
	  if($row['username']==$username)
	  {
		  $flag=1;
		  break;
	  }
  }
  if($flag==0)
      echo "用户名不存在";
  else
      echo "用户名已存在";
  mysql_close($con);
?>