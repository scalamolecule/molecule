package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.ops._


class DatomicQueryOps[Tpl](elements: Seq[Element])
  extends DatomicQueryOpsImpl[Tpl](elements) with QueryOps[Tpl] {

//  type Raw = jList[AnyRef]

//  override def take(n: Int): DatomicQuery[Tpl] = this
//  override def drop(n: Int): DatomicQuery[Tpl] = this
//  override def from(cursor: String): DatomicQuery[Tpl] = this

//  override def run(implicit conn: Connection): List[Tpl] = {
//    val query                               = ""
//    val inputs: Seq[AnyRef] = ???
//    val rows: util.Collection[jList[AnyRef]] = Peer.q(query, inputs: _*)
//    val row2tpls: Raw => Tpl = ???
//    val tuples = List.newBuilder[Tpl]
//    rows.forEach(row => tuples.addOne(row2tpls(row)))
//    tuples.result()
//  }
//  override def run: ZIO[DataSource, Throwable, Chunk[Tpl]] = ZIO.succeed(Chunk.empty[Tpl]) // .provideEnvironment(conn)
}
