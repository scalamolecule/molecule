package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}

case class JoinTable(
  stmt: String,
  ps: PS,
  leftPath: List[String],
  rightPath: List[String],
  rightCounts: List[Int] = List.empty[Int]
)
