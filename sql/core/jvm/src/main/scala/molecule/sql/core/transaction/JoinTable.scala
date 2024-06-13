package molecule.sql.core.transaction

case class JoinTable(
  stmt: String,
  leftPath: List[String],
  rightPath: List[String],
  rightCounts: List[Int] = List.empty[Int]
)
