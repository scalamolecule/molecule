package molecule.sql.jdbc.transaction

import java.sql.{Statement, PreparedStatement => PS}
import molecule.base.ast.SchemaAST.MetaNs
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveDelete
import molecule.core.transaction.ops.DeleteOps

trait Data_Delete extends JdbcBase_JVM with DeleteOps with MoleculeLogging { self: ResolveDelete =>

  def getData(elements: List[Element], nsMap: Map[String, MetaNs]): Data = {
    val refPath          = List(getInitialNs(elements))
    val table            = refPath.head
    val (deleteModel, _) = separateTxElements(elements)

    resolve(deleteModel, true)

    val ids_          = ids.mkString(", ")
    val stmt          = s"""DELETE FROM $table WHERE $table.id IN ($ids_)""".stripMargin
    val ps            = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
    val populatePS    = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
    val preparedTable = Table(refPath, stmt, ps, populatePS)
    (List(preparedTable), Nil)
  }

  override def addIds[T](ids1: Seq[T]): Unit = {
    ids = ids ++ ids1.asInstanceOf[Seq[Long]]
  }

  override def addFilterElements(elements: Seq[Element]): Unit = {
    filterElements = filterElements ++ elements
  }
}