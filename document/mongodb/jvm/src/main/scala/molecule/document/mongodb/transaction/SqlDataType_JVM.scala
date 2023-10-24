package molecule.document.mongodb.transaction

import java.sql.{PreparedStatement => PS}
import molecule.core.spi.DataType
import org.bson.BsonDocument
import java.util.{List => jList}

trait SqlDataType_JVM extends DataType {

  // refPath -> Array(ids)
  type IdsMap = Map[List[String], List[Long]]

  type RowIndex = Int

  // Setter to mutate PreparedStatement for each insertion
  type Setter = (PS, IdsMap, RowIndex) => Unit

//  type Data = (List[Table], List[JoinTable])
  // Collection name, java list of bson documents
  type Data = (String, jList[BsonDocument])
}

object SqlDataType_JVM extends SqlDataType_JVM
