package molecule.db.datomic.api

import java.util
import java.util.{List => jList}
import datomic.Peer
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.{Connection, Query}
import molecule.core.util.JavaConversions
import molecule.db.datomic.facade.Conn_Peer
import molecule.db.datomic.query.Model2Query

class DatomicQueryImpl[Tpl](elements: Seq[Element])
  extends Model2Query[Tpl](elements) with Query[Tpl] with JavaConversions {

  elements foreach println

  override def take(n: Int): DatomicQueryImpl[Tpl] = this
  override def drop(n: Int): DatomicQueryImpl[Tpl] = this
  override def from(cursor: String): DatomicQueryImpl[Tpl] = this

  override def run(implicit conn: Connection): List[Tpl] = {
    val db     = conn.asInstanceOf[Conn_Peer].peerConn.db()
    val rows   = Peer.q(query, db +: inputs: _*)
    val tuples = List.newBuilder[Tpl]
    rows.forEach(row => tuples.addOne(row2tpl(row)))
    tuples.result()
//    null
  }
  //  override def run: ZIO[DataSource, Throwable, Chunk[Tpl]] = ZIO.succeed(Chunk.empty[Tpl]) // .provideEnvironment(conn)
}
