<?php
  session_start();
  if(isset($_SESSION['username']))
    echo "已登录";
  else
    echo "未登录";
?>