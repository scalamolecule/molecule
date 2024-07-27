package molecule.sql.core.transaction.strategy.insert

import java.sql.{PreparedStatement => PS}
import molecule.sql.core.transaction.strategy.SqlOps

case class InsertOptionalRefs(
  parent: InsertAction,
  //  curNs: InsertAction,
  //  optional: InsertNs,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
  refAttrIndex: Int
) extends InsertAction(parent, sqlOps, refNs) {

  // Keep track of present optional value for each row
  private var optionalDefineds = List.empty[Boolean]

  def addOptionalDefined(defined: Boolean): Unit = {
    optionalDefineds = optionalDefineds :+ defined
  }

  override def rootAction: InsertAction = parent.rootAction

  override def process(): Unit = {
    // Process children of ref ns
    children.foreach(_.process())

//    println("optionalDefineds: " + optionalDefineds)
//    println("ids 1           : " + ids)
    // Add ref rows
    insertIntoTable(false)
//    println("ids 2           : " + ids)

    // Add ref ids from parent to ref
    val parentRowSetters = parent.rowSetters.iterator
    val defineds = optionalDefineds.iterator
    val refIds = ids.iterator
    while (defineds.hasNext) {
      val optionIsDefined = defineds.next()
      val refIdSetter     = if (optionIsDefined) {
        val refId = refIds.next()
        (ps: PS) => ps.setLong(refAttrIndex, refId)
      } else {
        (ps: PS) => ps.setNull(refAttrIndex, 0)
      }
      val parentRowSetter = parentRowSetters.next()
      parentRowSetter += refIdSetter
    }
  }

  def process2(): Unit = {

    println("optionalDefineds: " + optionalDefineds)

    sameLength(parent.ids.length, optionalDefineds.length, refAttr, refNs)


    //    children.foreach(_.execute())
    //    insertThisAction()
    val refSetters = parent.rowSetters.iterator
    val defineds   = optionalDefineds.iterator

    val refIds = ids.iterator
    while (refIds.hasNext) {
      val optionIsDefined = defineds.next()
      if (optionIsDefined) {
        val refSetter = refSetters.next()
        val refId     = refIds.next()
        refSetter += ((ps: PS) => ps.setLong(refAttrIndex, refId))
      }
    }
  }

  override def curStmt: String = {
    sqlOps.insertStmt(refNs, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    // Add refAttr to parent insert
    parent.setCol(refAttr)
    recurseRender(indent, "RefOpt")
  }
}
