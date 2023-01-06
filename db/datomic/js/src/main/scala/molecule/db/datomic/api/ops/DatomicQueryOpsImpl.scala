package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.ops.QueryOps
import molecule.core.marshalling.unpack.DTO2tpls
import molecule.core.util.JavaConversions
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

class DatomicQueryOpsImpl[Tpl](elements: Seq[Element])
  extends QueryOps[Tpl] with JavaConversions {

  override def take(n: Int): DatomicQueryOpsImpl[Tpl] = this
  override def drop(n: Int): DatomicQueryOpsImpl[Tpl] = this
  override def from(cursor: String): DatomicQueryOpsImpl[Tpl] = this

  override def get(implicit conn0: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    conn.rpc.query(conn.proxy, elements)
      .map {
        case Right(dto) => DTO2tpls[Tpl](elements, dto).unpack
        case Left(exc)  => throw exc
      }.recover {
      case e: Throwable =>
        printStackTrace(e)
        throw e
    }
  }
}
