package molecule.sql.core.transaction.op

import java.sql.{Connection, Statement}
import molecule.sql.core.transaction.strategy.{TxBase, TxStrategy}
import scala.collection.mutable.ListBuffer

abstract class HandleInsert(
  sqlConn: Connection,
  table: String
) extends TxStrategy {

  private val cols         = ListBuffer.empty[String]
  private val placeHolders = ListBuffer.empty[String]
  private var setters      = List.empty[PS => Unit]

  override def add(
    col: String,
    setter: Setter,
    placeHolder: String = "?"
  ): Unit = {
    //    println(s"----- $ns.$col")
    cols += col
    placeHolders += placeHolder
    setters = setters :+ setter
  }

  override def paramIndex: Int = {
    //    println(s"----- $ns     " + (setters.length + 1))
    setters.length + 1
  }

  def insertOne: Long = insert(getId)
  def insertMany: List[Long] = insert(getIds)

  def insert[T](idHandler: PS => T): T = {
    val ps = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)

    // Populate prepared statement
    setters.foreach(_(ps))

    // Complete row
    ps.addBatch()

    // Execute batch of prepared statements
    ps.executeBatch()

    val idx = idHandler(ps)
    ps.close()
    idx
  }

  def stmt: String = {
    val columns           = cols.mkString(",\n  ")
    val inputPlaceholders = placeHolders.mkString(", ")
    val stmt              =
      s"""INSERT INTO $table (
         |  $columns
         |) VALUES ($inputPlaceholders)""".stripMargin

    println("-----------------------------------------------\n" + stmt)
    stmt
  }

  // Extract generated ids (override if varying behaviors)
  def getId(ps: PS): Long = {
    val resultSet = ps.getGeneratedKeys
    resultSet.next()
    resultSet.getLong(1)
  }

  // Extract generated ids (override if varying behaviors)
  def getIds(ps: PS): List[Long] = {
    val resultSet = ps.getGeneratedKeys
    val ids       = ListBuffer.empty[Long]
    while (resultSet.next()) {
      ids += resultSet.getLong(1)
    }
    ids.toList
  }
}
