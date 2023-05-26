package molecule.sql.jdbc.transaction

import molecule.core.spi.DataType

trait JdbcDataType_js extends DataType {

  type Data = String // dummy not used since we serialize with boopickle
}
