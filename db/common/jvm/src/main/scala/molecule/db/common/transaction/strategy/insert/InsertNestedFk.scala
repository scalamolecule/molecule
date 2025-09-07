package molecule.db.common.transaction.strategy.insert

import java.sql.PreparedStatement as PS
import molecule.db.common.transaction.strategy.SqlOps

/**
 * Fan-out nested insert using a foreign key on the Many side instead of a join table.
 *
 * Execution:
 * - Parent (One) rows are inserted first so that parent ids are available.
 * - This action injects parent ids as FK setters on the nested (Many) rowSetters
 * according to the recorded rowCounts.
 * - Finally, the nested entity is inserted.
 */
case class InsertNestedFk(
  parent: InsertAction,
  nested: InsertEntity,
  sqlOps: SqlOps,
  ent: String, // One side entity name (parent)
  refAttr: String, // Ref attribute name on the One side
  ref: String, // Many side entity name (nested)
  reverseRefAttr: String, // FK column name on the Many side
  reverseRefAttrIndex: Int, // Parameter index for the FK column set on nested
  rowCount: Int
) extends InsertAction(parent, sqlOps, ref, rowCount) {

  // Keep track of nested count of rows per parent row
  private var rowCounts = List.empty[Int]

  def addNestedCount(n: Int): Unit = {
    rowCounts = rowCounts :+ n
  }

  override def process(): Unit = {
    // We need same number of parent ids as number of parent rows received during resolution
    sameLength(parent.ids.length, rowCounts.length, refAttr, ref)

    // Inject FK setters into nested rowSetters based on rowCounts, then insert nested.
    val parentIds        = parent.ids.iterator
    val counts           = rowCounts.iterator
    val nestedRowSetters = nested.rowSetters.iterator

    while (parentIds.hasNext) {
      val parentId = parentIds.next()
      val n        = counts.next()
      var i        = 0
      while (i < n) {
        val rowSetter = nestedRowSetters.next()
        rowSetter += ((ps: PS) => ps.setLong(reverseRefAttrIndex, parentId))
        i += 1
      }
    }

    // Important that this happens in the following order:

    // 1) Insert nested first (so nested.ids are available for deeper levels)
    nested.insert(false)

    // 2) Then process deeper nested actions that depend on nested.ids
    nested.children.foreach(_.process())
  }

  // This is just for inspection.
  // Actual insert statement is still nested.insert(false)
  override def curStmt: String = {
    nested.curStmt
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "NestedFK")
  }

  override def rootAction: InsertAction = parent.rootAction
}