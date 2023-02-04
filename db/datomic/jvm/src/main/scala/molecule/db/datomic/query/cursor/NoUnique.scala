package molecule.db.datomic.query.cursor

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
  with ApiUtils
  with CursorUtils
  with MoleculeLogging {


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
    val (rowHashes, attempts) = {
      val List(a, b, c, x, y, z) = tokens.takeRight(6)
      val hashes                 = (if (forward) List(z, y, x) else List(a, b, c)).filter(_.nonEmpty).map(_.toInt)
      (hashes, hashes.length)
    }

    //    println("filterAttr: " + filterAttr)
//    println("rowHashes: " + rowHashes)

    val altElements = filterAttr +: elements
    val rows        = getRawData(conn, altElements)
    val totalCount  = rows.size
    val sortedRows  = sortRows(rows)
    logger.debug(sortedRows.toArray().mkString("\n"))
    postAdjustAritiess()
    if (totalCount == 0) {
      (Nil, "", false)
    } else {
      val (from, until) = if (forward) (0, limit.min(totalCount)) else ((totalCount + limit).max(0), totalCount)
      val count         = until - from
      //      println("limit: " + limit)
      //      println("total: " + totalCount)
      //      println("from : " + from)
      //      println("until: " + until)
      //      println("count: " + count)
      val row2tpl       = castRow2Tpl(aritiess.head, castss.head, 0, None)
      var window        = false
      var i             = 0
      var more          = 0
      if (!forward)
        Collections.reverse(sortedRows)

//      println("first row hash: ")
//      sortedRows.forEach(row => println(row2tpl(row).asInstanceOf[Tpl].hashCode()))

      val tuples = ListBuffer.empty[Tpl]
      @tailrec
      def paginateFrom(hashCodes: List[Any]): Unit = {
        hashCodes match {
          case hashCode :: remainingHashCodes =>
//            println("---------- paginateFrom hash code: " + hashCode)
            sortedRows.forEach {
              case row if window && i != count       =>
//                println("A " + row)
                i += 1
                tuples += row2tpl(row).asInstanceOf[Tpl]
              case row if row2tpl(row).asInstanceOf[Tpl].hashCode() == hashCode =>
//                println("B " + row)
                window = true
              case row                               =>
                if (window)
                  more += 1
//                println("C " + row)
            }
            if (tuples.isEmpty) {
              // Try comparing next edge row
              paginateFrom(remainingHashCodes)
            }

          case Nil => throw MoleculeError(s"Couldn't find next page. Last $attempts rows were all deleted/updated.")
        }
      }
      paginateFrom(rowHashes)

      val tpls = if (forward) tuples.result() else tuples.result().reverse
//      println("tpls: " + tpls)
//      println("more: " + more)
      val cursor = nextCursorNoUnique(tpls, tokens)
      (tpls, cursor, more > 0)
    }
  }

  private def nextCursorNoUnique(tpls: List[Tpl], tokens: List[String]): String = {
    val attrTokens = tokens.drop(2).dropRight(6).grouped(13).toList.collect {
      case List(kind, dir, pos, tpe, ns, attr, uniqueIndex, _, _, _, _, _, _) =>
        val encode = encoder(tpe)
        List(kind, dir, pos, tpe, ns, attr, uniqueIndex) ++ getUniqueValues(tpls, uniqueIndex.toInt, encode)
    }.flatten
    val tokens1    = tokens.take(2) ++ attrTokens ++ getRowHashes(tpls)
    Base64.getEncoder.encodeToString(tokens1.mkString("\n").getBytes)
  }
}
