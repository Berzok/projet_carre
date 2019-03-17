<html>
<?php
$connexion = mysqli_connect("localhost:3306/", "g7", "g7");

if($mysqli->connect_error) {
  exit('Could not connect');
}
mysqli_select_db($connexion, "g7");

$id = "";
$moves = "";

mysqli_select_db($connexion, "g7");
$sql = "SELECT id_game, nb_move FROM game";
$result = mysqli_query($con,$sql);

echo "<tr>";
echo "<td>" . $id . "</td>";
echo "<td>" . $moves . "</td>";
echo "</tr>";
mysqli_close($connexion);
?>
</html>