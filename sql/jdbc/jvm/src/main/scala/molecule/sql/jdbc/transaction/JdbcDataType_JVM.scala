package molecule.sql.jdbc.transaction

import molecule.core.api.DataType

trait JdbcDataType_JVM extends DataType {

  // SQL statement for each related table
  type SqlStmt = String

  // Setter to mutate PreparedStatement for each insertion
  // (level, table/ns) -> Array(ids)
  type Setter = (java.sql.PreparedStatement, Map[(Int, String, String), Array[Long]], Int) => Unit

  type Data = List[Resolver]
}
