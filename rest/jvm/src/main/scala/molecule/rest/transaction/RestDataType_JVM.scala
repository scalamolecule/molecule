package molecule.rest.transaction

import java.util.{List => jList}
import molecule.core.spi.DataType

trait RestDataType_JVM extends DataType {

  type Data = String
}
