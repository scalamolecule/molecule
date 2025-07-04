package molecule.db.core.query

import java.net.URI
import java.time.*
import java.util.{Base64, Date, UUID}
import molecule.base.error.ModelError
import molecule.base.util.BaseHelpers
import molecule.core.dataModel.*
import molecule.db.core.spi.Conn
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer


trait Pagination[Tpl] extends BaseHelpers {

  protected def getFromUntil(
    tc: Int,
    limit: Option[Int],
    offset: Option[Int]
  ): Option[(Int, Int, Boolean)] = {
    (offset, limit) match {
      case (None, None)             => None
      case (None, Some(l)) if l > 0 => Some((0, l.min(tc), l < tc))
      case (None, Some(l))          => Some(((tc + l).max(0), tc, (tc + l) > 0))

      // When only offset is set, there will be no further rows in either directions
      case (Some(o), None) if o > 0 => Some((o.min(tc), tc, false))
      case (Some(o), None)          => Some((0, (tc + o).min(tc), false))

      case (Some(o), Some(l)) if l > 0 => Some((o.min(tc), (o + l).min(tc), (o + l) < tc))
      case (Some(o), Some(l))          => Some(((tc + o + l).max(0), (tc + o).max(0), (tc + o + l).max(0) > 0))
    }
  }

  protected def paginationCoords(
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): (Boolean, Boolean) = {
    offsetLimitCheck(optLimit, optOffset)
    val isPaginated = optLimit.isDefined || optOffset.isDefined
    val isForward   = optLimit.fold(true)(_ >= 0) && optOffset.fold(true)(_ >= 0)
    (isPaginated, isForward)
  }

  protected def offsetLimitCheck(optLimit: Option[Int], optOffset: Option[Int]): Unit = {
    lazy val limitSign  = optLimit.get >> 31
    lazy val offsetSign = optOffset.get >> 31
    if (optOffset.isDefined && optLimit.isDefined && optOffset.get != 0 && limitSign != offsetSign) {
      throw ModelError("Limit and offset should both be positive or negative.")
    }
  }

  protected def initialCursor(conn: Conn, elements: List[Element], tpls: List[Tpl]): String = {
    val unique = conn.proxy.metaDb.uniqueAttrs
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
                  val attrTokens    = List("OPTIONAL", dir, pos, tpe, a.ent, a.attr, i.toString)
                  val uniqueValues  = getUniqueValues(tpls, i, encode)
                  val rowHashes1    = if (rowHashes.nonEmpty) rowHashes else getRowHashes(tpls)
                  checkSort(tail, 3, init ++ attrTokens ++ uniqueValues, i + 1, rowHashes1)

                } else if (isNearUnique || unique.contains(a.cleanName)) {
                  if (pos == "1") {
                    // 1. Unique primary sort attribute
                    val (tpe, encode) = tpeEncode(a)
                    val initTokens    = List("1", getHash, tpe, a.ent, a.attr, i.toString)
                    val uniqueValues  = getUniquePair(tpls, i, encode)
                    // We can use this exclusively. So we don't need more data
                    checkSort(Nil, 1, initTokens ++ uniqueValues, -1, Nil)

                  } else {
                    // 2. Unique sub-sort attribute
                    val strategy1     = 2.min(strategy)
                    val init          = setStrategy(strategy1, tokens)
                    val (tpe, encode) = tpeEncode(a)
                    val attrTokens    = List("UNIQUE", dir, pos, tpe, a.ent, a.attr, i.toString)
                    val uniqueValues  = getUniqueValues(tpls, i, encode)
                    // We might have a primary non-unique sort attribute after. So we continue
                    checkSort(tail, strategy1, init ++ attrTokens ++ uniqueValues, i + 1, Nil)
                  }

                } else {
                  // 3. Non-unique sort attribute (strategy 1 or 2 still possible..)
                  val strategy1     = 3.min(strategy)
                  val init          = setStrategy(strategy1, tokens)
                  val (tpe, encode) = tpeEncode(a)
                  val attrTokens    = List("MANDATORY", dir, pos, tpe, a.ent, a.attr, i.toString)
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

    def setStrategy(strategy: Int, tokens: List[String]): List[String] = {
      if (tokens.isEmpty)
        List(strategy.toString, getHash)
      else
        List(strategy.toString, tokens(1)) ++ tokens.drop(2)
    }

    def getHash: String = (elements.hashCode() & 0xFFFFF).toString

    val tokens = checkSort(elements, 10, Nil, 0, Nil)
    Base64.getEncoder.encodeToString(tokens.mkString("\n").getBytes)
  }


