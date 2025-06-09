package molecule.db.sql.core.spi

import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import molecule.base.error.ModelError
import molecule.core.ast.*
import molecule.db.core.util.ModelUtils
import molecule.db.sql.core.javaSql.ResultSetInterface as RS

trait SpiHelpers extends ModelUtils {

  def noCollectionFilterEq(attr: String) = throw ModelError(
    s"Filtering by collection equality ($attr) not supported in updates."
  )
  def noIdsTwice() = throw ModelError(
    "Can't apply entity ids twice in update."
  )
  def noMixIdsFilterAttrs() = throw ModelError(
    "Can't mix using ids and filter elements"
  )

  protected def nestedArray2coalescedSet(a: Attr, rs: RS, isAttr: Boolean = true): Set[Any] = {
    a match {
      case _: AttrSetManID             => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Long])
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


  private def sql2set[T](isAttr: Boolean, rs: RS, j2s: Any => T): Set[T] = {
    if (isAttr)
      sqlNestedArrays2coalescedSet(rs, j2s)
    else
      sqlArrays2coalescedSet(rs, j2s)
  }

  private def sqlNestedArrays2coalescedSet[T](rs: RS, j2s: Any => T): Set[T] = {
    rs.next()
    val array = rs.getArray(1)
    if (rs.wasNull()) {
      Set.empty[T]
    } else {
      val resultSet = array.getResultSet
      var set       = Set.empty[T]
      while (resultSet.next()) {
        resultSet.getArray(2).getArray.asInstanceOf[Array[?]].foreach { value =>
          set += j2s(value)
        }
      }
      set
    }
  }

  private def sqlArrays2coalescedSet[T](rs: RS, j2s: Any => T): Set[T] = {
    var set = Set.empty[T]
    while (rs.next()) {
      rs.getArray(1).getArray.asInstanceOf[Array[?]].foreach { value =>
        set += j2s(value)
      }
    }
    set
  }

  protected def jsonArray2coalescedSet(a: Attr, rs: RS): Set[Any] = {
    rs.next()
    val json = rs.getString(1)
    a match {
      case _: AttrSetManID             => json.substring(1, json.length - 1).split(", ?").map(_.toLong).toSet
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
