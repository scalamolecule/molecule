package molecule.db.common.transaction

import molecule.db.common.spi.DataType
import molecule.db.common.transaction.strategy.SqlAction

trait SqlDataType_JVM extends DataType {
  type Data = SqlAction
}
