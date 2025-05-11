package molecule.db.datalog.datomic.transaction

import molecule.db.core.spi.DataType
import molecule.db.datalog.datomic

trait DatomicDataType_JS extends DataType {

  type Data = String // dummy not used since we serialize with boopickle
}
