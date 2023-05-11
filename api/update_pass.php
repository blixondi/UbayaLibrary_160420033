<?php

header("Access-Control-Allow-Origin: *");
extract($_POST);
$c = new mysqli("localhost", "noinheim_nmp", "NoinMP16!", "noinheim_ubayalib");
$sql = "UPDATE user SET password = ? WHERE id = ?";
$stmt = $c->prepare($sql);
$stmt->bind_param('ss', $password, $id);
$stmt->execute();
if ($stmt->affected_rows > 0) {
    $arr = ["result" => "success"];
} else {
    $arr = ["result" => "fail", "Error" => $c->error];
}
echo json_encode($arr);
