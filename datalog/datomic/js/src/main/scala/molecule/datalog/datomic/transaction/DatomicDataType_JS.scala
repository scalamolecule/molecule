package molecule.datalog.datomic.transaction

import molecule.core.spi.DataType

trait DatomicDataType_JS extends DataType {

  type Data = String // dummy not used since we serialize with boopickle
}
