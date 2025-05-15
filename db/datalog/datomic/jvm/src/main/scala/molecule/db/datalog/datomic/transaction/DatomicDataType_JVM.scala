package molecule.db.datalog.datomic.transaction

import java.util.List as jList
import molecule.db.core.spi.DataType

trait DatomicDataType_JVM extends DataType {

  type Data = jList[jList[AnyRef]]
}
