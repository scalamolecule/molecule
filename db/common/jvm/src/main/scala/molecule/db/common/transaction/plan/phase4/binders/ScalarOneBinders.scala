package molecule.db.common.transaction.plan.phase4.binders

import java.sql.{PreparedStatement, Types}
import molecule.core.dataModel.*
import molecule.db.common.transaction.ValueTransformers

object ScalarOneBinders extends ValueTransformers {

  def forOneMan(attr: AttrOneMan, raw: Any): (PreparedStatement, Int) => Unit = attr match {
    case _: AttrOneManID             => transformID(raw.asInstanceOf[Long])
    case _: AttrOneManString         => transformString(raw.asInstanceOf[String])
    case _: AttrOneManInt            => transformInt(raw.asInstanceOf[Int])
    case _: AttrOneManLong           => transformLong(raw.asInstanceOf[Long])
    case _: AttrOneManFloat          => transformFloat(raw.asInstanceOf[Float])
    case _: AttrOneManDouble         => transformDouble(raw.asInstanceOf[Double])
    case _: AttrOneManBoolean        => transformBoolean(raw.asInstanceOf[Boolean])
    case _: AttrOneManBigInt         => transformBigInt(raw.asInstanceOf[BigInt])
    case _: AttrOneManBigDecimal     => transformBigDecimal(raw.asInstanceOf[BigDecimal])
    case _: AttrOneManDate           => transformDate(raw.asInstanceOf[java.util.Date])
    case _: AttrOneManDuration       => transformDuration(raw.asInstanceOf[java.time.Duration])
    case _: AttrOneManInstant        => transformInstant(raw.asInstanceOf[java.time.Instant])
    case _: AttrOneManLocalDate      => transformLocalDate(raw.asInstanceOf[java.time.LocalDate])
    case _: AttrOneManLocalTime      => transformLocalTime(raw.asInstanceOf[java.time.LocalTime])
    case _: AttrOneManLocalDateTime  => transformLocalDateTime(raw.asInstanceOf[java.time.LocalDateTime])
    case _: AttrOneManOffsetTime     => transformOffsetTime(raw.asInstanceOf[java.time.OffsetTime])
    case _: AttrOneManOffsetDateTime => transformOffsetDateTime(raw.asInstanceOf[java.time.OffsetDateTime])
    case _: AttrOneManZonedDateTime  => transformZonedDateTime(raw.asInstanceOf[java.time.ZonedDateTime])
    case _: AttrOneManUUID           => transformUUID(raw.asInstanceOf[java.util.UUID])
    case _: AttrOneManURI            => transformURI(raw.asInstanceOf[java.net.URI])
    case _: AttrOneManByte           => transformByte(raw.asInstanceOf[Byte])
    case _: AttrOneManShort          => transformShort(raw.asInstanceOf[Short])
    case _: AttrOneManChar           => transformChar(raw.asInstanceOf[Char])
  }

  // AttrOneOpt: set NULL when None
  def forOneOpt(attr: AttrOneOpt, raw: Any): (PreparedStatement, Int) => Unit = attr match {
    case _: AttrOneOptID             => raw match {
      case Some(v: Long) => transformID(v)
      case None          => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptString         => raw match {
      case Some(v: String) => transformString(v)
      case None            => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptInt            => raw match {
      case Some(v: Int) => transformInt(v)
      case None         => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptLong           => raw match {
      case Some(v: Long) => transformLong(v)
      case None          => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptFloat          => raw match {
      case Some(v: Float) => transformFloat(v)
      case None           => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptDouble         => raw match {
      case Some(v: Double) => transformDouble(v)
      case None            => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptBoolean        => raw match {
      case Some(v: Boolean) => transformBoolean(v)
      case None             => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptBigInt         => raw match {
      case Some(v: BigInt) => transformBigInt(v)
      case None            => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptBigDecimal     => raw match {
      case Some(v: BigDecimal) => transformBigDecimal(v)
      case None                => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptDate           => raw match {
      case Some(v: java.util.Date) => transformDate(v)
      case None                    => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptDuration       => raw match {
      case Some(v: java.time.Duration) => transformDuration(v)
      case None                        => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptInstant        => raw match {
      case Some(v: java.time.Instant) => transformInstant(v)
      case None                       => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptLocalDate      => raw match {
      case Some(v: java.time.LocalDate) => transformLocalDate(v)
      case None                         => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptLocalTime      => raw match {
      case Some(v: java.time.LocalTime) => transformLocalTime(v)
      case None                         => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptLocalDateTime  => raw match {
      case Some(v: java.time.LocalDateTime) => transformLocalDateTime(v)
      case None                             => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptOffsetTime     => raw match {
      case Some(v: java.time.OffsetTime) => transformOffsetTime(v)
      case None                          => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptOffsetDateTime => raw match {
      case Some(v: java.time.OffsetDateTime) => transformOffsetDateTime(v)
      case None                              => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptZonedDateTime  => raw match {
      case Some(v: java.time.ZonedDateTime) => transformZonedDateTime(v)
      case None                             => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptUUID           => raw match {
      case Some(v: java.util.UUID) => transformUUID(v)
      case None                    => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptURI            => raw match {
      case Some(v: java.net.URI) => transformURI(v)
      case None                  => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptByte           => raw match {
      case Some(v: Byte) => transformByte(v)
      case None          => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptShort          => raw match {
      case Some(v: Short) => transformShort(v)
      case None           => (ps, i) => ps.setNull(i, Types.NULL)
    }
    case _: AttrOneOptChar           => raw match {
      case Some(v: Char) => transformChar(v)
      case None          => (ps, i) => ps.setNull(i, Types.NULL)
    }
  }
}
