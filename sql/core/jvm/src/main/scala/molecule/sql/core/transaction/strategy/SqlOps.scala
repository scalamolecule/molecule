package molecule.sql.core.transaction.strategy

import java.sql.Connection
import scala.collection.mutable.ListBuffer

class SqlOps extends SqlActionBase {

  def getIds(sqlConn: Connection, table: String, ps: PS): List[Long] = {
    // Execute incoming batch of prepared statements
    ps.executeBatch()

    // Get generated ids
    val resultSet = ps.getGeneratedKeys
    val ids       = ListBuffer.empty[Long]
    while (resultSet.next()) {
      ids += resultSet.getLong(1)
    }

    ps.close()
    ids.toList
  }

  def insertStmt(
    table: String, cols: Iterable[String], placeHolders: Iterable[String]
  ): String = {
    val columns           = cols.mkString(",\n  ")
    val inputPlaceholders = placeHolders.mkString(", ")
    s"""INSERT INTO $table (
       |  $columns
       |) VALUES ($inputPlaceholders)""".stripMargin
  }
}
