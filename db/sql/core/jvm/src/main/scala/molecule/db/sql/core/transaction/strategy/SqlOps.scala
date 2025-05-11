package molecule.db.sql.core.transaction.strategy

import java.sql.PreparedStatement as PS
import molecule.db.core.ast.Element
import molecule.db.base.util.BaseHelpers
import molecule.db.sql.core.query.Model2SqlQuery
import scala.collection.mutable.ListBuffer

trait SqlOps extends BaseHelpers {

  val sqlOps: SqlOps = this

  val sqlConn: java.sql.Connection

  val defaultValues = "(id) VALUES (DEFAULT)"

  val m2q: List[Element] => Model2SqlQuery


  def getIds(ps: PS, table: String = ""): List[Long] = {
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
    ent: String,
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
       |FROM $ent$joins
       |WHERE
       |  $clauses""".stripMargin
  }

  def joinIdNames(ent: String, ref: String): (String, String) = {
    if (ent == ref)
      (ss(ent, "1_id"), ss(ref, "2_id"))
    else
      (ss(ent, "id"), ss(ref, "id"))
  }

  def insertJoinStmt(ent: String, refAttr: String, ref: String): String = {
    val (id1, id2) = joinIdNames(ent, ref)
    s"""INSERT INTO ${ent}_${refAttr}_$ref (
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
