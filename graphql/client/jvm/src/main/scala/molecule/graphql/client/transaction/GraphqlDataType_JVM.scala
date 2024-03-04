package molecule.graphql.client.transaction

import java.util.{List => jList}
import molecule.core.spi.DataType

trait GraphqlDataType_JVM extends DataType {

  type Data = String
}
