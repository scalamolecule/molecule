package molecule.sql.jdbc.transaction

import molecule.core.api.DataType

trait JdbcDataType_JVM extends DataType {

  // SQL statement for each related table
  type Stmt = String

  // Setter to mutate PreparedStatement for each statement/row
  type Setter = (java.sql.PreparedStatement, List[Long]) => Unit

  type Data = (List[Stmt], List[Setter])
}
