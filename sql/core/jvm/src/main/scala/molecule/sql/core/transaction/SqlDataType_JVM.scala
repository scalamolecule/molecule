package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}
import molecule.core.spi.DataType

trait SqlDataType_JVM extends DataType {

  // refPath -> List(ids)
  type IdsMap = Map[List[String], List[Long]]

  type RowIndex = Int

  // Setter to mutate PreparedStatement for each insertion
  type Setter = (PS, IdsMap, RowIndex) => Unit

  type Data = (List[Table], List[JoinTable])
}

object SqlDataType_JVM extends SqlDataType_JVM
