package molecule.rest.transaction

import java.util.{List => jList}
import molecule.db.common.spi.DataType

trait RestDataType_JVM extends DataType {

  type Data = String
}
