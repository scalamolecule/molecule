package molecule.sql.jdbc.transaction

import java.sql.{PreparedStatement => PS}
import molecule.core.spi.DataType

trait JdbcDataType_JVM extends DataType {

  // (level, refPath, table/ns) -> Array(ids)
  type InsertIds = Map[(Int, List[String], String), Array[Long]]

  type RowIndex = Int

  // Setter to mutate PreparedStatement for each insertion
  type Setter = (PS, InsertIds, RowIndex) => Unit

  type Data = List[Resolver]
}
