package molecule.sql.jdbc.query

import java.util.Base64
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.dbView.DbView
import molecule.core.util.FutureUtils
import molecule.datalog.core.query.cursor.CursorUtils
import molecule.sql.jdbc.facade.JdbcConn_JVM
import molecule.sql.jdbc.query.cursorStrategy.{NoUnique, PrimaryUnique, SubUnique}
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
case class JdbcQueryResolveCursor[Tpl](
  elements: List[Element],
  limit: Option[Int],
  cursor: Option[String],
  dbView: Option[DbView]
) extends JdbcQueryResolve[Tpl](elements, dbView)
  with FutureUtils
  with CursorUtils
  with ModelTransformations_
  with MoleculeLogging {


  def getListFromCursor_async(implicit conn: JdbcConn_JVM, ec: ExecutionContext)
  : Future[(List[Tpl], String, Boolean)] = future(getListFromCursor_sync)


  def getListFromCursor_sync(implicit conn: JdbcConn_JVM)
  : (List[Tpl], String, Boolean) = {
    limit match {
      case Some(l) => cursor match {
        case Some("")     => getInitialPage(l)
        case Some(cursor) =>
          val raw    = new String(Base64.getDecoder.decode(cursor))
          val tokens = raw.split("\n").toList

          val strategy :: hash :: _ = tokens
          if ((elements.hashCode() & 0xFFFFF) != hash.toInt) {
            throw ModelError("Can only use cursor for un-modified query.")
          } else {
            strategy match {
              case "1" => PrimaryUnique(elements, limit, cursor, dbView).getPage(tokens, l)
              case "2" => SubUnique(elements, limit, cursor, dbView).getPage(tokens, l)
              case "3" => NoUnique(elements, limit, cursor, dbView).getPage(tokens, l)
            }
          }
        case None         => throw ModelError("Unexpected undefined cursor.")
      }
      case None    => throw ModelError("Please set limit to use cursor pagination.")
    }
  }


  private def getInitialPage(limit: Int)(implicit conn: JdbcConn_JVM)
  : (List[Tpl], String, Boolean) = {
    val forward     = limit > 0
    val altElements = if (forward) elements else reverseTopLevelSorting(elements)
    val rows        = getRawData(conn, altElements)
    val sortedRows  = sortRows(rows)
    logger.debug(sortedRows.toArray().mkString("\n"))

    if (isNested) {
      val nestedRows    = rows2nested(sortedRows)
      val toplevelCount = nestedRows.length
      val limitAbs      = limit.abs.min(toplevelCount)
      val hasMore       = limitAbs < toplevelCount
      val selectedRows  = nestedRows.take(limitAbs)
      val tpls          = if (forward) selectedRows else selectedRows.reverse
      val cursor        = initialCursor(conn, tpls)
      (tpls, cursor, hasMore)

    } else {
      val totalCount = rows.size
      val limitAbs   = limit.abs.min(totalCount)
      val hasMore    = limitAbs < totalCount
      val tuples     = ListBuffer.empty[Tpl]

      if (isNestedOpt) {
        postAdjustPullCasts()
        if (totalCount == 0) {
          (Nil, "", false)
        } else {
          val selectedRows = sortedRows.subList(0, limitAbs)
          selectedRows.forEach(row => tuples += pullRow2tpl(row))
          val tpls   = if (forward) tuples.result() else tuples.result().reverse
          val cursor = initialCursor(conn, tpls)
          (tpls, cursor, hasMore)
        }

      } else {
        postAdjustAritiess()
        if (totalCount == 0) {
          (Nil, "", false)
        } else {
          val row2tpl      = castRow2AnyTpl(aritiess.head, castss.head, 0, None)
          val selectedRows = sortedRows.subList(0, limitAbs)
          selectedRows.forEach(row => tuples += row2tpl(row).asInstanceOf[Tpl])
          val tpls   = if (forward) tuples.result() else tuples.result().reverse
          val cursor = initialCursor(conn, tpls)
          (tpls, cursor, hasMore)
        }
      }
    }
  }


  private def initialCursor(conn: JdbcConn_JVM, tpls: List[Tpl]): String = {
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

                } else if (isNearUnique || unique.contains(a.name)) {
                  if (pos == "1") {
                    // 1. Unique primary sort attribute
                    val (tpe, encode) = tpeEncode(a)
                    val initTokens    = List("1", getHash, tpe, a.ns, a.attr, i.toString)
                    val uniqueValues  = getUniquePair(tpls, i, encode)
                    // We can use this exclusively. So we don't need more meta data
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

            case Composite(elements) => checkSort(elements ++ tail, strategy, tokens, i, rowHashes)

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