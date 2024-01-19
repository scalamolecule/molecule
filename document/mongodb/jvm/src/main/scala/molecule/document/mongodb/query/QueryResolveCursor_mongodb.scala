package molecule.document.mongodb.query

import java.util.Base64
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.query.Pagination
import molecule.core.util.FutureUtils
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.query.cursorStrategy.{NoUnique, PrimaryUnique, SubUnique}
import scala.annotation.tailrec

case class QueryResolveCursor_mongodb[Tpl](
  elements: List[Element],
  optLimit: Option[Int],
  cursor: Option[String],
  m2q: Model2MongoQuery[Tpl]
) extends QueryResolve_mongodb[Tpl](elements, m2q)
  with FutureUtils
  with Pagination
  with ModelTransformations_
  with MoleculeLogging {

  def getListFromCursor_sync(implicit conn: MongoConn_JVM)
  : (List[Tpl], String, Boolean) = {
    optLimit match {
      case Some(limit) => cursor match {
        case Some("")     => getInitialPage(limit)
        case Some(cursor) =>
          val raw      = new String(Base64.getDecoder.decode(cursor))
          val tokens   = raw.split("\n").toList
          val strategy = tokens.head
          val hash     = tokens(1)
          if ((elements.hashCode() & 0xFFFFF) != hash.toInt) {
            throw ModelError("Can only use cursor for un-modified query.")
          } else {
            strategy match {
              case "1" => PrimaryUnique(elements, optLimit, cursor, m2q).getPage(tokens, limit)
              case "2" => SubUnique(elements, optLimit, cursor, m2q).getPage(tokens, limit)
              case "3" => NoUnique(elements, optLimit, cursor, m2q).getPage(tokens, limit)
            }
          }
        case None         => throw ModelError("Unexpected undefined cursor.")
      }
      case None        => throw ModelError("Please set limit to use cursor pagination.")
    }
  }


  private def getInitialPage(limit: Int)(implicit conn: MongoConn_JVM)
  : (List[Tpl], String, Boolean) = {
    val forward      = limit > 0
    val altElements  = if (forward) elements else reverseTopLevelSorting(elements)
//    val sortedRows   = getRawData(conn, altElements, Some(limit.abs), None)
//    val sortedRows1  = new ResultSetImpl(sortedRows)
//    val flatRowCount = m2q.getRowCount(sortedRows1)
    val flatRowCount = 42

    if (flatRowCount == 0) {
      (Nil, "", false)
    } else {
      if (m2q.isNestedMan || m2q.isNestedOpt) {
//        val nestedRows    = if (m2q.isNested) m2q.rows2nested(sortedRows1) else m2q.rows2nestedOpt(sortedRows1)
//        val topLevelCount = nestedRows.length
//        val limitAbs      = limit.abs.min(topLevelCount)
//        val hasMore       = limitAbs < topLevelCount
//        val selectedRows  = nestedRows.take(limitAbs)
//        val tpls          = if (forward) selectedRows else selectedRows.reverse
//        val cursor        = initialCursor(conn, tpls)
//        (tpls, cursor, hasMore)
        ???

      } else {
//        val totalCount = getTotalCount(conn)
//        val limitAbs   = limit.abs.min(totalCount)
//        val hasMore    = limitAbs < totalCount
//        val tuples     = ListBuffer.empty[Tpl]
//        val row2tpl    = m2q.castRow2AnyTpl(m2q.aritiess.head, m2q.castss.head, 1, None)
//        while (sortedRows.next()) {
//          tuples += row2tpl(sortedRows1).asInstanceOf[Tpl]
//        }
//        val result = if (forward) tuples.result() else tuples.result().reverse
//        val cursor = initialCursor(conn, result)
//        (result, cursor, hasMore)
        ???
      }
    }
  }

  private def initialCursor(conn: MongoConn_JVM, tpls: List[Tpl]): String = {
    val unique = conn.proxy.uniqueAttrs
    @tailrec
    def checkSort(
      elements: List[Element],
      strategy: Int,
      tokens: List[String],
      i: Int,
      rowHashes: List[String],
    ): List[String] = {
      elements match {
        case element :: tail =>
          element match {
            case a: AttrOne if a.isInstanceOf[Tacit] => checkSort(tail, strategy, tokens, i, rowHashes)
            case a: AttrOne                          =>
              if (a.sort.isDefined) {
                val sort                = a.sort.get
                val (dir, pos)          = (sort.head.toString, sort.last.toString)
                val (isNearUnique, opt) = {
                  a match {
                    case _: AttrOneManDate              => (true, false)
                    case _: AttrOneOptDate              => (false, true)
                    case _ if a.isInstanceOf[Mandatory] => (false, false)
                    case _                              => (false, true)
                  }
                }
                if (opt) {
                  if (pos == "1")
                    throw ModelError(
                      s"Can't use optional attribute (`${a.name}`) as primary sort attribute with cursor pagination."
                    )
                  // We use row hashes only when there's no unique sort attributes
                  val init          = setStrategy(3, tokens)
                  val (tpe, encode) = tpeEncode(a)
                  val attrTokens    = List("OPTIONAL", dir, pos, tpe, a.ns, a.attr, i.toString)
                  val uniqueValues  = getUniqueValues(tpls, i, encode)
                  val rowHashes1    = if (rowHashes.nonEmpty) rowHashes else getRowHashes(tpls)
                  checkSort(tail, 3, init ++ attrTokens ++ uniqueValues, i + 1, rowHashes1)

                } else if (isNearUnique || unique.contains(a.cleanName)) {
                  if (pos == "1") {
                    // 1. Unique primary sort attribute
                    val (tpe, encode) = tpeEncode(a)
                    val initTokens    = List("1", getHash, tpe, a.ns, a.attr, i.toString)
                    val uniqueValues  = getUniquePair(tpls, i, encode)
                    // We can use this exclusively. So we don't need more data
                    checkSort(Nil, 1, initTokens ++ uniqueValues, -1, Nil)

                  } else {
                    // 2. Unique sub-sort attribute
                    val strategy1     = 2.min(strategy)
                    val init          = setStrategy(strategy1, tokens)
                    val (tpe, encode) = tpeEncode(a)
                    val attrTokens    = List("UNIQUE", dir, pos, tpe, a.ns, a.attr, i.toString)
                    val uniqueValues  = getUniqueValues(tpls, i, encode)
                    // We might have a primary non-unique sort attribute after. So we continue
                    checkSort(tail, strategy1, init ++ attrTokens ++ uniqueValues, i + 1, Nil)
                  }

                } else {
                  // 3. Non-unique sort attribute (strategy 1 or 2 still possible..)
                  val strategy1     = 3.min(strategy)
                  val init          = setStrategy(strategy1, tokens)
                  val (tpe, encode) = tpeEncode(a)
                  val attrTokens    = List("MANDATORY", dir, pos, tpe, a.ns, a.attr, i.toString)
                  val uniqueValues  = getUniqueValues(tpls, i, encode)
                  val rowHashes1    = if (rowHashes.nonEmpty) rowHashes else getRowHashes(tpls)
                  checkSort(tail, strategy1, init ++ attrTokens ++ uniqueValues, i + 1, rowHashes1)
                }

              } else {
                // Non-sorted attribute
                checkSort(tail, strategy, tokens, i, rowHashes)
              }

            // Only top level sorting - ignore nested and tx meta data
            case _ => checkSort(tail, strategy, tokens, i, rowHashes)
          }

        case Nil if strategy == 3 => tokens ++ rowHashes
        case Nil                  => tokens
      }
    }

    val tokens = checkSort(elements, 10, Nil, 0, Nil)
    Base64.getEncoder.encodeToString(tokens.mkString("\n").getBytes)
  }

  private def setStrategy(strategy: Int, tokens: List[String]): List[String] = {
    if (tokens.isEmpty)
      List(strategy.toString, getHash)
    else
      List(strategy.toString, tokens(1)) ++ tokens.drop(2)
  }

  private def getHash: String = (elements.hashCode() & 0xFFFFF).toString
}