  private def getUniqueValues(tpls0: List[Tpl], uniqueIndex: Int, encode: Any => String): List[String] = {
    val tpls   = (if (tpls0.head.isInstanceOf[Product]) tpls0 else tpls0.map(Tuple1(_))).asInstanceOf[List[Product]]
    val first3 = tpls.take(3).map(t => encode(t.productElement(uniqueIndex))).padTo(3, "")
    val last3  = tpls.takeRight(3).map(t => encode(t.productElement(uniqueIndex))).reverse.padTo(3, "").reverse
    first3 ++ last3
  }

  private def getRowHashes(tpls: List[Tpl]): List[String] = {
    val first3 = tpls.take(3).map(row => row.hashCode().toString).padTo(3, "")
    val last3  = tpls.takeRight(3).map(row => row.hashCode().toString).reverse.padTo(3, "").reverse
    first3 ++ last3
  }

  private def getUniquePair(tpls: List[Tpl], uniqueIndex: Int, encode: Any => String): List[String] = {
    tpls.head match {
      case tpl: Product => List(
        encode(tpl.productElement(uniqueIndex)),
        encode(tpls.last.asInstanceOf[Product].productElement(uniqueIndex))
      )
      case v            => List(encode(v), encode(tpls.last))
    }
  }

  lazy val edgeValuesNotFound = "Couldn't find next page. Edge rows were all deleted/updated."

