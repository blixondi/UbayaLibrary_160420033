<?php

header("Access-Control-Allow-Origin: *");
extract($_POST);
$c = new mysqli("localhost", "noinheim_nmp", "NoinMP16!", "noinheim_ubayalib");
$data = [];
$erro = "not found";


$sql = "SELECT * FROM user WHERE username=? AND password=?";
$stmt = $c->prepare($sql);
$stmt->bind_param('ss', $username, $password);
$stmt->execute();
$result = $stmt->get_result();
$data = mysqli_fetch_assoc($result);

if ($data != "") {
    echo json_encode($data);
} else {
    echo json_encode(new stdClass());
}

$stmt->close();
$c->close();
