<?php
  $username=$_REQUEST["username"];
  $shelfname=$_REQUEST{"shelfname"};
 // $shelfname=iconv("gbk","utf-8",$shelfname);
  if ($_FILES["file"]["error"] > 0)
  {
	  echo "创建文件错误";
  }
  else
  {
//	$_FILES["file"]["name"]=iconv("gbk","utf-8",$_FILES["file"]["name"]);
//	echo "Upload: " . $_FILES["file"]["name"] . "<br />";
//    echo "Type: " . $_FILES["file"]["type"] . "<br />";
//    echo "Size: " . ($_FILES["file"]["size"] / 1024) . " Kb<br />";
 //   echo "Temp file: " . $_FILES["file"]["tmp_name"] . "<br />";
   // $_FILES["file"]["name"]=iconv("utf-8","gbk",$_FILES["file"]["name"]);
    //$newfilename=$_FILES["file"]["name"];
    //$newfilename=iconv("gbk","utf-8",$newfilename);
    $shelfname=iconv("utf-8","gbk",$shelfname);
    $a="onlineshelf/".$username."/".$shelfname."/".$_FILES["file"]["name"];
	if (file_exists($a))
    {
       echo "同名书籍已存在";
    }
    else
    {
  //    $newpath=iconv("gbk","utf-8",$newpath);

   //   $newpath=iconv("utf-8","gbk",$newpath);
      move_uploaded_file($_FILES["file"]["tmp_name"],$a);
      //echo $a;
	//  echo "Stored in: " . "onlineshelf/$username/$shelfname" . $_FILES["file"]["name"];
      echo "上传成功";
    }
	 // if(file_2exists("onlineshelf/$username/$shelfname"))
  }
?>