package molecule.db.common.transaction.strategy.insert

import java.sql.PreparedStatement as PS
import molecule.db.common.transaction.strategy.SqlOps

case class InsertRefOne(
  parent: InsertAction,
  sqlOps: SqlOps,
  ref: String,
  refAttrIndex: Int,
  rowCount: Int
) extends InsertAction(parent, sqlOps, ref, rowCount) {

  override def rootAction: InsertAction = parent.rootAction

  override def process(): Unit = {
    insertAndPrepareManyToOneRef(parent, refAttrIndex)
  }

  override def curStmt: String = {
    sqlOps.insertStmt(ref, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "RefOne")
  }
}
