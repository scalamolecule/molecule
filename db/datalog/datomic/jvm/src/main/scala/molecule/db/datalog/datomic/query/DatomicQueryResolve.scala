package molecule.db.datalog.datomic.query

import java.util
import java.util.stream.Stream as jStream
import java.util.{Collections, Comparator, ArrayList as jArrayList, Collection as jCollection, List as jList}
import datomic.{Database, Peer}
import molecule.base.error.ModelError
import molecule.core.dataModel.*
import molecule.core.util.MoleculeLogging
import molecule.db.core.marshalling.dbView.*
import molecule.db.core.query.Pagination
import molecule.db.datalog.core.query.{DatomicQueryBase, Model2DatomicQuery}
import molecule.db.datalog.datomic.facade.DatomicConn_JVM
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

abstract class DatomicQueryResolve[Tpl](
  dataModel: DataModel,
  dbView: Option[DbView],
  m2q: Model2DatomicQuery[Tpl] & DatomicQueryBase
) extends Pagination[Tpl] with MoleculeLogging {


  protected def postAdjustPullCasts(): Unit = {
    m2q.pullCastss = m2q.pullCastss :+ m2q.pullCasts.toList
    m2q.pullSortss = m2q.pullSortss :+ m2q.pullSorts.sortBy(_._1).map(_._2).toList
  }

  def getRawData(
    conn: DatomicConn_JVM,
    altElements: List[Element] = Nil,
    altDb: Option[datomic.Database] = None,
    validate: Boolean = true
  ): jCollection[jList[AnyRef]] = {
    val db = altDb.getOrElse(getDb(conn))
    m2q.getDatomicQueries(conn.optimizeQuery, altElements, validate) match {
      case ("", query, _) =>
        distinct(Peer.q(query, db +: m2q.inputs *))

      case (preQuery, query, _) =>
        // Pre-query
        val preRows = Peer.q(preQuery, db +: m2q.preInputs *)
        val preIds  = new java.util.HashSet[Long](preRows.size())
        preRows.forEach { row =>
          preIds.add(row.get(0).asInstanceOf[Long])
        }
        // Main query using entity ids from pre-query
        distinct(Peer.q(query, db +: m2q.inputs :+ preIds *))
    }
  }

  def getJavaStream(
    conn: DatomicConn_JVM,
    altElements: List[Element] = Nil,
    altDb: Option[datomic.Database] = None,
    validate: Boolean = true
  ): jStream[jList[AnyRef]] = {
    val db = altDb.getOrElse(getDb(conn))
    m2q.getDatomicQueries(conn.optimizeQuery, altElements, validate) match {
      case ("", query, _) =>
        Peer.qseq(query, db +: m2q.inputs *).asInstanceOf[jStream[jList[AnyRef]]]

      case (preQuery, query, _) =>
        // Pre-query
        val preRows = Peer.q(preQuery, db +: m2q.preInputs *)
        val preIds  = new java.util.HashSet[Long](preRows.size())
        preRows.forEach { row =>
          preIds.add(row.get(0).asInstanceOf[Long])
        }
        // Main query using entity ids from pre-query
        Peer.qseq(query, db +: m2q.inputs :+ preIds *).asInstanceOf[jStream[jList[AnyRef]]]
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
    // Always return distinct rows
    new util.HashSet[jList[AnyRef]](rows)
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


  def paginateFromIdentifiers(
    conn: DatomicConn_JVM,
    limit: Int,
    forward: Boolean,
    allTokens: List[String],
    attrTokens: List[String],
    identifiers: List[Any],
    identifyTpl: Tpl => Any,
    identifyRow: Unit => m2q.Row => Any,
    nextCursor: (List[Tpl], List[String]) => String
  ): (List[Tpl], String, Boolean) = {
    // Filter query by primary non-unique sort attribute
    val filterAttr  = {
      val List(_, dir, _, tpe, ent, attr, _, a, b, c, x, y, z) = attrTokens

      // Filter by most inclusive value
      val first   = List(c, b, a).filter(_.nonEmpty).head
      val last    = List(x, y, z).filter(_.nonEmpty).head
      val (fn, v) = (forward, dir) match {
        case (true, "a") => (Ge, last)
        case (true, _)   => (Le, first)
        case (_, "a")    => (Le, first)
        case (_, _)      => (Ge, last)
      }
      getFilterAttr(tpe, ent, attr, fn, v)
    }
    val altElements = filterAttr +: dataModel.elements
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
        if (m2q.isOptNested) {
          postAdjustPullCasts()
          if (!forward) Collections.reverse(sortedRows)
          val count          = getCount(limit, forward, totalCount)
          val row2tpl        = m2q.castRow2AnyTpl(m2q.castss.head, 0)
          val (tuples, more) = paginateRows(count, sortedRows, identifiers, identifyRow(()), row2tpl)
          val tpls           = (if (forward) tuples else tuples.reverse).filterNot(_ == Nil)
          val cursor         = nextCursor(tpls, allTokens)
          (tpls, cursor, more > 0)

        } else if (m2q.nestedOptRef) {
          postAdjustPullCasts()
          if (!forward) Collections.reverse(sortedRows)
          val count          = getCount(limit, forward, totalCount)
          val row2tpl        = m2q.castRow2AnyTpl(m2q.castss.head, 0)
          val (tuples, more) = paginateRows(count, sortedRows, identifiers, identifyRow(()), row2tpl)
          val tpls           = (if (forward) tuples else tuples.reverse).filterNot(_ == Nil)
          val cursor         = nextCursor(tpls, allTokens)
          (tpls, cursor, more > 0)

        } else {
          if (!forward) Collections.reverse(sortedRows)
          val count          = getCount(limit, forward, totalCount)
          val row2AnyTpl     = m2q.castRow2AnyTpl(m2q.castss.head, 0)
          val row2tpl        = (row: m2q.Row) => row2AnyTpl(row).asInstanceOf[Tpl]
          val (tuples, more) = paginateRows(count, sortedRows, identifiers, identifyRow(()), row2tpl)
          val tpls           = if (forward) tuples else tuples.reverse
          val cursor         = nextCursor(tpls, allTokens)
          (tpls, cursor, more > 0)
        }
      }
    }
  }

  private def paginateRows(
    count: Int,
    sortedRows: jList[jList[AnyRef]],
    identifiers: List[Any],
    identify: m2q.Row => Any,
    row2tpl: m2q.Row => Any,
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
            case row if window && i != count        => i += 1; tuples += row2tpl(row).asInstanceOf[Tpl]
            case row if identify(row) == identifier => window = true
            case _                                  => if (window) more += 1
          }
          if (tuples.isEmpty) {
            // Recursively try with next identifier
            findFrom(remainingIdentifiers)
          }

        case Nil => throw ModelError(edgeValuesNotFound)
      }
    }
    findFrom(identifiers)
    (tuples.toList, more)
  }
}
