package molecule.db.sql.core.transaction.strategy.update

import molecule.db.sql.core.transaction.strategy.SqlOps

case class UpdateRoot(
  sqlOps: SqlOps,
  ent: String
) extends UpdateAction(null, sqlOps, ent) {

  val firstEnt = UpdateEntity(this, sqlOps, ent, "Entity")
  children += firstEnt

  // set by SqlUpdate.initRoot()
  var idsQuery = ""
  var refIds   = Array.empty[List[Long]]


  override def rootAction: UpdateAction = this

  override def execute: List[Long] = {
    children.foreach(_.process())
    children.head.ids
  }

  override def toString: String = {
    val idQuery = if (idsQuery.isEmpty) "" else {
      val ids = cols.zip(refIds).map {
        case (col, ids) => s"$col: $ids"
      }.mkString("\n")
      s"""$idsQuery
         |-----------------------
         |$ids
         |
         |""".stripMargin
    }
    recurseRender(-1, idQuery + "Update")
  }
}
