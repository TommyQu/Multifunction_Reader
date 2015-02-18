<?php
  $username=$_REQUEST["username"];
  $oldname=$_REQUEST["oldname"];
  $newname=$_REQUEST["newname"];
  $di="onlineshelf/$username";
  $handle = opendir($di);
  while($file = readdir($handle))
  {
	$file=iconv("gbk","utf-8",$file);
	if($file==$oldname)
	{
		if(rename("$di/$file","$di/$newname"))
		   echo "修改成功";
		else
		   echo "修改失败";
		break;
	}
  }
?>