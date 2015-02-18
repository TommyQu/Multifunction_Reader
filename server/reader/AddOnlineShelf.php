<?php
  $username=$_REQUEST["username"];
  $newshelf=$_REQUEST["newshelf"];
  $newpath="onlineshelf/$username/$newshelf";
  if(file_exists($newpath))
    echo "书架已存在";
  else
  {
	  mkdir(iconv('utf-8', 'gbk', $newpath),0777);
	  echo "添加成功";
  }
?>