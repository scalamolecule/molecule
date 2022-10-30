package molecule.db.datomic.api

import java.util.{Collections, Comparator, Collection => jCollection}
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

  // ZIO program
  override def run(implicit conn: Connection): ZIO[Connection, MoleculeException, Chunk[Tpl]] = ???

  // Asynchronous Future
  override def getAsync(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]] = ???

  // Synchronous
  override def get(implicit conn: Connection): List[Tpl] = {
    val db         = conn.asInstanceOf[Conn_Peer].peerConn.db()
    val rows       = Peer.q(query, db +: rulesAndInputs: _*)
    val sortedRows = sortRows(rows)
    val tuples     = List.newBuilder[Tpl]
    sortedRows.forEach(row => tuples.addOne(row2tpl(row)))
    tuples.result()
  }


  // Helpers

  private def sortRows(rows: jCollection[Row]): jCollection[Row] = {
    sorts.length match {
      case 0 => rows
      case n =>
        val sorters    = sorts.sortBy(_._1).map(_._2)
        val sortedRows = new java.util.ArrayList(rows)
        val comparator = new Comparator[Row] {
          override def compare(a: Row, b: Row): Int = {
            var i      = 0
            var result = 0
            do {
              result = sorters(i)(a, b)
              i += 1
            } while (result == 0 && i != n)
            result
          }
        }
        Collections.sort(sortedRows, comparator)
        sortedRows
    }
  }
}
