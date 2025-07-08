package molecule.rpc.openapi.transaction

import java.util.{List => jList}
import molecule.db.common.spi.DataType

trait OpenapiDataType_JVM extends DataType {

  type Data = String
}
