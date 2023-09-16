package molecule.datalog.datomic.spi

import molecule.core.action.Query
import molecule.core.spi.Conn
import molecule.core.util.{FutureUtils, ModelUtils}
import molecule.datalog.datomic.facade.DatomicConn_JS
import scala.concurrent.{Future, ExecutionContext => EC}

trait Base extends ModelUtils with FutureUtils {

  def addCallback[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                      (implicit conn0: Conn, ec: EC): Future[Unit] = {
    val conn             = conn0.asInstanceOf[DatomicConn_JS]
    val elements         = q.elements
    val involvedAttrs    = getAttrNames(elements)
    val involvedDeleteNs = getInitialNs(elements)
    val maybeCallback    = (mutationAttrs: Set[String], isDelete: Boolean) => {
      if (
        mutationAttrs.exists(involvedAttrs.contains) ||
          isDelete && mutationAttrs.head.startsWith(involvedDeleteNs)
      ) {
        conn.rpc.query[Tpl](conn.proxy, q.elements, q.optLimit).future.map(callback)
        ()
      }
    }
    Future(conn.addCallback(elements -> maybeCallback))
  }
}
