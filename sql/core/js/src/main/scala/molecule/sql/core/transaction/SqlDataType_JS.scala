package molecule.sql.core.transaction

import molecule.core.api.DataType

trait SqlDataType_JS extends DataType {

  type Data = String // dummy not used since we serialize with boopickle
}
