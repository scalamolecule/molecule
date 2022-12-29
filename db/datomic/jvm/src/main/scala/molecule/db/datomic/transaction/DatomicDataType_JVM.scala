package molecule.db.datomic.transaction

import molecule.core.api.DataType
import java.util.{List => jList}

trait DatomicDataType_JVM extends DataType {

  type Data = jList[jList[AnyRef]]
}
