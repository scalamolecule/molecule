package molecule.document.mongodb.transaction

import java.util
import molecule.core.spi.DataType
import org.bson.BsonDocument

trait DataType_JVM_mongodb extends DataType {

  private type CollectionName = String
  private type Action = String
  private type Documents = util.List[BsonDocument]

//  type Data = (CollectionName, Action, Documents)
  type Data = (CollectionName, Documents)
}

object DataType_JVM_mongodb extends DataType_JVM_mongodb
