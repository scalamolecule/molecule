package molecule.sql.jdbc.transaction

import java.util.{List => jList}
import molecule.core.api.DataType

trait JdbcDataType_JVM extends DataType {

//  type Data = jList[jList[AnyRef]]
  type Data = java.sql.Statement
}
