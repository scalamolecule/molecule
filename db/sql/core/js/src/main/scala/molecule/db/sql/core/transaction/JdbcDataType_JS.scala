package molecule.db.sql.core.transaction

import molecule.db.core.spi.DataType

trait JdbcDataType_JS extends DataType {

  type Data = String // dummy not used on JS side since we serialize with boopickle
}
