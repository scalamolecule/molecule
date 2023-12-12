package molecule.document.mongodb.transaction

import molecule.core.spi.DataType
import org.bson.BsonDocument

trait DataType_JVM_mongodb extends DataType {
  type Data = BsonDocument
}

object DataType_JVM_mongodb extends DataType_JVM_mongodb
