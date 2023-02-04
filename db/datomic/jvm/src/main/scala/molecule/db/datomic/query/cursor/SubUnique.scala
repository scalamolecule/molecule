package molecule.db.datomic.query.cursor

import java.util
import java.util.{Base64, Collections}
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.action.ApiUtils
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query.DatomicQuery
import scala.annotation.tailrec
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
  with ApiUtils with CursorUtils with MoleculeLogging {

  def getPage(tokens: List[String], limit: Int)
             (implicit conn: DatomicConn_JVM, ec: ExecutionContext)
  : Future[(List[Tpl], String, Boolean)] = future {
    val forward    = limit > 0
    val attrTokens = tokens.drop(2).grouped(13).toList.sortBy(_(2))

    // Filter query by primary non-unique sort attribute
    val filterAttr = {
      val List(_, dir, _, tpe, ns, attr, _, a, b, c, x, y, z) = attrTokens.head

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

    // Get row index for unique edge value to go from
    val (uniqueIndex, uniqueValues) = {
      val List(_, _, _, tpe, _, _, i, a, b, c, x, y, z) = attrTokens.find(_.head == "UNIQUE").get

      val decode       = decoder(tpe)
      val uniqueValues = (if (forward) List(z, y, x) else List(a, b, c)).filter(_.nonEmpty).map(decode)
      (i.toInt, uniqueValues)
    }

    val altElements = filterAttr +: elements
    val rows        = getRawData(conn, altElements)
    val sortedRows  = sortRows(rows)
    logger.debug(sortedRows.toArray().mkString("\n"))

    if (isNested) {
      val nestedTpls    = rows2nested(sortedRows)
      val toplevelCount = nestedTpls.length
      if (toplevelCount == 0) {
        (Nil, "", false)
      } else {
        val count          = if (forward) limit.min(toplevelCount) else toplevelCount - (toplevelCount + limit).max(0)
        val nestedTpls1    = if (forward) nestedTpls else nestedTpls.reverse
        val (tuples, more) = paginateTpls(uniqueValues, nestedTpls1, count, uniqueIndex)
        val tpls           = if (forward) tuples.result() else tuples.result().reverse
        val cursor         = nextCursorSubUnique(tpls, tokens)
        //        println("SUB UNIQUE Nested tpls: " + tpls)
        (tpls, cursor, more > 0)
      }

    } else {
      val totalCount = rows.size
      if (totalCount == 0) {
        (Nil, "", false)
      } else {
        if (isNestedOpt) {
          postAdjustPullCasts()
          val count = if (forward) limit.min(totalCount) else totalCount - (totalCount + limit).max(0)
          if (!forward) Collections.reverse(sortedRows)
          val (tuples, more) = paginateRows(uniqueValues, sortedRows, count, pullRow2tpl, uniqueIndex)
          val tpls           = if (forward) tuples.result() else tuples.result().reverse
          //          println("SUB UNIQUE NestedOpt tpls: " + tpls)
          val cursor         = nextCursorSubUnique(tpls, tokens)
          (tpls, cursor, more > 0)

        } else {
          postAdjustAritiess()
          val count   = if (forward) limit.min(totalCount) else totalCount - (totalCount + limit).max(0)
          val row2tpl = castRow2Tpl(aritiess.head, castss.head, 0, None)
          if (!forward) Collections.reverse(sortedRows)
          val (tuples, more) = paginateRows(uniqueValues, sortedRows, count, row2tpl, uniqueIndex)
          val tpls           = if (forward) tuples.result() else tuples.result().reverse
          //          println("SUB UNIQUE Flat tpls: " + tpls)
          val cursor         = nextCursorSubUnique(tpls, tokens)
          (tpls, cursor, more > 0)
        }
      }
    }
  }


  def paginateTpls(
    uniqueValues: List[Any],
    tpls: List[Tpl],
    count: Int,
    uniqueIndex: Int
  ): (ListBuffer[Tpl], Int) = {
    val tuples      = ListBuffer.empty[Tpl]
    val tplProducts = tpls.map(_.asInstanceOf[Product])
    var window      = false
    var i           = 0
    var more        = 0
    @tailrec
    def findFrom(uniqueValues: List[Any]): Unit = {
      uniqueValues match {
        case uniqueValue :: remainingUniqueValues =>
          //          println("---------- paginateFrom unique value: " + uniqueValue)
          tplProducts.foreach {
            case tplProduct if window && i != count                                  =>
              //              println("A " + tplProduct)
              i += 1
              tuples += tplProduct.asInstanceOf[Tpl]
            case tplProduct if tplProduct.productElement(uniqueIndex) == uniqueValue =>
              //              println("B " + tplProduct)
              window = true
            case tplProduct                                                          =>
              if (window) more += 1
            //              println("C " + tplProduct)
          }
          if (tuples.isEmpty) {
            // Recursively try with next unique edge value
            findFrom(remainingUniqueValues)
          }

        case Nil => throw MoleculeError(edgeValuesNotFound)
      }
    }
    findFrom(uniqueValues)
    (tuples, more)
  }


  def paginateRows(
    uniqueValues: List[Any],
    sortedRows: util.ArrayList[util.List[AnyRef]],
    count: Int,
    row2tpl: Row => Any,
    uniqueIndex: Int
  ): (ListBuffer[Tpl], Int) = {
    val tuples = ListBuffer.empty[Tpl]
    var window = false
    var i      = 0
    var more   = 0
    @tailrec
    def findFrom(uniqueValues: List[Any]): Unit = {
      uniqueValues match {
        case uniqueValue :: remainingUniqueValues =>
          //          println("---------- paginateFrom unique value: " + uniqueValue)
          sortedRows.forEach {
            case row if window && i != count                =>
              //              println("A " + row)
              i += 1
              tuples += row2tpl(row).asInstanceOf[Tpl]
            case row if row.get(uniqueIndex) == uniqueValue =>
              //              println("B " + row)
              window = true
            case row                                        =>
              if (window) more += 1
            //              println("C " + row)
          }
          if (tuples.isEmpty) {
            // Recursively try with next unique edge value
            findFrom(remainingUniqueValues)
          }

        case Nil => throw MoleculeError(edgeValuesNotFound)
      }
    }
    findFrom(uniqueValues)
    (tuples, more)
  }


  private def nextCursorSubUnique(tpls: List[Tpl], tokens: List[String]): String = {
    val attrTokens = tokens.drop(2).grouped(13).toList.collect {
      case List(kind, dir, pos, tpe, ns, attr, uniqueIndex, _, _, _, _, _, _) =>
        val encode = encoder(tpe, kind)
        List(kind, dir, pos, tpe, ns, attr, uniqueIndex) ++ getUniqueValues(tpls, uniqueIndex.toInt, encode)
    }.flatten
    val tokens1    = tokens.take(2) ++ attrTokens
    Base64.getEncoder.encodeToString(tokens1.mkString("\n").getBytes)
  }
}
