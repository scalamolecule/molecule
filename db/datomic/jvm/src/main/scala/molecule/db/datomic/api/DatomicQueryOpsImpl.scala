package molecule.db.datomic.api

import datomic.Peer
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.{Connection, QueryOps}
import molecule.core.util.JavaConversions
import molecule.db.datomic.facade.Conn_Peer
import molecule.db.datomic.query.DatomicModel2Query
import zio.{Chunk, ZIO}
import scala.concurrent.{ExecutionContext, Future}

class DatomicQueryOpsImpl[Tpl](elements: Seq[Element])
  extends DatomicModel2Query[Tpl](elements) with QueryOps[Tpl] with JavaConversions {

  // Refinements

  override def take(n: Int): DatomicQueryOpsImpl[Tpl] = this
  override def drop(n: Int): DatomicQueryOpsImpl[Tpl] = this
  override def from(cursor: String): DatomicQueryOpsImpl[Tpl] = this


  // Delivery contexts

  override def run(implicit conn: Connection): ZIO[Connection, MoleculeException, Chunk[Tpl]] = ???

  override def getAsync(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]] = ???

  override def get(implicit conn: Connection): List[Tpl] = {
    val db     = conn.asInstanceOf[Conn_Peer].peerConn.db()
    val rows   = Peer.q(query, db +: inputs: _*)
    val tuples = List.newBuilder[Tpl]
    rows.forEach(row => tuples.addOne(row2tpl(row)))
    tuples.result()
  }

}
