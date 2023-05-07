package molecule.sql.jdbc.transaction

import molecule.core.api.DataType

trait JdbcDataType_JS extends DataType {

  type Data = String // dummy not used since we serialize with boopickle
}
