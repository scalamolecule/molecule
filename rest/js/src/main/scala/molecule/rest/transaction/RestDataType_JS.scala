package molecule.rest.transaction

import molecule.core.spi.DataType

trait RestDataType_JS extends DataType {

  type Data = String // dummy not used on JS side since we serialize with boopickle
}
