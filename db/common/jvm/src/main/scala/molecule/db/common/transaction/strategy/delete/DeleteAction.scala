package molecule.db.common.transaction.strategy.delete

import java.sql.Statement
import molecule.base.metaModel.CardOne
import molecule.base.util.BaseHelpers
import molecule.db.common.api.MetaDb
import molecule.db.common.transaction.strategy.{SqlAction, SqlOps}
import scala.collection.mutable.ListBuffer

abstract class DeleteAction(
  parent: DeleteAction,
  sqlStmt: Statement,
  sqlOps: SqlOps,
  entity: String,
  metaDb: MetaDb,
) extends SqlAction(parent, sqlOps, entity)
  with BaseHelpers {


  override def buildExecutionGraph(): Unit = {
    if (ids.nonEmpty) {
      val actions     = prepareQueryAndActions
      val refIdsQuery = makeOwnedRefIdsQuery
      val refIdss     = extractRefIds(refIdsQuery)
      populateDeleteActions(actions, refIdss)
    }
  }


  def prepareQueryAndActions: Seq[() => DeleteEntity] = metaDb.ownedRefs.getOrElse(entity, Nil).map {
    case (refAttr, CardOne, refEnt) =>
      cols += entity + "." + refAttr
      () =>
        // Make delete action if we find ref-one ids
        DeleteEntity(
          parent, sqlStmt, sqlOps, entity, refAttr, refEnt, metaDb
        )

    case (refAttr, _, refEnt) =>
      val t          = refAttr + "_" + refEnt
      val joinTable  = ss(entity, refAttr, refEnt)
      val (eid, rid) = sqlOps.joinIdNames(entity, refEnt)
      cols += s"$t.id"
      joins ++= List(
        s"LEFT JOIN $joinTable", "", s" ON $entity.id", s" = $joinTable.$eid",
        s"LEFT JOIN $refEnt", s" AS $t", s" ON $joinTable.$rid", s" = $t.id"
      )
      () => {
        // Make delete action if we find ref-many ids
        val deleteRef  = DeleteEntity(
          parent, sqlStmt, sqlOps, entity, refAttr, refEnt, metaDb
        )
        // Delete rows in join table
        val deleteJoin = DeleteJoin(
          parent, sqlStmt, sqlOps, entity, refAttr, refEnt, metaDb
        )
        // We can simply delete by the left ids of the parent entity
        deleteJoin.ids = ids

        // Make join deletion a child of (delete before) ref deletion
        deleteRef.addChild(deleteJoin)
        deleteRef
      }
  }


  private def makeOwnedRefIdsQuery: String = {
    clauses += s"$entity.id IN (${ids.mkString(", ")})"
    if (joins.isEmpty) {
      sqlOps.selectStmt(entity, cols, Nil, clauses)
    } else {
      val joinGroups = joins.toList.grouped(4).toList
      val max1       = joinGroups.map(_.head.length).max
      val max2       = joinGroups.map(_(1).length).max
      val max3       = joinGroups.map(_(2).length).max
      val joins1     = joinGroups.collect {
        case List(j, as, l, r) =>
          j + padS(max1, j) + as + padS(max2, as) + l + padS(max3, l) + r
      }
      sqlOps.selectStmt(entity, cols, joins1, clauses)
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
    actions: Seq[() => DeleteEntity],
    refIdss: Array[ListBuffer[Long]]
  ): Seq[DeleteEntity] = {
    actions.zip(refIdss).collect {
      case (deleteAction, refIds) if refIds.nonEmpty =>
        val action = deleteAction()
        action.ids = refIds.toList
        addChild(action)
    }
  }
}
