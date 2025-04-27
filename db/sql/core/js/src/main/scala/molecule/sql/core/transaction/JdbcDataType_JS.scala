package molecule.sql.core.transaction

import molecule.core.spi.DataType

trait JdbcDataType_JS extends DataType {

  type Data = String // dummy not used on JS side since we serialize with boopickle
}