  protected def paginateTpls(
    count: Int,
    tpls: List[Tpl],
    identifiers: List[Any],
    identify: Tpl => Any
  ): (List[Tpl], Int) = {
    val tuples = ListBuffer.empty[Tpl]
    var window = false
    var i      = 0
    var more   = 0
    @tailrec
    def findFrom(identifiers: List[Any]): Unit = {
      identifiers match {
        case identifier :: remainingIdentifiers =>
          tpls.foreach {
            case tpl if window && i != count        => i += 1; tuples += tpl
            case tpl if identify(tpl) == identifier => window = true
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

  protected def getCount(limit: Int, forward: Boolean, totalCount: Int) = {
    if (forward)
      limit.min(totalCount)
    else
      totalCount - (totalCount + limit).max(0)
  }

  protected def nextCursorUniques(tpls: List[Tpl], tokens: List[String]): String = {
    val List(_, _, tpe, _, _, i, _, _) = tokens
    val tokens1                        = tokens.dropRight(2) ++ getUniquePair(tpls, i.toInt, encoder(tpe, ""))
    Base64.getEncoder.encodeToString(tokens1.mkString("\n").getBytes)
  }

  protected def nextCursorSubUnique(tpls: List[Tpl], tokens: List[String]): String = {
    val attrTokens = tokens.drop(2).grouped(13).toList.collect {
      case List(kind, dir, pos, tpe, ent, attr, uniqueIndex, _, _, _, _, _, _) =>
        List(kind, dir, pos, tpe, ent, attr, uniqueIndex) ++
          getUniqueValues(tpls, uniqueIndex.toInt, encoder(tpe, kind))
    }.flatten
    val tokens1    = tokens.take(2) ++ attrTokens
    Base64.getEncoder.encodeToString(tokens1.mkString("\n").getBytes)
  }


  protected def nextCursorNoUnique(tpls: List[Tpl], tokens: List[String]): String = {
    val attrTokens = tokens.drop(2).dropRight(6).grouped(13).toList.collect {
      case List(kind, dir, pos, tpe, ent, attr, uniqueIndex, _, _, _, _, _, _) =>
        List(kind, dir, pos, tpe, ent, attr, uniqueIndex) ++
          getUniqueValues(tpls, uniqueIndex.toInt, encoder(tpe, kind))
    }.flatten
    val tokens1    = tokens.take(2) ++ attrTokens ++ getRowHashes(tpls)
    Base64.getEncoder.encodeToString(tokens1.mkString("\n").getBytes)
  }

  protected def offsetList(
    sortedRows: List[Tpl],
    fromUntil: Option[(Int, Int, Boolean)]
  ): List[Tpl] = {
    fromUntil.fold(sortedRows) {
      case (from, until, _) => sortedRows.slice(from, until)
    }
  }

  protected def tpeEncode(element: AttrOne): (String, Any => String) = {
    element match {
      case a: AttrOneMan =>
        a match {
          case _: AttrOneManID             => ("String", (v: Any) => v.toString)
          case _: AttrOneManString         => ("String", (v: Any) => escStr(v.toString))
          case _: AttrOneManInt            => ("Int", (v: Any) => v.toString)
          case _: AttrOneManLong           => ("Long", (v: Any) => v.toString)
          case _: AttrOneManFloat          => ("Float", (v: Any) => v.toString)
          case _: AttrOneManDouble         => ("Double", (v: Any) => v.toString)
          case _: AttrOneManBoolean        => ("Boolean", (v: Any) => v.toString)
          case _: AttrOneManBigInt         => ("BigInt", (v: Any) => v.toString)
          case _: AttrOneManBigDecimal     => ("BigDecimal", (v: Any) => v.toString)
          case _: AttrOneManDate           => ("Date", (v: Any) => date2str(v.asInstanceOf[Date]))
          case _: AttrOneManDuration       => ("Duration", (v: Any) => v.asInstanceOf[Duration].toString)
          case _: AttrOneManInstant        => ("Instant", (v: Any) => v.asInstanceOf[Instant].toString)
          case _: AttrOneManLocalDate      => ("LocalDate", (v: Any) => v.asInstanceOf[LocalDate].toString)
          case _: AttrOneManLocalTime      => ("LocalTime", (v: Any) => v.asInstanceOf[LocalTime].toString)
          case _: AttrOneManLocalDateTime  => ("LocalDateTime", (v: Any) => v.asInstanceOf[LocalDateTime].toString)
          case _: AttrOneManOffsetTime     => ("OffsetTime", (v: Any) => v.asInstanceOf[OffsetTime].toString)
          case _: AttrOneManOffsetDateTime => ("OffsetDateTime", (v: Any) => v.asInstanceOf[OffsetDateTime].toString)
          case _: AttrOneManZonedDateTime  => ("ZonedDateTime", (v: Any) => v.asInstanceOf[ZonedDateTime].toString)
          case _: AttrOneManUUID           => ("UUID", (v: Any) => v.toString)
          case _: AttrOneManURI            => ("URI", (v: Any) => v.toString)
          case _: AttrOneManByte           => ("Byte", (v: Any) => v.toString)
          case _: AttrOneManShort          => ("Short", (v: Any) => v.toString)
          case _: AttrOneManChar           => ("Char", (v: Any) => v.toString)
        }
      case a: AttrOneOpt =>
        a match {
          case _: AttrOneOptID             => ("String", (v: Any) => v.toString)
          case _: AttrOneOptString         => ("String", (v: Any) => escStr(v.toString))
          case _: AttrOneOptInt            => ("Int", (v: Any) => v.toString)
          case _: AttrOneOptLong           => ("Long", (v: Any) => v.toString)
          case _: AttrOneOptFloat          => ("Float", (v: Any) => v.toString)
          case _: AttrOneOptDouble         => ("Double", (v: Any) => v.toString)
          case _: AttrOneOptBoolean        => ("Boolean", (v: Any) => v.toString)
          case _: AttrOneOptBigInt         => ("BigInt", (v: Any) => v.toString)
          case _: AttrOneOptBigDecimal     => ("BigDecimal", (v: Any) => v.toString)
          case _: AttrOneOptDate           => ("Date", (v: Any) => v.toString) // (row hash used instead)
          case _: AttrOneOptDuration       => ("Duration", (v: Any) => v.toString)
          case _: AttrOneOptInstant        => ("Instant", (v: Any) => v.toString)
          case _: AttrOneOptLocalDate      => ("LocalDate", (v: Any) => v.toString)
          case _: AttrOneOptLocalTime      => ("LocalTime", (v: Any) => v.toString)
          case _: AttrOneOptLocalDateTime  => ("LocalDateTime", (v: Any) => v.toString)
          case _: AttrOneOptOffsetTime     => ("OffsetTime", (v: Any) => v.toString)
          case _: AttrOneOptOffsetDateTime => ("OffsetDateTime", (v: Any) => v.toString)
          case _: AttrOneOptZonedDateTime  => ("ZonedDateTime", (v: Any) => v.toString)
          case _: AttrOneOptUUID           => ("UUID", (v: Any) => v.toString)
          case _: AttrOneOptURI            => ("URI", (v: Any) => v.toString)
          case _: AttrOneOptByte           => ("Byte", (v: Any) => v.toString)
          case _: AttrOneOptShort          => ("Short", (v: Any) => v.toString)
          case _: AttrOneOptChar           => ("Char", (v: Any) => v.toString)
        }
      case other         => throw ModelError("Unexpected element for tpeEncode: " + other)
    }
  }

  protected def encoder(tpe: String, kind: String): Any => String = {
    if (kind == "OPTIONAL") {
      tpe match {
        case "String"         => (v: Any) => v.toString
        case "Int"            => (v: Any) => v.toString
        case "Long"           => (v: Any) => v.toString
        case "Float"          => (v: Any) => v.toString
        case "Double"         => (v: Any) => v.toString
        case "Boolean"        => (v: Any) => v.toString
        case "BigInt"         => (v: Any) => v.toString
        case "BigDecimal"     => (v: Any) => v.toString
        case "Date"           => (v: Any) => v.asInstanceOf[Option[Date]].map(date2str(_)).toString
        case "Duration"       => (v: Any) => v.toString
        case "Instant"        => (v: Any) => v.toString
        case "LocalDate"      => (v: Any) => v.toString
        case "LocalTime"      => (v: Any) => v.toString
        case "LocalDateTime"  => (v: Any) => v.toString
        case "OffsetTime"     => (v: Any) => v.toString
        case "OffsetDateTime" => (v: Any) => v.toString
        case "ZonedDateTime"  => (v: Any) => v.toString
        case "UUID"           => (v: Any) => v.toString
        case "URI"            => (v: Any) => v.toString
        case "Byte"           => (v: Any) => v.toString
        case "Short"          => (v: Any) => v.toString
        case "Char"           => (v: Any) => v.toString
      }
    }
    else
      tpe match {
        case "String"         => (v: Any) => v.toString
        case "Int"            => (v: Any) => v.toString
        case "Long"           => (v: Any) => v.toString
        case "Float"          => (v: Any) => v.toString
        case "Double"         => (v: Any) => v.toString
        case "Boolean"        => (v: Any) => v.toString
        case "BigInt"         => (v: Any) => v.toString
        case "BigDecimal"     => (v: Any) => v.toString
        case "Date"           => (v: Any) => date2str(v.asInstanceOf[Date])
        case "Duration"       => (v: Any) => v.toString
        case "Instant"        => (v: Any) => v.toString
        case "LocalDate"      => (v: Any) => v.toString
        case "LocalTime"      => (v: Any) => v.toString
        case "LocalDateTime"  => (v: Any) => v.toString
        case "OffsetTime"     => (v: Any) => v.toString
        case "OffsetDateTime" => (v: Any) => v.toString
        case "ZonedDateTime"  => (v: Any) => v.toString
        case "UUID"           => (v: Any) => v.toString
        case "URI"            => (v: Any) => v.toString
        case "Byte"           => (v: Any) => v.toString
        case "Short"          => (v: Any) => v.toString
        case "Char"           => (v: Any) => v.toString
      }
  }

  protected def decoder(tpe: String): String => Any = {
    tpe match {
      case "String"         => (v: String) => unescStr(v)
      case "Int"            => (v: String) => v.toInt
      case "Long"           => (v: String) => v.toLong
      case "Float"          => (v: String) => v.toFloat
      case "Double"         => (v: String) => v.toDouble
      case "Boolean"        => (v: String) => v.toBoolean
      case "BigInt"         => (v: String) => BigInt(v)
      case "BigDecimal"     => (v: String) => BigDecimal(v)
      case "Date"           => (v: String) => str2date(v)
      case "Duration"       => (v: String) => Duration.parse(v)
      case "Instant"        => (v: String) => Instant.parse(v)
      case "LocalDate"      => (v: String) => LocalDate.parse(v)
      case "LocalTime"      => (v: String) => LocalTime.parse(v)
      case "LocalDateTime"  => (v: String) => LocalDateTime.parse(v)
      case "OffsetTime"     => (v: String) => OffsetTime.parse(v)
      case "OffsetDateTime" => (v: String) => OffsetDateTime.parse(v)
      case "ZonedDateTime"  => (v: String) => ZonedDateTime.parse(v)
      case "UUID"           => (v: String) => UUID.fromString(v)
      case "URI"            => (v: String) => new URI(v)
      case "Byte"           => (v: String) => v.toByte
      case "Short"          => (v: String) => v.toShort
      case "Char"           => (v: String) => v.charAt(0)
    }
  }

  // Decode String value from cursor to java value for direct comparison with raw row value
  protected def decoder2(tpe: String): String => Any = {
    tpe match {
      case "String"         => (v: String) => unescStr(v)
      case "Int"            => (v: String) => v.toInt
      case "Long"           => (v: String) => v.toLong
      case "Float"          => (v: String) => v.toFloat
      case "Double"         => (v: String) => v.toDouble
      case "Boolean"        => (v: String) => v.toBoolean
      case "BigInt"         => (v: String) => BigInt(v).bigInteger
      case "BigDecimal"     => (v: String) => BigDecimal(v).bigDecimal
      case "Date"           => (v: String) => str2date(v)
      case "Duration"       => (v: String) => v // All java.time types are saved as Strings
      case "Instant"        => (v: String) => v
      case "LocalDate"      => (v: String) => v
      case "LocalTime"      => (v: String) => v
      case "LocalDateTime"  => (v: String) => v
      case "OffsetTime"     => (v: String) => v
      case "OffsetDateTime" => (v: String) => v
      case "ZonedDateTime"  => (v: String) => v
      case "UUID"           => (v: String) => UUID.fromString(v)
      case "URI"            => (v: String) => new URI(v)
      case "Byte"           => (v: String) => v.toByte
      case "Short"          => (v: String) => v.toShort
      case "Char"           => (v: String) => v // compare String
    }
  }

  protected def getFilterAttr(tpe: String, ent: String, attr: String, fn: Op, v: String): AttrOneTac = {
    tpe match {
      case "String"         => AttrOneTacString(ent, attr, fn, Seq(unescStr(v)))
      case "Int"            => AttrOneTacInt(ent, attr, fn, Seq(v.toInt))
      case "Long"           => AttrOneTacLong(ent, attr, fn, Seq(v.toLong))
      case "Float"          => AttrOneTacFloat(ent, attr, fn, Seq(v.toFloat))
      case "Double"         => AttrOneTacDouble(ent, attr, fn, Seq(v.toDouble))
      case "Boolean"        => AttrOneTacBoolean(ent, attr, fn, Seq(v.toBoolean))
      case "BigInt"         => AttrOneTacBigInt(ent, attr, fn, Seq(BigInt(v)))
      case "BigDecimal"     => AttrOneTacBigDecimal(ent, attr, fn, Seq(BigDecimal(v)))
      case "Date"           => AttrOneTacDate(ent, attr, fn, Seq(str2date(v)))
      case "Duration"       => AttrOneTacDuration(ent, attr, fn, Seq(Duration.parse(v)))
      case "Instant"        => AttrOneTacInstant(ent, attr, fn, Seq(Instant.parse(v)))
      case "LocalDate"      => AttrOneTacLocalDate(ent, attr, fn, Seq(LocalDate.parse(v)))
      case "LocalTime"      => AttrOneTacLocalTime(ent, attr, fn, Seq(LocalTime.parse(v)))
      case "LocalDateTime"  => AttrOneTacLocalDateTime(ent, attr, fn, Seq(LocalDateTime.parse(v)))
      case "OffsetTime"     => AttrOneTacOffsetTime(ent, attr, fn, Seq(OffsetTime.parse(v)))
      case "OffsetDateTime" => AttrOneTacOffsetDateTime(ent, attr, fn, Seq(OffsetDateTime.parse(v)))
      case "ZonedDateTime"  => AttrOneTacZonedDateTime(ent, attr, fn, Seq(ZonedDateTime.parse(v)))
      case "UUID"           => AttrOneTacUUID(ent, attr, fn, Seq(UUID.fromString(v)))
      case "URI"            => AttrOneTacURI(ent, attr, fn, Seq(new URI(v)))
      case "Byte"           => AttrOneTacByte(ent, attr, fn, Seq(v.toByte))
      case "Short"          => AttrOneTacShort(ent, attr, fn, Seq(v.toShort))
      case "Char"           => AttrOneTacChar(ent, attr, fn, Seq(v.charAt(0)))
    }
  }
}
