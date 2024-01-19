package molecule.document.mongodb.query.cursorStrategy

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.query.Pagination
import molecule.core.util.FutureUtils
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.query.{Model2MongoQuery, QueryResolve_mongodb}


case class NoUnique[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  cursor: String,
  m2q: Model2MongoQuery[Tpl]
) extends QueryResolve_mongodb[Tpl](elements, m2q)
  with FutureUtils with Pagination[Tpl] with MoleculeLogging {

  def getPage(allTokens: List[String], limit: Int)
             (implicit conn: MongoConn_JVM)
  : (List[Tpl], String, Boolean) = try {
    val forward     = limit > 0
    val attrsTokens = allTokens.drop(2).dropRight(6).grouped(13).toList.sortBy(_(2))

    val rowHashes = {
      val List(a, b, c, x, y, z) = allTokens.takeRight(6)
      (if (forward) List(z, y, x) else List(a, b, c)).filter(_.nonEmpty).map(_.toInt)
    }

    val identifyTpl = (tpl: Tpl) => tpl.hashCode()

    paginateFromIdentifiers(
      conn,
      limit,
      forward,
      allTokens,
      attrsTokens.head,
      rowHashes,
      identifyTpl,
      nextCursorNoUnique
    )
  } catch {
    case t: Throwable => throw ModelError(t.toString)
  }
}
