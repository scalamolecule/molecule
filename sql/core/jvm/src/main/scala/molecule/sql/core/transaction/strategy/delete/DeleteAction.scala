package molecule.sql.core.transaction.strategy.delete

import java.sql.Statement
import molecule.base.ast.{CardOne, MetaAttr, MetaNs}
import molecule.base.util.BaseHelpers
import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}
import scala.collection.mutable.ListBuffer

abstract class DeleteAction(
  nsMap: Map[String, MetaNs],
  parent: DeleteAction,
  sqlStmt: Statement,
  sqlOps: SqlOps,
  ns: String,
) extends SqlAction(parent, sqlOps, ns)
  with BaseHelpers {


  override def buildExecutionGraph(): Unit = {
    if (ids.nonEmpty) {
      val actions     = prepareQueryAndActions
      val refIdsQuery = makeOwnedRefIdsQuery
      val refIdss     = extractRefIds(refIdsQuery)
      populateDeleteActions(actions, refIdss)
    }
  }


  private def prepareQueryAndActions: Seq[() => DeleteNs] = {
    nsMap(ns).attrs.collect {
      case MetaAttr(refAttr, card, _, Some(refNs), options, _, _, _, _, _)
        if options.contains("owner") =>
        if (card.isInstanceOf[CardOne]) {
          cols += ns + "." + refAttr

          () => {
            // Make delete action if we find ref-one ids
            DeleteNs(
              nsMap, parent, sqlStmt, sqlOps, ns, refAttr, refNs
            )
          }

        } else {
          val t               = refAttr + "_" + refNs
          val joinTable       = ss(ns, refAttr, refNs)
          val (ns_id, ref_id) = sqlOps.joinIdNames(ns, refNs)
          cols += s"$t.id"
          joins ++= List(
            s"LEFT JOIN $joinTable", "", s" ON $ns.id", s" = $joinTable.$ns_id",
            s"LEFT JOIN $refNs", s" AS $t", s" ON $joinTable.$ref_id", s" = $t.id"
          )
          () => {
            // Make delete action if we find ref-many ids
            val deleteRef  = DeleteNs(
              nsMap, parent, sqlStmt, sqlOps, ns, refAttr, refNs
            )
            // Delete rows in join table
            val deleteJoin = DeleteJoin(
              nsMap, parent, sqlStmt, sqlOps, ns, refAttr, refNs
            )
            // We can simply delete by the left ids of the parent namespace (ns)
            deleteJoin.ids = ids

            // Make join deletion a child of (delete before) ref deletion
            deleteRef.addChild(deleteJoin)
            deleteRef
          }
        }
    }
  }


  private def makeOwnedRefIdsQuery: String = {
    clauses += s"$ns.id IN (${ids.mkString(", ")})"
    if (joins.isEmpty) {
      sqlOps.selectStmt(ns, cols, Nil, clauses)
    } else {
      val joinGroups = joins.toList.grouped(4).toList
      val max1       = joinGroups.map(_.head.length).max
      val max2       = joinGroups.map(_(1).length).max
      val max3       = joinGroups.map(_(2).length).max
      val joins1     = joinGroups.collect {
        case List(j, as, l, r) =>
          j + padS(max1, j) + as + padS(max2, as) + l + padS(max3, l) + r
      }
      sqlOps.selectStmt(ns, cols, joins1, clauses)
    }
  }


  private def extractRefIds(idsQuery: String): Array[ListBuffer[Long]] = {
    val refCount = cols.length
    val refIdss  = new Array[ListBuffer[Long]](refCount)
      .map(_ => ListBuffer.empty[Long])

    if (refCount != 0) {
      val resultSet = sqlOps.sqlConn.prepareStatement(idsQuery).executeQuery()
      var refIndex  = 0
      while (resultSet.next()) {
        refIndex = 0
        while (refIndex < refCount) {
          val id = resultSet.getLong(refIndex + 1)
          if (!resultSet.wasNull()) {
            refIdss(refIndex) += id
          }
          refIndex += 1
        }
      }
    }
    refIdss
  }


  private def populateDeleteActions(
    actions: Seq[() => DeleteNs],
    refIdss: Array[ListBuffer[Long]]
  ): Seq[DeleteNs] = {
    actions.zip(refIdss).collect {
      case (deleteAction, refIds) if refIds.nonEmpty =>
        val action = deleteAction()
        action.ids = refIds.toList
        addChild(action)
    }
  }
}
