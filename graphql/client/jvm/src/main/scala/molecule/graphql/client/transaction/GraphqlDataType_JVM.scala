package molecule.graphql.client.transaction

import java.util.List as jList
import molecule.db.core.spi.DataType
import molecule.graphql.client

trait GraphqlDataType_JVM extends DataType {

  type Data = String
}
