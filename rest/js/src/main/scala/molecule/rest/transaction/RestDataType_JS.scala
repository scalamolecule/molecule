package molecule.rest.transaction

import molecule.db.core.spi.DataType

trait RestDataType_JS extends DataType {

  type Data = String // dummy not used on JS side since we serialize with boopickle
}
