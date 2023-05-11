<?php

header("Access-Control-Allow-Origin: *");

$c = new mysqli("localhost", "noinheim_nmp", "NoinMP16!", "noinheim_ubayalib");
$data = [];

if (empty($_GET)) {
    $sql = "SELECT * from buku";

    $stmt = $c->prepare($sql);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        while ($r = mysqli_fetch_assoc($result)) {
            array_push($data, $r);
        }
    }
} else {
    $sql = "SELECT * from buku WHERE id = " . $_GET['id'];

    $stmt = $c->prepare($sql);
    $stmt->execute();
    $result = $stmt->get_result();
    $data = mysqli_fetch_assoc($result);
}

echo json_encode($data);

$stmt->close();
$c->close();
