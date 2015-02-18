<?php
  $username=$_REQUEST["username"];
  $di="onlineshelf/$username";
  if(filesize($di)!=0)
  {
  $handle = opendir($di);
  while($file = readdir($handle))
  {
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
  }
  if(flag==1)
  {
//    $new="onlineshelf/$username/默认书架";
//    $new=iconv("utf-8","gbk",$new);
//    mkdir($new);
    echo "清空成功";
  }
  else
  {
    echo "清空失败";
  }
  }

?>