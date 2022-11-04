package molecule.db.datomic.api

import java.util.{Collections, Comparator, Collection => jCollection}
import datomic.Peer
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.{Connection, QueryOps}
import molecule.core.util.JavaConversions
import molecule.db.datomic.facade.Conn_Peer
import molecule.db.datomic.query.DatomicModel2Query
import molecule.db.datomic.util.DatomicApiLoader
import zio.{Chunk, ZIO}
import scala.concurrent.{ExecutionContext, Future}

class DatomicQueryOpsImpl[Tpl](elements: Seq[Element])
  extends DatomicModel2Query[Tpl](elements)
    with QueryOps[Tpl]
    with JavaConversions
    with DatomicApiLoader {

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
  override def get(implicit conn0: Connection): List[Tpl] = {
    val conn = conn0.asInstanceOf[Conn_Peer]
    val db   = conn.peerConn.db()
    val rows = getQueries(conn.optimizeQuery) match {
      case ("", query)       => Peer.q(query, db +: inputs: _*)
      case (preQuery, query) =>
        // Pre-query
        val preRows = Peer.q(preQuery, db +: preInputs: _*)
        val preIds  = new java.util.HashSet[Long](preRows.size())
        preRows.forEach { row =>
          preIds.add(row.get(0).asInstanceOf[Long])
        }
        //        println(preIds)
        // Main query using entity ids from pre-query
        Peer.q(query, db +: inputs :+ preIds: _*)
    }

    val tuples = List.newBuilder[Tpl]
    sortRows(rows).forEach(row => tuples.addOne(row2tpl(row)))
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
