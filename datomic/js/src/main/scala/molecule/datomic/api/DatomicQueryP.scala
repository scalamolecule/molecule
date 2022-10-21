package molecule.datomic.api

import java.util.{List => jList}
import molecule.boilerplate.ast.moleculeModel._
import molecule.core.api.{Connection, Query}
import molecule.core.util.JavaConversions


class DatomicQueryP[Tpl](elements: Seq[Element])
  extends Query[Tpl]
    with JavaConversions {

  type Raw = jList[AnyRef]

  override def take(n: Int): DatomicQueryP[Tpl] = this
  override def drop(n: Int): DatomicQueryP[Tpl] = this
  override def from(cursor: String): DatomicQueryP[Tpl] = this

  override def run(implicit conn: Connection): List[Tpl] = {
//    val query                               = ""
//    val inputs: Seq[AnyRef] = ???
//    val rows: util.Collection[jList[AnyRef]] = Peer.q(query, inputs: _*)
//    val row2tpls: Raw => Tpl = ???
//    val tuples = List.newBuilder[Tpl]
//    rows.forEach(row => tuples.addOne(row2tpls(row)))
//    tuples.result()
    Nil
  }
  //  override def run: ZIO[DataSource, Throwable, Chunk[Tpl]] = ZIO.succeed(Chunk.empty[Tpl]) // .provideEnvironment(conn)
}
