package molecule.db.datomic.api

import java.util.{List => jList}
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.{Connection, Query}
import molecule.core.util.JavaConversions


class DatomicQueryImpl[Tpl](elements: Seq[Element])
  extends Query[Tpl]
    with JavaConversions {

  type Raw = jList[AnyRef]

  override def take(n: Int): DatomicQueryImpl[Tpl] = this
  override def drop(n: Int): DatomicQueryImpl[Tpl] = this
  override def from(cursor: String): DatomicQueryImpl[Tpl] = this

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
