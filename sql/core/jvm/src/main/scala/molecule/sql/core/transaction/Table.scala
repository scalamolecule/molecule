package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}
import molecule.sql.core.transaction.JdbcDataType_JVM._

case class Table(
  refPath: List[String],
  stmt: String,
  ps: PS,
  populatePS: (PS, IdsMap, RowIndex) => Unit = null
)
