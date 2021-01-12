<?php
$username = "root";
$password = "";
$host = "localhost";
$dbname = "android_login";

$options = array(PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8');

try {
$db = new PDO("mysql:host={$host};dbname={$dbname}", $username, $password);

$db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch(PDOException $ex) {
die("Failed to connect to the database: " . $ex->getMessage());
} 
header('Content-Type: application/json; charset=utf-8');
?>
