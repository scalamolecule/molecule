package molecule.sql.core.transaction.strategy.insert

import molecule.sql.core.transaction.strategy.SqlOps

case class InsertNs(
  parent: InsertAction,
  sqlOps: SqlOps,
  ns: String,
  action: String,
  rowCount: Int
) extends InsertAction(parent, sqlOps, ns, rowCount) {

  override def rootAction: InsertAction = parent.rootAction

  override def process(): Unit = {
    children.foreach(_.process())

    println(s"++++++++++++++++++++++++++++++++++++++++++++++++++++++++  $ns  ")

    println("rowSetters")
    println(rowSetters.map { rs => rs.toList.mkString("\n   ") }.mkString("   ", "\n   -----\n   ", ""))

    println("parent.rowSetters")
    println(parent.rowSetters.map { rs => rs.toList.mkString("\n   ") }.mkString("   ", "\n   -----\n   ", ""))

    insert()
  }

  override def curStmt: String = {
    sqlOps.insertStmt(ns, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, action)
  }
}
