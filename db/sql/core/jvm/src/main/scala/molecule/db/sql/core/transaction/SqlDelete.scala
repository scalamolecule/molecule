package molecule.db.sql.core.transaction

import molecule.base.metaModel.{CardOne, MetaEntity}
import molecule.base.error.ModelError
import molecule.core.dataModel.*
import molecule.db.core.transaction.ResolveDelete
import molecule.db.core.transaction.ops.DeleteOps
import molecule.db.core.util.ModelUtils
import molecule.db.sql.core.transaction.strategy.SqlOps
import molecule.db.sql.core.transaction.strategy.delete.{DeleteAction, DeleteRoot}
import scala.collection.mutable.ListBuffer

trait SqlDelete
  extends DeleteOps
    with ModelUtils { self: ResolveDelete & SqlOps =>

  protected var root        : DeleteRoot   = null
  protected var deleteAction: DeleteAction = null

  private var needsIdQuery = false
  private var ent          = ""

  private object query {
    val idCols         = ListBuffer.empty[String]
    val joins          = ListBuffer.empty[String]
    val filterElements = ListBuffer.empty[Element]
  }

  def getDeleteAction(
    elements: List[Element], entityMap: Map[String, MetaEntity]
  ): DeleteAction = {
    ent = getInitialEntity(elements)
    query.idCols += s"$ent.id"
    root = DeleteRoot(entityMap, sqlOps, sqlConn.createStatement(), ent)
    deleteAction = root.firstEnt
    resolve(elements, true)
    initRoot(sqlOps)
    root
  }


  private def initRoot(sqlOps: SqlOps): Unit = {
    if (needsIdQuery) {
      val idsQuery  = sqlOps.selectStmt(
        ent, query.idCols, query.joins,
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
      case Ref(ent, refAttr, ref, card, _, _) =>
        card match {
          case CardOne =>
            query.joins += s"INNER JOIN $ref ON $ent.$refAttr = $ref.id"

          case _ =>
            val joinTable = ss(ent, refAttr, ref)
            val eid       = s"${ent}_id"
            val rid       = s"${ref}_id"
            query.joins ++= List(
              s"INNER JOIN $joinTable ON $ent.id = $joinTable.$eid",
              s"INNER JOIN $ref ON $joinTable.$rid = $ref.id",
            )
        }

      case filterAttr =>
        query.filterElements += filterAttr
    }
  }
}