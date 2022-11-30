package molecule.db.datomic.api

import java.util
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

  // Refinements - todo
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
    val conn       = conn0.asInstanceOf[Conn_Peer]
    val db         = conn.peerConn.db()
    val rows       = getQueries(conn.optimizeQuery) match {
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
    //    println("RAW rows:")
    //    rows.forEach(row => println(row))
    val sortedRows = sortRows(rows)

    println("SORTED rows:")
    sortedRows.forEach(row => println(row))

    lazy val tuples = List.newBuilder[Tpl]
    if (isNested) {
      castss = castss :+ casts.toList
      rows2nested(sortedRows)

    } else if (isNestedOpt) {
      pullCastss = pullCastss :+ pullCasts.toList
      if (flatten)
        sortedRows.forEach(row => tuples.addOne(pullRowFlatten2tpl(row)))
      else
        sortedRows.forEach(row => tuples.addOne(pullRow2tpl(row)))
      tuples.result()

    } else if (isComposite) {
      sortedRows.forEach(row => tuples.addOne(compositeRow2tpl(row)))
      tuples.result()

    } else {
      sortedRows.forEach(row => tuples.addOne(row2tpl(row)))
      tuples.result()
    }
  }


  // Helpers

  private def sortRows(rows: jCollection[Row]): util.ArrayList[Row] = {
    (sortsAcc.length + sorts.length) match {
      case 0 => new java.util.ArrayList(rows)
      case n =>
        validateSortIndexes()
        val sorters = sortsAcc ++ sorts.sortBy(_._1).map(_._2)
        //        println("--- SORT ------------------------------------------------------")
        //        sorters.foreach(println)

        val nestedIdsCount = nestedIds.length
        val sortedRows     = new java.util.ArrayList(rows)
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


  override def inspect(implicit conn0: Connection): Unit = {
    val conn              = conn0.asInstanceOf[Conn_Peer]
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
    val output            = get(conn).take(100).zipWithIndex.map { case (row, i) =>
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
