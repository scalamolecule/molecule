package molecule.db.datomic.query

import java.util.Base64
import molecule.base.util.exceptions.MoleculeError
import molecule.base.util.{BaseHelpers, DateHandling}
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.action.ApiUtils
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query.cursor._
import molecule.db.datomic.util.DatomicApiLoader
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}

/**
 *
 * @param elements Molecule model
 * @param limit    When going forward from start, use a positive number.
 *                 And vice versa from end with a negative number. Can't be zero.
 * @param cursor   Base64 encoded cursor meta information
 * @tparam Tpl Type of each row
 */
case class DatomicQueryCursor[Tpl](
  elements: List[Element],
  limit: Option[Int],
  cursor: Option[String]
) extends DatomicQuery[Tpl](elements, limit)
  with ApiUtils
  with CursorUtils
  with ModelTransformations
  with MoleculeLogging {

  /**
   *
   * @param conn
   * @param ec
   * @return Future[(rows, cursor, hasMore)]
   */
  def getListFromCursor(implicit conn: DatomicConn_JVM, ec: ExecutionContext)
  : Future[(List[Tpl], String, Boolean)] = {
    limit match {
      case Some(0) => Future.failed(MoleculeError(limitNotZeroMsg))
      case Some(l) => cursor match {
        case Some("")     => getInitialPage(l)
        case Some(cursor) =>
          val raw                   = new String(Base64.getDecoder.decode(cursor))
          val coords                = raw.split("\n").toList
          //          println("coords: " + coords)
          val strategy :: hash :: _ = coords
          if ((elements.hashCode() & 0xFFFFF) != hash.toInt) {
            Future.failed(MoleculeError("Can only use cursor for un-modified query."))
          } else {
            strategy match {
              case "1" => PrimaryUnique(elements, limit, cursor).getPage(coords, l)
              case "2" => SubUnique(elements, limit, cursor).getPage(coords, l)
            }
          }
        case None         => Future.failed(MoleculeError("Unexpected undefined cursor."))
      }
      case None    => throw MoleculeError("Please set limit to use cursor pagination.")
    }
  }

  private def getInitialPage(limit: Int)(implicit conn: DatomicConn_JVM, ec: ExecutionContext)
  : Future[(List[Tpl], String, Boolean)] = future {
    val forward     = limit > 0
    val altElements = if (forward) elements else reverseTopLevelSorting(elements)
    val rows        = getRawData(conn, altElements)
    val totalCount  = rows.size
    val sortedRows  = sortRows(rows)
    val limitAbs    = limit.abs.min(totalCount)
    logger.debug(sortedRows.toArray().mkString("\n"))

    //    if (isNested) {
    //      val nestedRows    = rows2nested(sortedRows)
    //      val toplevelCount = nestedRows.length
    //      val fromUntil     = getFromUntil(toplevelCount, limit, offset)
    //      val fromUntil     = ()
    //      val hasMore          = fromUntil.fold(totalCount > 0)(_._3)
    //      (offsetList(nestedRows, fromUntil), "xx", hasMore)
    //
    //    } else {
    //      val fromUntil = getFromUntil(totalCount, limit, offset)
    //      val hasMore      = fromUntil.fold(totalCount > 0)(_._3)
    //      val tuples    = ListBuffer.empty[Tpl]
    //
    //      if (isNestedOpt) {
    //        postAdjustPullCasts()
    //        offsetRaw(sortedRows, fromUntil).forEach(row => tuples += pullRow2tpl(row))
    //        (tuples.result(), "xx", hasMore)
    //
    //      } else {
    //        postAdjustAritiess()
    //        val row2tpl = castRow2Tpl(aritiess.head, castss.head, 0, None)
    //        offsetRaw(sortedRows, fromUntil).forEach(row => tuples += row2tpl(row).asInstanceOf[Tpl])
    //        (tuples.result(), "xx", hasMore)
    //      }
    //    }
    postAdjustAritiess()
    if (totalCount == 0) {
      (Nil, "", false)
    } else {
      val hasMore = limitAbs < totalCount
      val tuples  = ListBuffer.empty[Tpl]
      val row2tpl = castRow2Tpl(aritiess.head, castss.head, 0, None)
      sortedRows.subList(0, limitAbs).forEach(row => tuples += row2tpl(row).asInstanceOf[Tpl])
      val tpls   = if (forward) tuples.result() else tuples.result().reverse
      val cursor = initialCursor(conn, tpls)
      //      println("INITIAL RESULT: " + tpls)
      //      println("INITIAL RESULT: " + cursor)
      //      println("INITIAL RESULT: " + hasMore)
      (tpls, cursor, hasMore)
    }
  }


  private def edgeVs(tpls: List[Tpl], i: Int, encode: Any => String): List[String] = {
    tpls.head match {
      case tpl: Product => List(
        encode(tpl.productElement(i)),
        encode(tpls.last.asInstanceOf[Product].productElement(i))
      )
      case v            => List(encode(v), encode(tpls.last))
    }
  }

  private def initialCursor(conn: DatomicConn_JVM, tpls: List[Tpl]): String = {
    val unique = conn.proxy.uniqueAttrs
    @tailrec
    def checkSort(
      elements: List[Element],
      strategy: Int,
      coords: List[String],
      i: Int
    ): List[String] = {
      elements match {
        case element :: tail =>
          element match {
            case a: AttrOne if a.isInstanceOf[Tacit] => checkSort(tail, strategy, coords, i)
            case a: AttrOne                          =>
              if (a.sort.isDefined) {
                val sort       = a.sort.get
                val (dir, pos) = (sort.head.toString, sort.last.toString)
                if (unique.contains(a.name)) {
                  if (pos == "1") {
                    // 1. Unique primary sort attribute.
                    val (tpe, encode) = tpeEncode(a)
                    val edgeValues    = edgeVs(tpls, i, encode)
                    val coords1       = List("1", getHash, i.toString, tpe, a.ns, a.attr) ++ edgeValues
                    // We can use this exclusively. So we don't need more meta data
                    checkSort(Nil, 1, coords1, -1)

                  } else {
                    // 2. Unique sub-sort attribute
                    val strategy1     = 2.min(strategy)
                    val init          = if (coords.isEmpty)
                      List(strategy1.toString, getHash) ++ coords.drop(2)
                    else
                      List(strategy1.toString, coords(1)) ++ coords.drop(2)
                    val (tpe, encode) = tpeEncode(a)
                    val edgeValues    = edgeVs(tpls, i, encode)
                    val coords1       = List("unique", dir, pos, i.toString, tpe, a.ns, a.attr) ++ edgeValues
                    // We might have a primary non-unique sort attribute after. So we continue
                    checkSort(tail, strategy1, init ++ coords1, i + 1)
                  }

                } else if (List("Date").contains(a.toString)) {
                  // 3. Near-unique sort attribute
                  checkSort(tail, 3.min(strategy), coords, i + 1)

                } else {
                  // 4. Non-unique sort attribute
                  val strategy1     = 4.min(strategy)
                  val init          = if (coords.isEmpty)
                    List(strategy1.toString, getHash) ++ coords.drop(2)
                  else
                    List(strategy1.toString, coords(1)) ++ coords.drop(2)
                  val (tpe, encode) = tpeEncode(a)
                  val edgeValues    = edgeVs(tpls, i, encode)
                  val coords1       = List("standard", dir, pos, i.toString, tpe, a.ns, a.attr) ++ edgeValues
                  checkSort(tail, strategy1, init ++ coords1, i + 1)
                }

              } else {
                // Non-sorted attribute
                checkSort(tail, strategy, coords, i)
              }

            case Composite(elements) => checkSort(elements ++ tail, strategy, coords, i)
            case _                   => checkSort(tail, strategy, coords, i)
          }
        case Nil             => coords
      }
    }
    val coords = checkSort(elements, 10, Nil, 0)
    Base64.getEncoder.encodeToString(coords.mkString("\n").getBytes)
  }

  private def getHash: String = (elements.hashCode() & 0xFFFFF).toString
}
