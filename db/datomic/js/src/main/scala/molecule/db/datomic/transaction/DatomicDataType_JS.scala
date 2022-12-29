package molecule.db.datomic.transaction

import java.util.{List => jList}
import molecule.core.api.DataType

trait DatomicDataType_JS extends DataType {

  type Data = String // edn string
}
