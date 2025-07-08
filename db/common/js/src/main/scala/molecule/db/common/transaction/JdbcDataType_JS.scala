package molecule.db.common.transaction

import molecule.db.common.spi.DataType

trait JdbcDataType_JS extends DataType {

  type Data = String // dummy not used on JS side since we serialize with boopickle
}
