package molecule.sql.core.transaction.strategy.insert

import java.sql.{Connection, Statement}
import molecule.sql.core.transaction.strategy.{SqlOps, SqlAction}
import scala.collection.mutable.ListBuffer

abstract class InsertBase(
  sqlConn: Connection,
  dbOps: SqlOps,
  table: String
) extends SqlAction {

  private val cols         = ListBuffer.empty[String]
  private val placeHolders = ListBuffer.empty[String]
  private val setters      = ListBuffer.empty[PS => Unit]

  override def add(
    attr: String,
    setter: Setter,
    placeHolder: String = "?",
    typeCast: String = ""
  ): Unit = {
    cols += attr
    placeHolders += placeHolder + typeCast
    setters += setter
  }

  override def paramIndex: Int = {
    setters.length + 1
  }

  def prepStmt(stmt: String) = {
    sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
  }

  def insert: List[Long] = {
    // Execute referenced namespaces first so that we can reference them subsequently
    refs.foreach(_.execute)

    // Execute this namespace insert
    val ps = prepStmt(dbOps.insertStmt(table, cols, placeHolders))

    // Populate prepared statement
    setters.foreach(_(ps))

    // Complete row
    ps.addBatch()

    // Retrieve generated ids (various db implementations)
    // Closes prepared statement
    val ids = dbOps.getIds(sqlConn, table, ps)

    // Execute post inserts of refs given new ids of this namespace
    // (many-to-many joins using this id and ref id in pairs)
    refs.foreach(_.getPostSetters.foreach(_(ids)))

    // Execute post inserts of this ns (card-many ref attr ids-to-join tables)
    getPostSetters.foreach(_(ids))
    ids
  }

  override def addCardManyRefAttr(
    ns: String, refAttr: String, refNs: String,
    refIds: Set[Long], defaultValues: String
  ): Unit = {
    if (refIds.nonEmpty) {
      val (id1, id2) = joinIdNames(ns, refNs)
      val joinStmt   =
        s"""INSERT INTO ${ns}_${refAttr}_$refNs (
           |  $id1, $id2
           |) VALUES (?, ?)""".stripMargin
      stmts += joinStmt // for inspect

      addPostSetter(
        (parentIds: List[Long]) => {
          val leftId = parentIds.head
          // necessary?...
          //          val leftId = if (parentIds.isEmpty) {
          //            // Add empty parent row that can be left id in join table
          //            val ps = prepStmt(s"INSERT INTO $table $defaultValues")
          //            ps.execute()
          //            val newParentId = getIds(ps).head
          //            ps.close()
          //            newParentId
          //          } else parentIds.head

          val ps = prepStmt(joinStmt)
          val it = refIds.iterator
          while (it.hasNext) {
            ps.setLong(1, leftId)
            ps.setLong(2, it.next())
            ps.addBatch()
          }
          ps.executeBatch()
          ps.close()
        }
      )
    }
  }

  override def refOne(ns: String, refAttr: String, refNs: String): SqlAction = {
    val ref = InsertRefOne(this, sqlConn, dbOps, ns, refAttr, refNs)
    refs += ref
    ref
  }
  override def refMany(ns: String, refAttr: String, refNs: String): SqlAction = {
    val ref = InsertRefMany(this, sqlConn, dbOps, ns, refAttr, refNs)
    refs += ref
    ref
  }

  def render(indent: Int, strategy: String): String = {
    val i              = "  " * indent
    val j              = i + "  "
    val curStmt        = dbOps.insertStmt(table, cols, placeHolders)
    val strategies     = refs.map(_.render(indent + 1)) ++ (stmts :+ curStmt)
    val executionGraph = strategies.map(stmt =>
      stmt.linesIterator.mkString("\n  " + i)
    ).mkString(s"\n$j---------------------------\n$j")
    s"""${i}$strategy(
       |${i}  $executionGraph
       |${i})""".stripMargin
  }
}
