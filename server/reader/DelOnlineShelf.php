<?php
  $username=$_REQUEST["username"];
  $nodetext=$_REQUEST["nodetext"];
  $flag=0;
  $di="onlineshelf/$username";
  $handle = opendir($di);
  while($file = readdir($handle))
  {
	$file=iconv("gbk","utf-8",$file);
	if($file==$nodetext)
	{
		$file=iconv("utf-8","gbk",$file);
		$handle2=opendir("$di/$file");
		while($f=readdir($handle2))
		{
			if($f!="."&&$f!="..")
			{
				if(!unlink("$di/$file/$f"))
				{
				  $flag=1;
				}
			}
		}
		if(!rmdir("$di/$file"))
		{
			$flag=1;
		}
		else
		{
			if($flag==0)
				echo "删除成功";
			else
				echo "删除失败";
			break;
		}
	}
  }
?>