package molecule.sql.core.transaction

import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveDelete
import molecule.core.transaction.ops.DeleteOps
import molecule.core.util.ModelUtils
import molecule.sql.core.transaction.strategy.SqlOps
import molecule.sql.core.transaction.strategy.delete.{DeleteAction, DeleteRoot}
import scala.collection.mutable.ListBuffer

trait SqlDelete
  extends DeleteOps
    with ModelUtils { self: ResolveDelete with SqlOps =>

  protected var root        : DeleteRoot   = null
  protected var deleteAction: DeleteAction = null

  private var needsIdQuery = false
  private var ns           = ""

  private object query {
    val idCols         = ListBuffer.empty[String]
    val joins          = ListBuffer.empty[String]
    val filterElements = ListBuffer.empty[Element]
  }

  def getDeleteAction(
    elements: List[Element], nsMap: Map[String, MetaNs]
  ): DeleteAction = {
    ns = getInitialNs(elements)
    query.idCols += s"$ns.id"
    root = DeleteRoot(nsMap, sqlOps, sqlConn.createStatement(), ns)
    deleteAction = root.firstNs
    resolve(elements, true)
    initRoot(sqlOps)
    root
  }


  private def initRoot(sqlOps: SqlOps): Unit = {
    if (needsIdQuery) {
      val idsQuery  = sqlOps.selectStmt(
        ns, query.idCols, query.joins,
        m2q(query.filterElements.toList).getWhereClauses
      )
      val ids       = ListBuffer.empty[Long]
      val resultSet = sqlOps.sqlConn.prepareStatement(idsQuery).executeQuery()
      while (resultSet.next()) {
        ids += resultSet.getLong(1)
      }
      deleteAction.ids = ids.toList
    }
  }

  override def addIds(ids: Seq[Long]): Unit = {
    if (deleteAction.ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in delete.")
    }
    deleteAction.ids = ids.toList
  }


  override def addFilterElement(element: Element): Unit = {
    needsIdQuery = true
    element match {
      case Ref(ns, refAttr, refNs, card, _, _) =>
        card match {
          case CardOne =>
            query.joins += s"INNER JOIN $refNs ON $ns.$refAttr = $refNs.id"

          case _ =>
            val joinTable = ss(ns, refAttr, refNs)
            val ns_id     = s"${ns}_id"
            val ref_id    = s"${refNs}_id"
            query.joins ++= List(
              s"INNER JOIN $joinTable ON $ns.id = $joinTable.$ns_id",
              s"INNER JOIN $refNs ON $joinTable.$ref_id = $refNs.id",
            )
        }

      case filterAttr =>
        query.filterElements += filterAttr
    }
  }
}