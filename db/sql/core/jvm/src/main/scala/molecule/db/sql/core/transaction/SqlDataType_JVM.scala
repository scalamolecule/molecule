package molecule.db.sql.core.transaction

import molecule.db.core.spi.DataType
import molecule.db.sql.core.transaction.strategy.SqlAction

trait SqlDataType_JVM extends DataType {
  type Data = SqlAction
}
