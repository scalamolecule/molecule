package molecule.db.datalog.datomic.transaction

import java.util.List as jList
import molecule.core.spi.DataType
import molecule.db.datalog.datomic

trait DatomicDataType_JVM extends DataType {

  type Data = jList[jList[AnyRef]]
}
