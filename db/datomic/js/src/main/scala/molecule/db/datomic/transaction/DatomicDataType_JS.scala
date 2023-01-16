package molecule.db.datomic.transaction

import molecule.core.api.DataType

trait DatomicDataType_JS extends DataType {

  type Data = String // dummy not used since we serialize with boopickle
}
