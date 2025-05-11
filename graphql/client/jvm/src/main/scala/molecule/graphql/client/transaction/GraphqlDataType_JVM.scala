package molecule.graphql.client.transaction

import java.util.{List => jList}
import molecule.db.core.spi.DataType

trait GraphqlDataType_JVM extends DataType {

  type Data = String
}
