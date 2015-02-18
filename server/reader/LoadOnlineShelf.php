<?php
$username=$_REQUEST["username"];
$di = "onlineshelf/$username/";
$handle = opendir($di);
$categories=array();
while($file = readdir($handle))
{
	$file=iconv("gbk","utf-8",$file);
    array_push($categories,$file);
}
echo json_encode($categories);
?>
