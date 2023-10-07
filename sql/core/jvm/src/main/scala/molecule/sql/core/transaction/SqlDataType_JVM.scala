package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}
import molecule.core.spi.DataType

trait JdbcDataType_JVM extends DataType {

  // refPath -> Array(ids)
  type IdsMap = Map[List[String], List[Long]]

  type RowIndex = Int

  // Setter to mutate PreparedStatement for each insertion
  type Setter = (PS, IdsMap, RowIndex) => Unit

  type Data = (List[Table], List[JoinTable])
}

object JdbcDataType_JVM extends JdbcDataType_JVM