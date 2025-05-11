package molecule.db.sql.core.transaction.strategy.delete

import java.sql.Statement
import molecule.db.base.ast.{CardOne, MetaAttribute, MetaEntity}
import molecule.db.base.util.BaseHelpers
import molecule.db.sql.core.transaction.strategy.{SqlAction, SqlOps}
import scala.collection.mutable.ListBuffer

abstract class DeleteAction(
  entityMap: Map[String, MetaEntity],
  parent: DeleteAction,
  sqlStmt: Statement,
  sqlOps: SqlOps,
  entity: String,
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


  private def prepareQueryAndActions: Seq[() => DeleteEntity] = {
    entityMap(entity).attrs.collect {
      case MetaAttribute(refAttr, card, _, Some(refEnt), options, _, _, _, _, _)
        if options.contains("owner") =>
        if (card.isInstanceOf[CardOne]) {
          cols += entity + "." + refAttr

          () => {
            // Make delete action if we find ref-one ids
            DeleteEntity(
              entityMap, parent, sqlStmt, sqlOps, entity, refAttr, refEnt
            )
          }

        } else {
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
              entityMap, parent, sqlStmt, sqlOps, entity, refAttr, refEnt
            )
            // Delete rows in join table
            val deleteJoin = DeleteJoin(
              entityMap, parent, sqlStmt, sqlOps, entity, refAttr, refEnt
            )
            // We can simply delete by the left ids of the parent entity
            deleteJoin.ids = ids

            // Make join deletion a child of (delete before) ref deletion
            deleteRef.addChild(deleteJoin)
            deleteRef
          }
        }
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
