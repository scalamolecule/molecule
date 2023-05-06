package molecule.sql.core.transaction

import java.util.{List => jList}
import molecule.core.api.DataType

trait SqlDataType_JVM extends DataType {

//  type Data = jList[jList[AnyRef]]
  type Data = java.sql.Statement
}
