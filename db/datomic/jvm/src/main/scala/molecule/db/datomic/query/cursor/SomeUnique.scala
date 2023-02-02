package molecule.db.datomic.query.cursor

import java.util.{Base64, Collections}
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.action.ApiUtils
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query.DatomicQuery
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
case class SomeUnique[Tpl](
  elements: List[Element],
  limit: Option[Int],
  cursor: String
) extends DatomicQuery[Tpl](elements, limit)
  with ApiUtils
  with CursorUtils
  with MoleculeLogging {


  def getPage(coords: List[String], limit: Int)
             (implicit conn: DatomicConn_JVM, ec: ExecutionContext)
  : Future[(List[Tpl], String, Boolean)] = future {
    val forward                          = limit > 0
    val coordsByPos                      = coords.drop(2).grouped(9).toList.sortBy(_(2))
    val (_, filterAttrs, i, uniqueValue) = coordsByPos.foldLeft(1, List.empty[AttrOneTac], "0", "uv": Any) {
      case ((1, acc, _, _), List("unique", dir, _, i, tpe, _, _, firstV, lastV)) =>
        val decode  = decoder(tpe)
        val uniqueV = {
          (forward, dir) match {
            case (true, "a") => decode(lastV)
            case (true, _)   => decode(lastV)
            case (_, "a")    => decode(firstV)
            case (_, _)      => decode(firstV)
          }
        }
        (0, acc, i, uniqueV)

      case ((1, acc, ui, ut), List(_, dir, _, _, tpe, ns, attr, firstV, lastV)) =>
        val (fn, v) = {
          (forward, dir) match {
            case (true, "a") => (Ge, lastV)
            case (true, _)   => (Le, firstV)
            case (_, "a")    => (Le, firstV)
            case (_, _)      => (Ge, lastV)
          }
        }
        (1, acc :+ getFilterAttr(tpe, ns, attr, fn, v), ui, ut)

      case ((_, acc, ui, ut), _) => (0, acc, ui, ut)
    }

    val uniqueIndex = i.toInt

    //    filterAttrs.foreach(println)
    //    println("uniqueIndex: " + uniqueIndex)
    //    println("uniqueValue: " + uniqueValue)

    val altElements = filterAttrs ++ elements
    val rows        = getRawData(conn, altElements)
    val totalCount  = rows.size
    val sortedRows  = sortRows(rows)
    logger.debug(sortedRows.toArray().mkString("\n"))
    postAdjustAritiess()
    if (totalCount == 0) {
      (Nil, "", false)
    } else {
      val (from, until) = if (limit > 0) (0, limit.min(totalCount)) else ((totalCount + limit).max(0), totalCount)
      val count         = until - from
      //      println("limit: " + limit)
      //      println("total: " + totalCount)
      //      println("from : " + from)
      //      println("until: " + until)
      //      println("count: " + count)
      val tuples        = ListBuffer.empty[Tpl]
      val row2tpl       = castRow2Tpl(aritiess.head, castss.head, 0, None)
      var window        = false
      var i             = 0
      var more          = 0
      if (!forward)
        Collections.reverse(sortedRows)
      sortedRows.forEach {
        case row if window && i != count                =>
          //          println("A " + row)
          i += 1
          tuples += row2tpl(row).asInstanceOf[Tpl]
        case row if row.get(uniqueIndex) == uniqueValue =>
          //          println("B " + row)
          window = true
        case row                                        =>
          if (window)
            more += 1
        //          println("C " + row)
      }
      val tpls   = if (forward) tuples.result() else tuples.result().reverse
      //      println("tpls: " + tpls)
      //      println("more: " + more)
      val cursor = nextCursorSubUnique(tpls, coords)
      (tpls, cursor, more > 0)
    }
  }

  private def nextCursorSubUnique(tpls: List[Tpl], coords: List[String]): String = {
    val newAttrCoords = coords.drop(2).grouped(9).toList.collect {
      case List(unique, dir, pos, i, tpe, ns, attr, _, _) =>
        val encode = encoder(tpe)
        List(unique, dir, pos, i, tpe, ns, attr) ++ edgeVs(tpls, i.toInt, encode)
    }.flatten
    val coords1       = coords.take(2) ++ newAttrCoords
    Base64.getEncoder.encodeToString(coords1.mkString("\n").getBytes)
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
}
