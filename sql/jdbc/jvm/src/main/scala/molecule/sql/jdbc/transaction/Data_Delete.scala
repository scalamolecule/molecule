package molecule.sql.jdbc.transaction

import java.sql.{PreparedStatement => PS}
import molecule.base.ast.SchemaAST.{CardSet, MetaNs}
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveDelete
import molecule.core.transaction.ops.DeleteOps

trait Data_Delete extends JdbcBase_JVM with DeleteOps with MoleculeLogging { self: ResolveDelete =>

  def getData(elements: List[Element], nsMap: Map[String, MetaNs]): Data = {
    val refPath          = List(getInitialNs(elements))
    val (deleteModel, _) = separateTxElements(elements)

    resolve(deleteModel, true)

    val ids_ = ids.mkString(", ")

    def prepareTable(table: String, idColumn: String) = {
      val stmt       = s"""DELETE FROM $table WHERE $idColumn IN ($ids_)""".stripMargin
      val ps         = sqlConn.prepareStatement(stmt)
      val populatePS = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
      Table(refPath, stmt, ps, populatePS)
    }

    val ns    = refPath.head
    val table = prepareTable(ns, s"$ns.id")

    // Delete join rows matching deleted entities
    val joinTables = nsMap(ns).attrs
      .filter(attr => attr.refNs.nonEmpty && attr.card == CardSet)
      .map { metaAttr =>
        val refNs     = metaAttr.refNs.get
        val joinTable = s"${ns}_${metaAttr.attr}_$refNs"
        val idCol     = ns + (if (ns == refNs) "_1_id" else "_id")
        prepareTable(joinTable, idCol)
      }.toList

    (table +: joinTables, Nil)
  }

  override def addIds[T](ids1: Seq[T]): Unit = {
    ids = ids ++ ids1.asInstanceOf[Seq[Long]]
  }

  override def addFilterElements(elements: Seq[Element]): Unit = {
    filterElements = filterElements ++ elements
  }
}