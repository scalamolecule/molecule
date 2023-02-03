package molecule.db.datomic.query

import java.util.{Collections, Comparator, ArrayList => jArrayList, Collection => jCollection, List => jList}
import datomic.Peer
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.util.DatomicApiLoader

/**
 *
 * @param elements Molecule model
 * @param limit    When going forward from start, use a positive number.
 *                 And vice versa from end with a negative number. Can't be zero.
 * @param offset   Positive offset from start when going forwards,
 *                 negative offset from end when going backwards
 * @param cursor   Base64 encoded cursor meta information
 * @tparam Tpl Type of each row
 */
abstract class DatomicQuery[Tpl](
  elements: List[Element],
  limit: Option[Int],
) extends DatomicModel2Query[Tpl](elements)
  with DatomicApiLoader // todo: necessary?
{


  lazy val limitNotZeroMsg = "Limit cannot be 0. " +
    "Please use a positive number to limit next rows, or a negative number to limit previous rows."

  protected def limitNotZero(): Unit = {
    if (limit.isDefined && limit.get == 0) {
      throw MoleculeError(limitNotZeroMsg)
    }
  }


  protected def postAdjustPullCasts() = {
    pullCastss = pullCastss :+ pullCasts.toList
    pullSortss = pullSortss :+ pullSorts.sortBy(_._1).map(_._2).toList
  }

  protected def postAdjustAritiess() = {
    // Remove started composite groups that turned out to have only tacit attributes
    aritiess = aritiess.map(_.filterNot(_.isEmpty))
  }


  protected def getUniqueValues(tpls0: List[Tpl], uniqueIndex: Int, encode: Any => String): List[String] = {
    val tpls   = (if (tpls0.head.isInstanceOf[Product]) tpls0 else tpls0.map(Tuple1(_))).asInstanceOf[List[Product]]
    val first3 = tpls.take(3).map(t => encode(t.productElement(uniqueIndex))).padTo(3, "")
    val last3  = tpls.takeRight(3).map(t => encode(t.productElement(uniqueIndex))).reverse.padTo(3, "").reverse
    first3 ++ last3
  }

  protected def getUniquePair(tpls: List[Tpl], uniqueIndex: Int, encode: Any => String): List[String] = {
    tpls.head match {
      case tpl: Product => List(
        encode(tpl.productElement(uniqueIndex)),
        encode(tpls.last.asInstanceOf[Product].productElement(uniqueIndex))
      )
      case v            => List(encode(v), encode(tpls.last))
    }
  }

  protected def getRawData(
    conn: DatomicConn_JVM,
    altElements: List[Element] = Nil
  ): jCollection[jList[AnyRef]] = {
    isFree = conn.isFreeVersion
    val db = conn.peerConn.db()
    getQueries(conn.optimizeQuery, altElements) match {
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
  }

  protected def sortRows(rows: jCollection[jList[AnyRef]]): jArrayList[jList[AnyRef]] = {
    val sorters = getFlatSorters(sortss)
    sorters.length match {
      case 0 => new jArrayList(rows)
      case n =>
        val nestedIdsCount = nestedIds.length
        val sortedRows     = new jArrayList(rows)
        val comparator     = new Comparator[Row] {
          override def compare(a: Row, b: Row): Int = {
            var i      = 0
            var result = 0;
            result = sorters(i)(nestedIdsCount)(a, b)
            i += 1
            while (result == 0 && i != n) {
              result = sorters(i)(nestedIdsCount)(a, b)
              i += 1
            }
            result
          }
        }
        Collections.sort(sortedRows, comparator)
        sortedRows
    }
  }



  //  def inspect(implicit conn0: DatomicConn_JVM, ec: ExecutionContext): Future[Unit] = {
  //    val conn = conn0.asInstanceOf[DatomicConn_JVM]
  //    isFree = conn.isFreeVersion
  //    val (preQuery, query) = getQueries(conn.optimizeQuery)
  //    val queries           = if (preQuery.isEmpty) query else {
  //      val preInp = if (preInputs.isEmpty) "" else
  //        s"\n\nPRE-INPUTS:\n" + preInputs.mkString("\n")
  //
  //      val preQ = if (preQuery.isEmpty) "" else
  //        s"\n\nPRE-Query:\n" + preQuery + preInp + "\n\n"
  //
  //      val inp = if (inputs.isEmpty) "" else
  //        s"\n\nINPUTS:\n" + inputs.mkString("\n")
  //
  //      s"""${preQ}QUERY:
  //         |$query$inp
  //         |
  //         |""".stripMargin
  //    }
  //    getList.map { rows =>
  //      val output = rows._1.take(100).zipWithIndex.map { case (row, i) =>
  //        s"ROW ${i + 1}:".padTo(7, ' ') + row
  //      }.mkString("\n")
  //      logger.info(
  //        s"""
  //           |--------------------------------------------------------------------------
  //           |${elements.mkString("\n")}
  //           |
  //           |$queries
  //           |
  //           |$output
  //           |(showing up to 100 rows)
  //           |--------------------------------------------------------------------------
  //      """.stripMargin
  //      )
  //    }
  //  }
}
