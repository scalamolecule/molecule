package molecule.db.datomic.api.ops

import java.util.{Collections, Comparator, ArrayList => jArrayList, Collection => jCollection}
import datomic.Peer
import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.ops.QueryOps
import molecule.core.util.JavaConversions
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query.DatomicModel2Query
import molecule.db.datomic.util.DatomicApiLoader
import scala.concurrent.{ExecutionContext, Future, blocking}

class DatomicQueryOpsImpl[Tpl](elements: Seq[Element])
  extends DatomicModel2Query[Tpl](elements)
    with QueryOps[Tpl]
    with JavaConversions
    with DatomicApiLoader {

  // Refinements - prevent no-sense combinations todo
  override def take(n: Int): DatomicQueryOpsImpl[Tpl] = this
  override def drop(n: Int): DatomicQueryOpsImpl[Tpl] = this
  override def from(cursor: String): DatomicQueryOpsImpl[Tpl] = this


  // Delivery contexts

  //  override def run(implicit conn: Connection): ZIO[Connection, MoleculeException, Chunk[Tpl]] = ???

  override def get(implicit conn0: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
    Future {
      try {
        val sortedRows = getSortedRows

        // Remove started composite groups that turned out to have only tacit attributes
        aritiess = aritiess.map(_.filterNot(_.isEmpty))

        lazy val tuples = List.newBuilder[Tpl]
        val result = if (isNested) {
          rows2nested(sortedRows)
        } else if (isNestedOpt) {
          pullCastss = pullCastss :+ pullCasts.toList
          pullSortss = pullSortss :+ pullSorts.sortBy(_._1).map(_._2).toList
          sortedRows.forEach(row => tuples.addOne(pullRow2tpl(row)))
          tuples.result()
        } else {
          val row2tpl = castRow2Tpl(aritiess.head, castss.head, 0, None)
          sortedRows.forEach(row => tuples.addOne(row2tpl(row).asInstanceOf[Tpl]))
          tuples.result()
        }
        Future(result)
      } catch {
        case e: Throwable => Future.failed(e)
      }
    }.flatten
  }

  def getSortedRows(implicit conn0: Connection): jArrayList[Row] = {
    val conn = conn0.asInstanceOf[DatomicConn_JVM]
    val db   = conn.peerConn.db()
    isFree = conn.isFreeVersion
    val rows = getQueries(conn.optimizeQuery) match {
      case ("", query)       => Peer.q(query, db +: inputs: _*)
      case (preQuery, query) =>
        // Pre-query
        val preRows = Peer.q(preQuery, db +: preInputs: _*)
        val preIds  = new java.util.HashSet[Long](preRows.size())
        preRows.forEach { row =>
          preIds.add(row.get(0).asInstanceOf[Long])
        }
        // Main query using entity ids from pre-query
        Peer.q(query, db +: inputs :+ preIds: _*)
    }

    println("RAW rows:")
    rows.forEach(row => println(row))

    val sortedRows = sortRows(rows)

    println("SORTED rows:")
    sortedRows.forEach(row => println(row))
    sortedRows
  }


  // Helpers

  def sortRows(rows: jCollection[Row]): jArrayList[Row] = {
    val sorters = getFlatSorters(sortss)
    sorters.length match {
      case 0 => new jArrayList(rows)
      case n =>
        val nestedIdsCount = nestedIds.length
        val sortedRows     = new jArrayList(rows)
        val comparator     = new Comparator[Row] {
          override def compare(a: Row, b: Row): Int = {
            var i      = 0
            var result = 0
            do {
              result = sorters(i)(nestedIdsCount)(a, b)
              i += 1
            } while (result == 0 && i != n)
            result
          }
        }
        Collections.sort(sortedRows, comparator)
        sortedRows
    }
  }

  override def inspect(implicit conn0: Connection, ec: ExecutionContext): Future[Unit] = {
    val conn = conn0.asInstanceOf[DatomicConn_JVM]
    isFree = conn.isFreeVersion
    val (preQuery, query) = getQueries(conn.optimizeQuery)
    val queries           = if (preQuery.isEmpty) query else {
      val preInp = if (preInputs.isEmpty) "" else
        s"\n\nPRE-INPUTS:\n" + preInputs.mkString("\n")

      val preQ = if (preQuery.isEmpty) "" else
        s"\n\nPRE-Query:\n" + preQuery + preInp + "\n\n"

      val inp = if (inputs.isEmpty) "" else
        s"\n\nINPUTS:\n" + inputs.mkString("\n")

      s"""${preQ}QUERY:
         |$query$inp
         |
         |""".stripMargin
    }
    get.map { rows =>
      val output = rows.take(100).zipWithIndex.map { case (row, i) =>
        s"ROW ${i + 1}:".padTo(7, ' ') + row
      }.mkString("\n")
      println(
        s"""
           |--------------------------------------------------------------------------
           |${elements.mkString("\n")}
           |
           |$queries
           |
           |$output
           |(showing up to 100 rows)
           |--------------------------------------------------------------------------
      """.stripMargin
      )
    }
  }
}
