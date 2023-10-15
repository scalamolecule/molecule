package molecule.sql.core.spi

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.action.Query
import molecule.core.util.ModelUtils
import molecule.sql.core.javaSql.{ResultSetInterface => Row}
import scala.annotation.nowarn
import scala.collection.mutable.ListBuffer


trait SpiHelpers extends ModelUtils {

  def prepareMultipleUpdates(
    elements: List[Element],
    isUpsert: Boolean
  ): (List[Element], List[Long => List[Element]]) = {
    val update       = if (isUpsert) "upsert" else "update"
    val dummyCoord   = Seq(0, 0) // irrelevant for id columns that will never collide with keywords
    var firstNs      = true
    var prevNs       = ""
    val idsModel     = ListBuffer.empty[Element]
    val updateModel  = ListBuffer.empty[Element]
    var updateModels = List.empty[Long => List[Element]]
    elements.foreach {
      case a: Attr =>
        updateModel += a
        a match {
          case a: AttrOneMan =>
            prevNs = a.ns
            a match {
              case a: AttrOneManString         => idsModel += AttrOneTacString(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManInt            => idsModel += AttrOneTacInt(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManLong           => idsModel += AttrOneTacLong(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManFloat          => idsModel += AttrOneTacFloat(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManDouble         => idsModel += AttrOneTacDouble(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManBoolean        => idsModel += AttrOneTacBoolean(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManBigInt         => idsModel += AttrOneTacBigInt(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManBigDecimal     => idsModel += AttrOneTacBigDecimal(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManDate           => idsModel += AttrOneTacDate(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManDuration       => idsModel += AttrOneTacDuration(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManInstant        => idsModel += AttrOneTacInstant(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManLocalDate      => idsModel += AttrOneTacLocalDate(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManLocalTime      => idsModel += AttrOneTacLocalTime(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManLocalDateTime  => idsModel += AttrOneTacLocalDateTime(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManOffsetTime     => idsModel += AttrOneTacOffsetTime(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManOffsetDateTime => idsModel += AttrOneTacOffsetDateTime(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManZonedDateTime  => idsModel += AttrOneTacZonedDateTime(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManUUID           => idsModel += AttrOneTacUUID(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManURI            => idsModel += AttrOneTacURI(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManByte           => idsModel += AttrOneTacByte(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManShort          => idsModel += AttrOneTacShort(a.ns, a.attr, coord = a.coord)
              case a: AttrOneManChar           => idsModel += AttrOneTacChar(a.ns, a.attr, coord = a.coord)
            }
          case a: AttrOneTac => idsModel += a

          case a: AttrSetMan =>
            if (a.op == Eq || a.op == Add || a.op == Swap || a.op == Remove) {
              prevNs = a.ns
              a match {
                case a: AttrSetManString         => idsModel += AttrSetTacString(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManInt            => idsModel += AttrSetTacInt(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManLong           => idsModel += AttrSetTacLong(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManFloat          => idsModel += AttrSetTacFloat(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManDouble         => idsModel += AttrSetTacDouble(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManBoolean        => idsModel += AttrSetTacBoolean(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManBigInt         => idsModel += AttrSetTacBigInt(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManBigDecimal     => idsModel += AttrSetTacBigDecimal(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManDate           => idsModel += AttrSetTacDate(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManDuration       => idsModel += AttrSetTacDuration(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManInstant        => idsModel += AttrSetTacInstant(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManLocalDate      => idsModel += AttrSetTacLocalDate(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManLocalTime      => idsModel += AttrSetTacLocalTime(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManLocalDateTime  => idsModel += AttrSetTacLocalDateTime(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManOffsetTime     => idsModel += AttrSetTacOffsetTime(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManOffsetDateTime => idsModel += AttrSetTacOffsetDateTime(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManZonedDateTime  => idsModel += AttrSetTacZonedDateTime(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManUUID           => idsModel += AttrSetTacUUID(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManURI            => idsModel += AttrSetTacURI(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManByte           => idsModel += AttrSetTacByte(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManShort          => idsModel += AttrSetTacShort(a.ns, a.attr, coord = a.coord)
                case a: AttrSetManChar           => idsModel += AttrSetTacChar(a.ns, a.attr, coord = a.coord)
              }

            } else {
              throw ModelError(s"Unexpected $update operation for card-many attribute. Found:\n" + a)
            }

          case _: AttrOneOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
          case _: AttrSetTac => throw ModelError("Can only lookup entity with card-one attribute value. Found:\n" + a)
          case _: AttrSetOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
        }

      case ref@Ref(_, _, _, CardOne, coord) =>
        if (firstNs) {
          firstNs = false
          val tacitElements = updateModel.toList
          // First namespace already has a tacit id attribute
          updateModels = updateModels :+ ((_: Long) => tacitElements)

        } else if (prevNs.nonEmpty) {
          // Get id
          idsModel += AttrOneManLong(prevNs, "id", coord = coord)

          // Make update model once we get an id
          val ns            = prevNs
          val tacitElements = updateModel.toList
          updateModels = updateModels :+ ((id: Long) => AttrOneTacLong(ns, "id", Eq, Seq(id), coord = coord) +: tacitElements)
        }

        idsModel += ref
        prevNs = ""
        updateModel.clear()

      case ref: Ref => throw ModelError(
        s"Can't $update attributes in card-many referenced namespace `${ref.refAttr.capitalize}`"
      )

      case other => idsModel += other
    }

    // Add id to last ref ns
    if (prevNs.nonEmpty) {
      // Get id
      idsModel += AttrOneManLong(prevNs, "id", coord = dummyCoord)

      // Make update model once we get an id
      val id2updateModel = (id: Long) => AttrOneTacLong(prevNs, "id", Eq, Seq(id), coord = dummyCoord) +: updateModel.toList
      updateModels = updateModels :+ id2updateModel
    }

    (idsModel.toList, updateModels)
  }

  private type L = Long

  def getRefIds(refIdsAnyCardinality: List[Any]): List[Long] = {
    refIdsAnyCardinality.headOption.fold(List(0L)) {
      case a: L                                                                                                                                 => List(0L, a)
      case (a: L, b: L)                                                                                                                         => List(0L, a, b)
      case (a: L, b: L, c: L)                                                                                                                   => List(0L, a, b, c)
      case (a: L, b: L, c: L, d: L)                                                                                                             => List(0L, a, b, c, d)
      case (a: L, b: L, c: L, d: L, e: L)                                                                                                       => List(0L, a, b, c, d, e)
      case (a: L, b: L, c: L, d: L, e: L, f: L)                                                                                                 => List(0L, a, b, c, d, e, f)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L)                                                                                           => List(0L, a, b, c, d, e, f, g)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L)                                                                                     => List(0L, a, b, c, d, e, f, g, h)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L)                                                                               => List(0L, a, b, c, d, e, f, g, h, i)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L)                                                                         => List(0L, a, b, c, d, e, f, g, h, i, j)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L)                                                                   => List(0L, a, b, c, d, e, f, g, h, i, j, k)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L)                                                             => List(0L, a, b, c, d, e, f, g, h, i, j, k, l)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L)                                                       => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L)                                                 => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L)                                           => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L)                                     => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L)                               => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L)                         => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L)                   => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L, t: L)             => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L, t: L, u: L)       => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L, t: L, u: L, v: L) => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)
    }
  }

  @nowarn // Accept dynamic type parameter of returned Query
  def getIdQuery(
    elements: List[Element],
    isUpsert: Boolean = false
  ) = {
    if (isUpsert)
      throw ModelError("Can't upsert referenced attributes. Please update instead.")
    val (refIdsModel, updateModels) = prepareMultipleUpdates(elements, isUpsert)
    val idQuery                     = updateModels.size match {
      case 1  => Query[L](refIdsModel)
      case 2  => Query[(L, L)](refIdsModel)
      case 3  => Query[(L, L, L)](refIdsModel)
      case 4  => Query[(L, L, L, L)](refIdsModel)
      case 5  => Query[(L, L, L, L, L)](refIdsModel)
      case 6  => Query[(L, L, L, L, L, L)](refIdsModel)
      case 7  => Query[(L, L, L, L, L, L, L)](refIdsModel)
      case 8  => Query[(L, L, L, L, L, L, L, L)](refIdsModel)
      case 9  => Query[(L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 10 => Query[(L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 11 => Query[(L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 12 => Query[(L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 13 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 14 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 15 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 16 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 17 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 18 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 19 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 20 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 21 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 22 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
    }
    (idQuery, updateModels)
  }


  protected def nestedArray2coalescedSet(a: Attr, rs: Row, isAttr: Boolean = true): Set[Any] = {
    a match {
      case _: AttrSetManString         => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[String])
      case _: AttrSetManInt            => sql2set(isAttr, rs, (v: Any) => v.toString.toInt)
      case _: AttrSetManLong           => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Long])
      case _: AttrSetManFloat          => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Float])
      case _: AttrSetManDouble         => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Double])
      case _: AttrSetManBoolean        => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Boolean])
      case _: AttrSetManBigInt         => sql2set(isAttr, rs, (v: Any) => BigInt(v.toString))
      case _: AttrSetManBigDecimal     => sql2set(isAttr, rs, (v: Any) => BigDecimal(v.toString))
      case _: AttrSetManDate           => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Date])
      case _: AttrSetManDuration       => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Duration])
      case _: AttrSetManInstant        => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Instant])
      case _: AttrSetManLocalDate      => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[LocalDate])
      case _: AttrSetManLocalTime      => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[LocalTime])
      case _: AttrSetManLocalDateTime  => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[LocalDateTime])
      case _: AttrSetManOffsetTime     => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[OffsetTime])
      case _: AttrSetManOffsetDateTime => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[OffsetDateTime])
      case _: AttrSetManZonedDateTime  => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[ZonedDateTime])
      case _: AttrSetManUUID           => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[UUID])
      case _: AttrSetManURI            => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[String]).map(v => new URI(v))
      case _: AttrSetManByte           => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Integer].toByte)
      case _: AttrSetManShort          => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Integer].toShort)
      case _: AttrSetManChar           => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[String].charAt(0))
      case other                       => throw ModelError(
        "Unexpected attribute type for Set validation value retriever:\n" + other
      )
    }
  }


  private def sql2set[T](isAttr: Boolean, row: Row, j2s: Any => T): Set[T] = {
    if (isAttr)
      sqlNestedArrays2coalescedSet(row, j2s)
    else
      sqlArrays2coalescedSet(row, j2s)
  }

  private def sqlNestedArrays2coalescedSet[T](row: Row, j2s: Any => T): Set[T] = {
    row.next()
    val array = row.getArray(1)
    if (row.wasNull()) {
      Set.empty[T]
    } else {
      val resultSet = array.getResultSet
      var set       = Set.empty[T]
      while (resultSet.next()) {
        resultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
          set += j2s(value)
        }
      }
      set
    }
  }

  private def sqlArrays2coalescedSet[T](row: Row, j2s: Any => T): Set[T] = {
    val resultSet = row
    var set       = Set.empty[T]
    while (resultSet.next()) {
      resultSet.getArray(1).getArray.asInstanceOf[Array[_]].foreach { value =>
        set += j2s(value)
      }
    }
    set
  }

  protected def jsonArray2coalescedSet(a: Attr, rs: Row): Set[Any] = {
    rs.next()
    val json = rs.getString(1)
    a match {
      case _: AttrSetManString         => json.substring(2, json.length - 2).split("\", ?\"").toSet
      case _: AttrSetManInt            => json.substring(1, json.length - 1).split(", ?").map(_.toInt).toSet
      case _: AttrSetManLong           => json.substring(1, json.length - 1).split(", ?").map(_.toLong).toSet
      case _: AttrSetManFloat          => json.substring(1, json.length - 1).split(", ?").map(_.toFloat).toSet
      case _: AttrSetManDouble         => json.substring(1, json.length - 1).split(", ?").map(_.toDouble).toSet
      case _: AttrSetManBoolean        => json.substring(1, json.length - 1).split(", ?").map(_ == "1").toSet
      case _: AttrSetManBigInt         => json.substring(1, json.length - 1).split(", ?").map(BigInt(_)).toSet
      case _: AttrSetManBigDecimal     => json.substring(1, json.length - 1).split(", ?").map(BigDecimal(_)).toSet
      case _: AttrSetManDate           => json.substring(1, json.length - 1).split(", ?").map(v => new Date(v.toLong)).toSet
      case _: AttrSetManDuration       => json.substring(1, json.length - 1).split(", ?").map(v => Duration.parse(v)).toSet
      case _: AttrSetManInstant        => json.substring(1, json.length - 1).split(", ?").map(v => Instant.parse(v)).toSet
      case _: AttrSetManLocalDate      => json.substring(1, json.length - 1).split(", ?").map(v => LocalDate.parse(v)).toSet
      case _: AttrSetManLocalTime      => json.substring(1, json.length - 1).split(", ?").map(v => LocalTime.parse(v)).toSet
      case _: AttrSetManLocalDateTime  => json.substring(1, json.length - 1).split(", ?").map(v => LocalDateTime.parse(v)).toSet
      case _: AttrSetManOffsetTime     => json.substring(1, json.length - 1).split(", ?").map(v => OffsetTime.parse(v)).toSet
      case _: AttrSetManOffsetDateTime => json.substring(1, json.length - 1).split(", ?").map(v => OffsetDateTime.parse(v)).toSet
      case _: AttrSetManZonedDateTime  => json.substring(1, json.length - 1).split(", ?").map(v => ZonedDateTime.parse(v)).toSet
      case _: AttrSetManUUID           => json.substring(2, json.length - 2).split("\", ?\"").map(UUID.fromString).toSet
      case _: AttrSetManURI            => json.substring(2, json.length - 2).split("\", ?\"").map(new URI(_)).toSet
      case _: AttrSetManByte           => json.substring(1, json.length - 1).split(", ?").map(_.toByte).toSet
      case _: AttrSetManShort          => json.substring(1, json.length - 1).split(", ?").map(_.toShort).toSet
      case _: AttrSetManChar           => json.substring(2, json.length - 2).split("\", ?\"").map(_.charAt(0)).toSet
      case other                       => throw ModelError(
        "Unexpected attribute type for Set validation value retriever:\n" + other
      )
    }
  }
}
