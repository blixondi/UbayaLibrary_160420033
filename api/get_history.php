<?php
header("Access-Control-Allow-Origin: *");
extract($_POST);
$c = new mysqli("localhost", "noinheim_nmp", "NoinMP16!", "noinheim_ubayalib");
$data = [];

$sql = "SELECT b.judul, b.gambar, h.tanggal, h.status
FROM buku b
INNER JOIN history h on b.id = h.buku_id
WHERE h.user_id = ?";

$stmt = $c->prepare($sql);
$stmt->bind_param('i', $id);
$stmt->execute();
$result = $stmt->get_result();
if ($result->num_rows > 0) {
    while ($r = mysqli_fetch_assoc($result)) {
        array_push($data, $r);
    }
}
echo json_encode($data);
$stmt->close();
$c->close();
