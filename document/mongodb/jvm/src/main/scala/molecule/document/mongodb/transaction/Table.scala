package molecule.document.mongodb.transaction

import java.sql.{PreparedStatement => PS}
import molecule.document.mongodb.transaction.SqlDataType_JVM._

case class Table(
  refPath: List[String],
  stmt: String,
  ps: PS,
  populatePS: (PS, IdsMap, RowIndex) => Unit = null
)
