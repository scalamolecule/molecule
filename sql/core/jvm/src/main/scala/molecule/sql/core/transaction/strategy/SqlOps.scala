package molecule.sql.core.transaction.strategy

import java.sql.Connection
import molecule.base.util.BaseHelpers
import scala.collection.mutable.ListBuffer

class SqlOps extends SqlBase with BaseHelpers {

  lazy val defaultValues = "(id) VALUES (DEFAULT)"


  def joinIdNames(ns: String, refNs: String): (String, String) = {
    if (ns == refNs)
      (ss(ns, "1_id"), ss(refNs, "2_id"))
    else
      (ss(ns, "id"), ss(refNs, "id"))
  }

  def getJoinStmt(ns: String, refAttr: String, refNs: String): String = {
    val (id1, id2) = joinIdNames(ns, refNs)
    s"""INSERT INTO ${ns}_${refAttr}_$refNs (
       |  $id1, $id2
       |) VALUES (?, ?)""".stripMargin
  }

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


  def updateStmt(
    table: String, cols: Iterable[String], clauses: Iterable[String]
  ): String = {
    val columnSetters = cols.mkString(",\n  ")
    val updateClauses = clauses.mkString(" AND\n  ")
    s"""UPDATE $table
       |SET
       |  $columnSetters
       |WHERE
       |  $updateClauses""".stripMargin

  }
}
