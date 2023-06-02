package molecule.sql.jdbc.transaction

import java.sql.{PreparedStatement => PS}
import molecule.sql.jdbc.marshalling.JdbcRpcJVM._

case class TableInsert(
  refPath: List[String],
  stmt: String,
  ps: PS,
  populatePS: (PS, IdsMap, RowIndex) => Unit = null
)

case class JoinTableInsert(
  joinRefPath: List[String],
  stmt: String,
  ps: PS,
  leftPath: List[String],
  rightPath: List[String],
  rightCounts: List[Int] = List.empty[Int]
)
