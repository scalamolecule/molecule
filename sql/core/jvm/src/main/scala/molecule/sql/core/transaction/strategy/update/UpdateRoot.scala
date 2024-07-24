package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateRoot(
  sqlConn: Connection,
  m2q: ListBuffer[Element] => Model2SqlQuery,
  ns: String,
  isUpsert: Boolean,
)(implicit sqlOps: SqlOps)
  extends UpdateAction(null, sqlConn, sqlOps, isUpsert, ns) {

  val firstNs = UpdateNs(this, sqlConn, sqlOps, isUpsert, ns, "Ns")
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
