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


case class NoUnique[Tpl](
  elements: List[Element],
  limit: Option[Int],
  cursor: String
) extends DatomicQuery[Tpl](elements, limit)
  with ApiUtils with CursorUtils with MoleculeLogging {

  def getPage(tokens: List[String], limit: Int)
             (implicit conn: DatomicConn_JVM, ec: ExecutionContext)
  : Future[(List[Tpl], String, Boolean)] = future {
    val forward    = limit > 0
    val attrTokens = tokens.drop(2).dropRight(6).grouped(13).toList.sortBy(_(2))

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
    val rowHashes = {
      val List(a, b, c, x, y, z) = tokens.takeRight(6)
      (if (forward) List(z, y, x) else List(a, b, c)).filter(_.nonEmpty).map(_.toInt)
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
        val (tuples, more) = paginateTpls(rowHashes, nestedTpls1, count)
        val tpls           = if (forward) tuples.result() else tuples.result().reverse
        val cursor         = nextCursorNoUnique(tpls, tokens)
        //        println("NO UNIQUE Nested tpls: " + tpls)
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
          val (tuples, more) = paginateRows(rowHashes, sortedRows, count, pullRow2tpl)
          val tpls           = if (forward) tuples.result() else tuples.result().reverse
          //          println("NO UNIQUE NestedOpt tpls: " + tpls)
          val cursor         = nextCursorNoUnique(tpls, tokens)
          (tpls, cursor, more > 0)

        } else {
          postAdjustAritiess()
          val count   = if (forward) limit.min(totalCount) else totalCount - (totalCount + limit).max(0)
          val row2tpl = castRow2Tpl(aritiess.head, castss.head, 0, None)
          if (!forward) Collections.reverse(sortedRows)
          val (tuples, more) = paginateRows(rowHashes, sortedRows, count, row2tpl)
          val tpls           = if (forward) tuples.result() else tuples.result().reverse
          //          println("NO UNIQUE Flat tpls: " + tpls)
          val cursor         = nextCursorNoUnique(tpls, tokens)
          (tpls, cursor, more > 0)
        }
      }
    }
  }

  def paginateTpls(
    hashCodes: List[Any],
    tpls: List[Tpl],
    count: Int,
  ): (ListBuffer[Tpl], Int) = {
    val tuples = ListBuffer.empty[Tpl]
    var window = false
    var i      = 0
    var more   = 0
    @tailrec
    def findFrom(hashCodes: List[Any]): Unit = {
      hashCodes match {
        case hashCode :: remainingHashCodes =>
          //          println("---------- paginateFrom unique value: " + hashCode)
          tpls.foreach {
            case tpl if window && i != count       =>
              //              println("A " + tpl)
              i += 1
              tuples += tpl
            case tpl if tpl.hashCode() == hashCode =>
              //              println("B " + tpl)
              window = true
            case tpl                               =>
              if (window) more += 1
            //              println("C " + tpl)
          }
          if (tuples.isEmpty) {
            // Recursively try with next unique edge value
            findFrom(remainingHashCodes)
          }

        case Nil => throw MoleculeError(edgeValuesNotFound)
      }
    }
    findFrom(hashCodes)
    (tuples, more)
  }


  def paginateRows(
    hashCodes: List[Any],
    sortedRows: util.ArrayList[util.List[AnyRef]],
    count: Int,
    row2tpl: Row => Any
  ): (ListBuffer[Tpl], Int) = {
    val tuples = ListBuffer.empty[Tpl]
    var window = false
    var i      = 0
    var more   = 0
    @tailrec
    def findFrom(hashCodes: List[Any]): Unit = {
      hashCodes match {
        case hashCode :: remainingHashCodes =>
          //          println("---------- paginateFrom hash code: " + hashCode)
          sortedRows.forEach {
            case row if window && i != count                                  =>
              //              println("A " + row)
              i += 1
              tuples += row2tpl(row).asInstanceOf[Tpl]
            case row if row2tpl(row).asInstanceOf[Tpl].hashCode() == hashCode =>
              //              println("B " + row)
              window = true
            case row                                                          =>
              if (window) more += 1
            //              println("C " + row)
          }
          if (tuples.isEmpty) {
            // Recursively try comparing next edge row
            findFrom(remainingHashCodes)
          }

        case Nil => throw MoleculeError(edgeValuesNotFound)
      }
    }
    findFrom(hashCodes)
    (tuples, more)
  }

  private def nextCursorNoUnique(tpls: List[Tpl], tokens: List[String]): String = {
    val attrTokens = tokens.drop(2).dropRight(6).grouped(13).toList.collect {
      case List(kind, dir, pos, tpe, ns, attr, uniqueIndex, _, _, _, _, _, _) =>
        val encode = encoder(tpe, kind)
        List(kind, dir, pos, tpe, ns, attr, uniqueIndex) ++ getUniqueValues(tpls, uniqueIndex.toInt, encode)
    }.flatten
    val tokens1    = tokens.take(2) ++ attrTokens ++ getRowHashes(tpls)
    Base64.getEncoder.encodeToString(tokens1.mkString("\n").getBytes)
  }
}
