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
 * Molecule has a unique attribute that is not sorted first.
 *
 * We filter by the previous value of the primary non-unique attribute and then
 * loop until the previous value of the unique attribute is found and then take
 * the following rows.
 *
 * This is of course not optimal, especially if the primary standard sort
 * attribute contains few values and the data set is big.
 * *
 * Presumes that the row with the previous unique value hasn't been altered.
 *
 * @param elements Molecule model
 * @param limit    When going forward from start, use a positive number.
 *                 And vice versa from end with a negative number. Can't be zero.
 * @param cursor   Base64 encoded cursor meta information, including previous edge values.
 * @tparam Tpl Type of each row
 */
case class SubUnique[Tpl](
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
    val forward     = limit > 0
    val coordsByPos = coords.drop(2).grouped(9).toList.sortBy(_(2))

    // Filter query by primary non-unique sort attribute
    val filterAttr = {
      val List(_, dir, _, _, tpe, ns, attr, firstV, lastV) = coordsByPos.head

      val (fn, v) = (forward, dir) match {
        case (true, "a") => (Ge, lastV)
        case (true, _)   => (Le, firstV)
        case (_, "a")    => (Le, firstV)
        case (_, _)      => (Ge, lastV)
      }
      getFilterAttr(tpe, ns, attr, fn, v)
    }

    // Get row index for unique edge value to go from
    val (uniqueIndex, uniqueValue) = {
      val List("unique", dir, _, i, tpe, _, _, firstV, lastV) = coordsByPos.find(_.head == "unique").get

      val decode      = decoder(tpe)
      val uniqueValue = (forward, dir) match {
        case (true, "a") => decode(lastV)
        case (true, _)   => decode(lastV) // used on reversed rows
        case (_, "a")    => decode(firstV)
        case (_, _)      => decode(firstV) // used on reversed rows
      }
      (i.toInt, uniqueValue)
    }

    //    println("filterAttr: " + filterAttr)
    //    println("uniqueIndex: " + uniqueIndex)
    //    println("uniqueValue: " + uniqueValue)
    //    println("uniqueValue: " + uniqueValue.getClass)

    val altElements = filterAttr +: elements
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

      //      println("unique row value: " + sortedRows.get(0).get(uniqueIndex))
      //      println("unique row value: " + sortedRows.get(0).get(uniqueIndex).getClass)
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
