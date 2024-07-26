package molecule.sql.core.transaction.strategy.update

import molecule.sql.core.transaction.strategy.SqlOps

case class UpdateRoot(
  sqlOps: SqlOps,
  ns: String
) extends UpdateAction(null, sqlOps, ns) {

  val firstNs = UpdateNs(this, sqlOps, ns, "Ns")
  children += firstNs

  // set by SqlUpdate.initRoot()
  var idsQuery = ""
  var refIds   = Array.empty[List[Long]]


  override def rootAction: UpdateAction = this

  override def executeRoot: List[Long] = {
    children.foreach(_.execute())
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
