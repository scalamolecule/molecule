package molecule.rpc.openapi.transaction

import java.util.{List => jList}
import molecule.core.spi.DataType

trait OpenapiDataType_JVM extends DataType {

  type Data = String
}
