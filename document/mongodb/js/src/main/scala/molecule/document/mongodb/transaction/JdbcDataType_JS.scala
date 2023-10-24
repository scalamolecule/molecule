package molecule.document.mongodb.transaction

import molecule.core.spi.DataType

trait JdbcDataType_JS extends DataType {

  type Data = String // dummy not used since we serialize with boopickle
}
