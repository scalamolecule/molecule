package molecule.sql.core.transaction

import molecule.core.spi.DataType
import molecule.sql.core.transaction.strategy.SqlAction

trait SqlDataType_JVM extends DataType {
  type Data = SqlAction
}
