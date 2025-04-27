package molecule.db.sql.core.transaction.strategy.insert

import molecule.db.sql.core.transaction.strategy.SqlOps

case class InsertEntity(
  parent: InsertAction,
  sqlOps: SqlOps,
  ent: String,
  action: String,
  rowCount: Int
) extends InsertAction(parent, sqlOps, ent, rowCount) {

  override def rootAction: InsertAction = parent.rootAction

  override def process(): Unit = {
    children.foreach(_.process())

//    println(s"++++++++++++++++++++++++++++++++++++++++++++++++++++++++  $ent  ")
//
//    println("rowSetters")
//    println(rowSetters.map { rs => rs.toList.mkString("\n   ") }.mkString("   ", "\n   -----\n   ", ""))
//
//    println("parent.rowSetters")
//    println(parent.rowSetters.map { rs => rs.toList.mkString("\n   ") }.mkString("   ", "\n   -----\n   ", ""))

    insert()
  }

  override def curStmt: String = {
    sqlOps.insertStmt(ent, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, action)
  }
}
