package molecule.datomic.transaction

import java.util.{List => jList}
import molecule.core.api.DataType

trait DatomicDataType_JVM extends DataType {

  type Data = jList[jList[AnyRef]]
}
