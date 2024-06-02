package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}
import molecule.sql.core.transaction.SqlDataType_JVM._

case class Table(
  refPath: List[String],
  stmt: String,
  populatePS: (PS, IdsMap, RowIndex) => Unit = null,
  accIds: Boolean = false,
  useAccIds: Boolean = false,
  curIds: List[Long] = Nil,
  upsertStmt: Option[List[Long] => String] = None,
)
