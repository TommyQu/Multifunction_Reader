<?php
$username=$_REQUEST["username"];
$nodetext=$_REQUEST["nodetext"];
$di = "onlineshelf/$username/$nodetext";
$handle = opendir($di);
$categories=array();
while($file = readdir($handle))
{
	$file=iconv("gbk","utf-8",$file);
    array_push($categories,$file);
}
echo json_encode($categories);
?>
