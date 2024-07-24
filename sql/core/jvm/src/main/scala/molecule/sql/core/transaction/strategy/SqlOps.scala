package molecule.sql.core.transaction.strategy

import java.sql.Connection
import molecule.base.util.BaseHelpers
import scala.collection.mutable.{ArrayStack, ListBuffer}

class SqlOps extends SqlBase with BaseHelpers {

  lazy val defaultValues = "DEFAULT VALUES"


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


  // Render sql statements --------------------------------------

  def selectStmt(
    ns: String,
    cols0: Iterable[String],
    joins0: Iterable[String],
    clauses0: Iterable[String],
  ): String = {
    val cols    = cols0.mkString(",\n  ")
    val joins   = if (joins0.isEmpty) "" else
      joins0.mkString(s"\n  ", s"\n  ", "")
    val clauses = clauses0.mkString(" AND\n  ")
    s"""SELECT DISTINCT
       |  $cols
       |FROM $ns$joins
       |WHERE
       |  $clauses""".stripMargin
  }

  def joinIdNames(ns: String, refNs: String): (String, String) = {
    if (ns == refNs)
      (ss(ns, "1_id"), ss(refNs, "2_id"))
    else
      (ss(ns, "id"), ss(refNs, "id"))
  }

  def insertJoinStmt(ns: String, refAttr: String, refNs: String): String = {
    val (id1, id2) = joinIdNames(ns, refNs)
    s"""INSERT INTO ${ns}_${refAttr}_$refNs (
       |  $id1, $id2
       |) VALUES (?, ?)""".stripMargin
  }

  def deleteJoinStmt(ns: String, refAttr: String, refNs: String): String = {
    val joinTable = ss(ns, refAttr, refNs)
    val ns_id     = ss(ns, "id")
    val refNs_id  = ss(refNs, "id")
    //    val deleteJoins = s"DELETE FROM $joinTable WHERE $ns_id = $id" + refIds

    val (id1, id2) = joinIdNames(ns, refNs)
    s"""INSERT INTO ${ns}_${refAttr}_$refNs (
       |  $id1, $id2
       |) VALUES (?, ?)""".stripMargin
  }

  def insertStmt(
    table: String, cols: Iterable[String], placeHolders: Iterable[String]
  ): String = {
    val columns           = cols.mkString(",\n  ")
    val inputPlaceholders = placeHolders.mkString(", ")
    if (cols.nonEmpty) {
      s"""INSERT INTO $table (
         |  $columns
         |) VALUES ($inputPlaceholders)""".stripMargin
    } else {
      s"INSERT INTO $table $defaultValues"
    }
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

  def deleteStmt(
    table: String, clauses: Iterable[String]
  ): String = {
    val deleteClauses = clauses.mkString(" AND\n  ")
    s"""DELETE FROM $table
       |WHERE
       |  $deleteClauses""".stripMargin
  }
}
