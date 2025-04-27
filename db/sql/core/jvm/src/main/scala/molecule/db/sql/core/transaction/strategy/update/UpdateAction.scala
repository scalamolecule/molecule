package molecule.db.sql.core.transaction.strategy.update

import molecule.db.sql.core.transaction.strategy.{SqlAction, SqlOps}

abstract class UpdateAction(
  parent: UpdateAction,
  sqlOps: SqlOps,
  ent: String
) extends SqlAction(parent, sqlOps, ent) {


  // Execute update ----------------------------------------

  def updateThisAction(): Unit = {
    if (cols.isEmpty || ids.isEmpty) {
      // No update if no columns are set or supplied collections are empty
      ()

    } else {
      // Execute this entity update
      val ps = prepare(curStmt)
      rowSetters.head.foreach(_(ps)) // set values in prepared update stmt
      ps.addBatch()

      ids = if (ids.nonEmpty) {
        ps.executeBatch()
        ps.close()
        ids
      } else {
        sqlOps.getIds(ps, ent)
      }
    }
  }


  // Change strategy ----------------------------------------

  def deleteRefIds(
    refAttr: String, ref: String, entId: Long, refIds: Set[Long] = Set.empty[Long]
  ): Unit = {
    addSibling(UpdateRefIdsDelete(
      parent, sqlOps, ent, refAttr, ref, entId, refIds
    ))
  }
  def insertRefIds(
    refAttr: String, ref: String, refIds: Set[Long]
  ): Unit = {
    addSibling(UpdateRefIdsInsert(
      parent, sqlOps, ent, refAttr, ref, refIds
    ))
  }

  def refOne(ent: String, refAttr: String, ref: String): UpdateAction = {
    addChild(UpdateRefOne(this, sqlOps, ent, refAttr, ref))
  }

  def refMany(ent: String, refAttr: String, ref: String): UpdateAction = {
    addChild(UpdateRefMany(this, sqlOps, ent, refAttr, ref))
  }

  def backRef: UpdateAction = parent

  def optRef: UpdateAction = ???

  // Traverse back to initial InsertAction
  def rootAction: UpdateAction = ???


  // Add missing refs for upserts ----------------------------------------

  def getCompleteRefIds(ref: String, knownIds: List[Long]): List[Long] = {
    val newRefIds = insertMissingRefRows(ref, knownIds)
    addRefs(knownIds, newRefIds)
    mergeKnownAndNewRefIds(knownIds, newRefIds)
  }

  def addRefs(knownIds: List[Long], newRefIds: List[Long]): Unit = ???

  protected def insertMissingRefRows(
    ref: String,
    knownIds: List[Long]
  ): List[Long] = {
    val insertEmptyRefRows = prepare(sqlOps.insertStmt(ref, Nil, Nil))
    (1 to knownIds.count(_ == 0L))
      .foreach(_ => insertEmptyRefRows.addBatch())
    sqlOps.getIds(insertEmptyRefRows, ref)
  }

  protected def mergeKnownAndNewRefIds(
    knownIds: List[Long], newRefIds: List[Long]
  ): List[Long] = {
    val newRefIds1 = newRefIds.iterator
    knownIds.map {
      case 0     => newRefIds1.next()
      case refId => refId
    }
  }
}
