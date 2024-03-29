package molecule.datalog.datomic.transaction

import java.util.{List => jList}
import molecule.core.spi.DataType

trait DatomicDataType_JVM extends DataType {

  type Data = jList[jList[AnyRef]]
}
