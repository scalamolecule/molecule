package molecule.datalog.datomic.query

import java.util
import java.util.{Collections, Comparator, ArrayList => jArrayList, Collection => jCollection, List => jList}
import datomic.{Database, Peer}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.dbView._
import molecule.datalog.core.query.cursor.CursorUtils
import molecule.datalog.core.query.{DatomicQueryBase, Model2DatomicQuery}
import molecule.datalog.datomic.facade.DatomicConn_JVM
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer


abstract class DatomicQueryResolve[Tpl](
  elements: List[Element],
  dbView: Option[DbView],
  m2q: Model2DatomicQuery[Tpl] with DatomicQueryBase
) extends CursorUtils with MoleculeLogging {


  protected def postAdjustPullCasts(): Unit = {
    m2q.pullCastss = m2q.pullCastss :+ m2q.pullCasts.toList
    m2q.pullSortss = m2q.pullSortss :+ m2q.pullSorts.sortBy(_._1).map(_._2).toList
  }

  protected def getUniqueValues(tpls0: List[Tpl], uniqueIndex: Int, encode: Any => String): List[String] = {
    val tpls   = (if (tpls0.head.isInstanceOf[Product]) tpls0 else tpls0.map(Tuple1(_))).asInstanceOf[List[Product]]
    val first3 = tpls.take(3).map(t => encode(t.productElement(uniqueIndex))).padTo(3, "")
    val last3  = tpls.takeRight(3).map(t => encode(t.productElement(uniqueIndex))).reverse.padTo(3, "").reverse
    first3 ++ last3
  }

  protected def getRowHashes(tpls: List[Tpl]): List[String] = {
    val first3 = tpls.take(3).map(row => row.hashCode().toString).padTo(3, "")
    val last3  = tpls.takeRight(3).map(row => row.hashCode().toString).reverse.padTo(3, "").reverse
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
    altElements: List[Element] = Nil,
    altDb: Option[datomic.Database] = None
  ): jCollection[jList[AnyRef]] = {
    val db = altDb.getOrElse(getDb(conn))
    m2q.getDatomicQueries(conn.optimizeQuery, altElements) match {
      case ("", query, _)       =>
        distinct(Peer.q(query, db +: m2q.inputs: _*))
      case (preQuery, query, _) =>
        // Pre-query
        val preRows = Peer.q(preQuery, db +: m2q.preInputs: _*)
        val preIds  = new java.util.HashSet[Long](preRows.size())
        preRows.forEach { row =>
          preIds.add(row.get(0).asInstanceOf[Long])
        }
        // Main query using entity ids from pre-query
        distinct(Peer.q(query, db +: m2q.inputs :+ preIds: _*))
    }
  }

  private def getDb(conn: DatomicConn_JVM): Database = {
    dbView.fold(conn.peerConn.db()) {
      case AsOf(TxLong(tx))  => conn.peerConn.db().asOf(tx)
      case AsOf(TxDate(d))   => conn.peerConn.db().asOf(d)
      case Since(TxLong(tx)) => conn.peerConn.db().since(tx)
      case Since(TxDate(d))  => conn.peerConn.db().since(d)
    }
  }


  private def distinct(rows: jCollection[jList[AnyRef]]): jCollection[jList[AnyRef]] = {
    if (m2q.hasOptAttr)
      new util.HashSet[jList[AnyRef]](rows)
    else
      rows
  }


  protected def sortRows(rows: jCollection[jList[AnyRef]]): jArrayList[jList[AnyRef]] = {
    val sorters = m2q.getFlatSorters(m2q.sortss)
    sorters.length match {
      case 0 => new jArrayList(rows)
      case n =>
        val nestedIdsCount = m2q.nestedIds.length
        val sortedRows     = new jArrayList(rows)
        val comparator     = new Comparator[m2q.Row] {
          override def compare(a: m2q.Row, b: m2q.Row): Int = {
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


  protected def offsetRaw(
    sortedRows: jArrayList[jList[AnyRef]],
    fromUntil: Option[(Int, Int, Boolean)]
  ): jList[jList[AnyRef]] = {
    fromUntil.fold[jList[jList[AnyRef]]](sortedRows) {
      case (from, until, _) => sortedRows.subList(from, until)
    }
  }

  protected def getFromUntil(
    tc: Int,
    limit: Option[Int],
    offset: Option[Int]
  ): Option[(Int, Int, Boolean)] = {
    (offset, limit) match {
      case (None, None)                => None
      case (None, Some(l)) if l > 0    => Some((0, l.min(tc), l < tc))
      case (None, Some(l))             => Some(((tc + l).max(0), tc, (tc + l) > 0))
      case (Some(o), None) if o > 0    => Some((o.min(tc), tc, o < tc))
      case (Some(o), None)             => Some((0, (tc + o).min(tc), -o < tc))
      case (Some(o), Some(l)) if l > 0 => Some((o.min(tc), (o + l).min(tc), (o + l) < tc))
      case (Some(o), Some(l))          => Some(((tc + o + l).max(0), (tc + o).max(0), (tc + o + l).max(0) > 0))
    }
  }

  def paginateFromIdentifiers(
    conn: DatomicConn_JVM,
    limit: Int,
    forward: Boolean,
    allTokens: List[String],
    attrTokens: List[String],
    identifiers: List[Any],
    identifyTpl: Tpl => Any,
    identifyRow: Boolean => m2q.Row => Any,
    nextCursor: (List[Tpl], List[String]) => String
  ): (List[Tpl], String, Boolean) = {
    // Filter query by primary non-unique sort attribute
    val filterAttr  = {
      val List(_, dir, _, tpe, ns, attr, _, a, b, c, x, y, z) = attrTokens

      // Filter by most inclusive value
      val first   = List(c, b, a).filter(_.nonEmpty).head
      val last    = List(x, y, z).filter(_.nonEmpty).head
      val (fn, v) = (forward, dir) match {
        case (true, "a") => (Ge, last)
        case (true, _)   => (Le, first)
        case (_, "a")    => (Le, first)
        case (_, _)      => (Ge, last)
      }
      getFilterAttr(tpe, ns, attr, fn, v)
    }
    val altElements = filterAttr +: elements
    val rows        = getRawData(conn, altElements)
    val sortedRows  = sortRows(rows)
    logger.debug(sortedRows.toArray().mkString("\n"))

    if (sortedRows.size() == 0) {
      (Nil, "", false)
    } else {
      if (m2q.isNested) {
        val nestedTpls     = m2q.rows2nested(sortedRows)
        val totalCount     = nestedTpls.length
        val count          = getCount(limit, forward, totalCount)
        val nestedTpls1    = if (forward) nestedTpls else nestedTpls.reverse
        val (tuples, more) = paginateTpls(count, nestedTpls1, identifiers, identifyTpl)
        val tpls           = if (forward) tuples else tuples.reverse
        val cursor         = nextCursor(tpls, allTokens)
        (tpls, cursor, more > 0)

      } else {
        val totalCount = rows.size
        if (m2q.isNestedOpt) {
          postAdjustPullCasts()
          if (!forward) Collections.reverse(sortedRows)
          val count          = getCount(limit, forward, totalCount)
          val (tuples, more) = paginateRows(count, sortedRows, identifiers, identifyRow(true), m2q.pullRow2tpl)
          val tpls           = if (forward) tuples else tuples.reverse
          val cursor         = nextCursor(tpls, allTokens)
          (tpls, cursor, more > 0)

        } else {
          if (!forward) Collections.reverse(sortedRows)
          val count          = getCount(limit, forward, totalCount)
          val row2AnyTpl     = m2q.castRow2AnyTpl(m2q.aritiess.head, m2q.castss.head, 0, None)
          val row2tpl        = (row: m2q.Row) => row2AnyTpl(row).asInstanceOf[Tpl]
          val (tuples, more) = paginateRows(count, sortedRows, identifiers, identifyRow(false), row2tpl)
          val tpls           = if (forward) tuples else tuples.reverse
          val cursor         = nextCursor(tpls, allTokens)
          (tpls, cursor, more > 0)
        }
      }
    }
  }

  private def getCount(limit: Int, forward: Boolean, totalCount: Int) = {
    if (forward)
      limit.min(totalCount)
    else
      totalCount - (totalCount + limit).max(0)
  }

  def paginateTpls(
    count: Int,
    tpls: List[Tpl],
    identifiers: List[Any],
    identify: Tpl => Any
  ): (List[Tpl], Int) = {
    val tuples = ListBuffer.empty[Tpl]
    var window = false
    var i      = 0
    var more   = 0
    @tailrec
    def findFrom(identifiers: List[Any]): Unit = {
      identifiers match {
        case identifier :: remainingIdentifiers =>
          tpls.foreach {
            case tpl if window && i != count        => i += 1; tuples += tpl
            case tpl if identify(tpl) == identifier => window = true
            case _                                  => if (window) more += 1
          }
          if (tuples.isEmpty) {
            // Recursively try with next identifier
            findFrom(remainingIdentifiers)
          }

        case Nil => throw ModelError("Couldn't find next page. Edge row tuples were all deleted/updated.")
      }
    }
    findFrom(identifiers)
    (tuples.result(), more)
  }


  def paginateRows(
    count: Int,
    sortedRows: jList[jList[AnyRef]],
    identifiers: List[Any],
    identify: m2q.Row => Any,
    row2tpl: m2q.Row => Tpl,
  ): (List[Tpl], Int) = {
    val tuples = ListBuffer.empty[Tpl]
    var window = false
    var i      = 0
    var more   = 0
    @tailrec
    def findFrom(identifiers: List[Any]): Unit = {
      identifiers match {
        case identifier :: remainingIdentifiers =>
          sortedRows.forEach {
            case row if window && i != count        => i += 1; tuples += row2tpl(row)
            case row if identify(row) == identifier => window = true
            case _                                  => if (window) more += 1
          }
          if (tuples.isEmpty) {
            // Recursively try with next identifier
            findFrom(remainingIdentifiers)
          }

        case Nil => throw ModelError("Couldn't find next page. Edge rows were all deleted/updated.")
      }
    }
    findFrom(identifiers)
    (tuples.result(), more)
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
