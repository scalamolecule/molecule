package molecule.db.datalog.datomic.transaction

import java.util.List as jList
import molecule.db.core.spi.DataType
import molecule.db.datalog.datomic

trait DatomicDataType_JVM extends DataType {

  type Data = jList[jList[AnyRef]]
}
