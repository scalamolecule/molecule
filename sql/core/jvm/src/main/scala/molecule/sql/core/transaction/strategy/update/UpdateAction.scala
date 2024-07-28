package molecule.sql.core.transaction.strategy.update

import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}

abstract class UpdateAction(
  parent: UpdateAction,
  sqlOps: SqlOps,
  ns: String
) extends SqlAction(parent, sqlOps, ns) {


  // Execute update ----------------------------------------

  def updateThisAction(): Unit = {
    if (cols.isEmpty || ids.isEmpty) {
      // No update if no columns are set or supplied collections are empty
      ()

    } else {
      // Execute this namespace update
      val ps = prepare(curStmt)
      rowSetters.head.foreach(_(ps)) // set values in prepared update stmt
      ps.addBatch()

      ids = if (ids.nonEmpty) {
        ps.executeBatch()
        ps.close()
        ids
      } else {
        sqlOps.getIds(ps, ns)
      }
    }
  }


  // Change strategy ----------------------------------------

  def deleteRefIds(
    refAttr: String, refNs: String, nsId: Long, refIds: Set[Long] = Set.empty[Long]
  ): Unit = {
    addSibling(UpdateRefIdsDelete(
      parent, sqlOps, ns, refAttr, refNs, nsId, refIds
    ))
  }
  def insertRefIds(
    refAttr: String, refNs: String, refIds: Set[Long]
  ): Unit = {
    addSibling(UpdateRefIdsInsert(
      parent, sqlOps, ns, refAttr, refNs, refIds
    ))
  }

  def refOne(ns: String, refAttr: String, refNs: String): UpdateAction = {
    addChild(UpdateRefOne(this, sqlOps, ns, refAttr, refNs))
  }

  def refMany(ns: String, refAttr: String, refNs: String): UpdateAction = {
    addChild(UpdateRefMany(this, sqlOps, ns, refAttr, refNs))
  }

  def backRef: UpdateAction = parent

  def optRef: UpdateAction = ???

  // Traverse back to initial InsertAction
  def rootAction: UpdateAction = ???


  // Add missing refs for upserts ----------------------------------------

  def getCompleteRefIds(refNs: String, knownIds: List[Long]): List[Long] = {
    val newRefIds = insertMissingRefRows(refNs, knownIds)
    addRefs(knownIds, newRefIds)
    mergeKnownAndNewRefIds(knownIds, newRefIds)
  }

  def addRefs(knownIds: List[Long], newRefIds: List[Long]): Unit = ???

  protected def insertMissingRefRows(
    refNs: String,
    knownIds: List[Long]
  ): List[Long] = {
    val insertEmptyRefRows = prepare(sqlOps.insertStmt(refNs, Nil, Nil))
    (1 to knownIds.count(_ == 0L))
      .foreach(_ => insertEmptyRefRows.addBatch())
    sqlOps.getIds(insertEmptyRefRows, refNs)
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
