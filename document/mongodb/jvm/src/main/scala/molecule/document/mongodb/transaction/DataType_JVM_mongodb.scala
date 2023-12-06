package molecule.document.mongodb.transaction

import java.util
import molecule.core.spi.DataType
import org.bson.BsonDocument

trait DataType_JVM_mongodb extends DataType {

//  type Action = String
//  type Collections = BsonDocument

//  type Data = (Action, Collections)
  type Data = BsonDocument
//  type Data2 = BsonDocument
}

object DataType_JVM_mongodb extends DataType_JVM_mongodb
