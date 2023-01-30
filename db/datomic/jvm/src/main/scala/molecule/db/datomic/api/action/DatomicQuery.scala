package molecule.db.datomic.api.action

import java.util.{Collections, Comparator, ArrayList => jArrayList, Collection => jCollection, List => jList}
import datomic.Peer
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.action.ApiUtils
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.pagination.Offset
import molecule.db.datomic.query.DatomicModel2Query
import molecule.db.datomic.util.DatomicApiLoader
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}

abstract class DatomicQuery[Tpl](
  elements: List[Element],
  limit: Option[Int],
  offset: Option[Int],
  cursor: Option[String]
) extends DatomicModel2Query[Tpl](elements)
  with ApiUtils with Offset with DatomicApiLoader with MoleculeLogging {

  //  // Post-adjustments of casts/arities
  //  if (isNestedOpt) {
  //    pullCastss = pullCastss :+ pullCasts.toList
  //    pullSortss = pullSortss :+ pullSorts.sortBy(_._1).map(_._2).toList
  //  } else if(!isNested) {
  //    // Flat rows
  //    // Remove started composite groups that turned out to have only tacit attributes
  //    aritiess = aritiess.map(_.filterNot(_.isEmpty))
  //  }


  // Handles both offset- and non-paginated results
  protected def getListOffset(implicit conn: DatomicConn_JVM,
                              ec: ExecutionContext
                             ): Future[(List[Tpl], Int)] = future {
    limitNotZero()
    if (offset.isDefined && limit.isDefined && limit.get >> 31 != offset.get >> 31) {
      throw MoleculeError("Limit and offset should both be positive or negative.")
    }
    val rows       = getRawData(conn)
    val totalCount = rows.size
    val sortedRows = sortRows(rows)
    logger.debug(sortedRows.toArray().mkString("\n"))

    //    if (totalCount == 0 || offset.getOrElse(0).abs > totalCount) {
    //      (List.empty[Tpl], 0)
    //    } else
    if (isNested) {
      val nestedRows    = rows2nested(sortedRows)
      val toplevelCount = nestedRows.length
      val fromUntil     = getFromUntil(toplevelCount, limit, offset)
      (offsetList(nestedRows, fromUntil), toplevelCount)

    } else {
      val fromUntil = getFromUntil(totalCount, limit, offset)
      val tuples    = ListBuffer.empty[Tpl]

      if (isNestedOpt) {
        postAdjustPullCasts()
        offsetRaw(sortedRows, fromUntil).forEach(row => tuples += pullRow2tpl(row))
        (tuples.result(), totalCount)

      } else {
        postAdjustAritiess()
        val row2tpl = castRow2Tpl(aritiess.head, castss.head, 0, None)
        offsetRaw(sortedRows, fromUntil).forEach(row => tuples += row2tpl(row).asInstanceOf[Tpl])
        (tuples.result(), totalCount)
      }
    }
  }

  private def postAdjustPullCasts() = {
    pullCastss = pullCastss :+ pullCasts.toList
    pullSortss = pullSortss :+ pullSorts.sortBy(_._1).map(_._2).toList
  }

  private def postAdjustAritiess() = {
    // Remove started composite groups that turned out to have only tacit attributes
    aritiess = aritiess.map(_.filterNot(_.isEmpty))
  }

  protected def getListCursor(implicit conn: DatomicConn_JVM,
                              ec: ExecutionContext
                             ): Future[(List[Tpl], String)] = future {
    limitNotZero()

    val unique = conn.proxy.uniqueAttrs

    //    @tailrec
    //    def checkTx(elements: List[Element], sortCoordinates: List[(String, Boolean)]): Unit = {
    //      elements match {
    //        case element :: tail =>
    //          element match {
    //            case a: Attr                                       =>
    //              if(a.sort.isDefined && unique.contains(a.name))
    //
    //            case _: TxMetaData                                      => addTxVar = true
    //            case AttrOneManLong("_Generic", "tx", _, _, _, _, _, _) => addTxVar = true
    //            case AttrOneTacLong("_Generic", "tx", _, _, _, _, _, _) => addTxVar = true
    //            case Composite(elements)                                => checkTx(elements ++ tail)
    //            case Nested(_, elements)                                => checkTx(tail ++ elements)
    //            case _                                                  => checkTx(tail)
    //          }
    //        case Nil             => ()
    //      }
    //    }
    //    checkTx(elements)


    ???
  }


  private def limitNotZero(): Unit = {
    if (limit.isDefined && limit.get == 0) {
      throw MoleculeError("Limit cannot be 0. " +
        "Please use a positive number to limit next rows, or a negative number to limit previous rows.")
    }
  }

  private def getRawData(conn: DatomicConn_JVM): jCollection[jList[AnyRef]] = {
    isFree = conn.isFreeVersion
    val db = conn.peerConn.db()
    getQueries(conn.optimizeQuery) match {
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

  private def sortRows(rows: jCollection[jList[AnyRef]]): jArrayList[jList[AnyRef]] = {
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

  private def offsetRaw(
    sortedRows: jArrayList[jList[AnyRef]],
    fromUntil: Option[(Int, Int)]
  ): jList[jList[AnyRef]] = {
    fromUntil.fold[jList[jList[AnyRef]]](sortedRows) {
      case (from, until) => sortedRows.subList(from, until)
    }
  }

  private def offsetList(
    sortedRows: List[Tpl],
    fromUntil: Option[(Int, Int)]
  ): List[Tpl] = {
    fromUntil.fold(sortedRows) {
      case (from, until) => sortedRows.slice(from, until)
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
