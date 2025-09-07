package molecule.db.common.transaction.plan.phase4.binders

import java.sql.{PreparedStatement as PS, Types}
import molecule.core.dataModel.*
import molecule.db.common.transaction.ValueTransformers

object SetBinders extends ValueTransformers {

  private inline def setArray(sqlTpe: String, values: Array[AnyRef]): (PS, Int) => Unit =
    (ps: PS, i: Int) => {
      if (values != null && values.nonEmpty) {
        val arr = ps.getConnection.createArrayOf(sqlTpe, values)
        ps.setArray(i, arr)
      } else {
        ps.setNull(i, Types.NULL)
      }
    }

  def forSetMan(attr: AttrSetMan, raw: Any): (PS, Int) => Unit = attr match {
    case _: AttrSetManID             => setArray("BIGINT",   set2arrayID(raw.asInstanceOf[Set[Long]]))
    case _: AttrSetManString         => setArray("VARCHAR",  set2arrayString(raw.asInstanceOf[Set[String]]))
    case _: AttrSetManInt            => setArray("INTEGER",  set2arrayInt(raw.asInstanceOf[Set[Int]]))
    case _: AttrSetManLong           => setArray("BIGINT",   set2arrayLong(raw.asInstanceOf[Set[Long]]))
    case _: AttrSetManFloat          => setArray("REAL",     set2arrayFloat(raw.asInstanceOf[Set[Float]]))
    case _: AttrSetManDouble         => setArray("DOUBLE",   set2arrayDouble(raw.asInstanceOf[Set[Double]]))
    case _: AttrSetManBoolean        => setArray("BOOLEAN",  set2arrayBoolean(raw.asInstanceOf[Set[Boolean]]))
    case _: AttrSetManBigInt         => setArray("DECIMAL",  set2arrayBigInt(raw.asInstanceOf[Set[BigInt]]))
    case _: AttrSetManBigDecimal     => setArray("DECIMAL",  set2arrayBigDecimal(raw.asInstanceOf[Set[BigDecimal]]))
    case _: AttrSetManDate           => setArray("BIGINT",   set2arrayDate(raw.asInstanceOf[Set[java.util.Date]]))
    case _: AttrSetManDuration       => setArray("VARCHAR",  set2arrayDuration(raw.asInstanceOf[Set[java.time.Duration]]))
    case _: AttrSetManInstant        => setArray("VARCHAR",  set2arrayInstant(raw.asInstanceOf[Set[java.time.Instant]]))
    case _: AttrSetManLocalDate      => setArray("VARCHAR",  set2arrayLocalDate(raw.asInstanceOf[Set[java.time.LocalDate]]))
    case _: AttrSetManLocalTime      => setArray("VARCHAR",  set2arrayLocalTime(raw.asInstanceOf[Set[java.time.LocalTime]]))
    case _: AttrSetManLocalDateTime  => setArray("VARCHAR",  set2arrayLocalDateTime(raw.asInstanceOf[Set[java.time.LocalDateTime]]))
    case _: AttrSetManOffsetTime     => setArray("VARCHAR",  set2arrayOffsetTime(raw.asInstanceOf[Set[java.time.OffsetTime]]))
    case _: AttrSetManOffsetDateTime => setArray("VARCHAR",  set2arrayOffsetDateTime(raw.asInstanceOf[Set[java.time.OffsetDateTime]]))
    case _: AttrSetManZonedDateTime  => setArray("VARCHAR",  set2arrayZonedDateTime(raw.asInstanceOf[Set[java.time.ZonedDateTime]]))
    case _: AttrSetManUUID           => setArray("UUID",     set2arrayUUID(raw.asInstanceOf[Set[java.util.UUID]]))
    case _: AttrSetManURI            => setArray("VARCHAR",  set2arrayURI(raw.asInstanceOf[Set[java.net.URI]]))
    case _: AttrSetManByte           => setArray("TINYINT",  set2arrayByte(raw.asInstanceOf[Set[Byte]]))
    case _: AttrSetManShort          => setArray("SMALLINT", set2arrayShort(raw.asInstanceOf[Set[Short]]))
    case _: AttrSetManChar           => setArray("VARCHAR",  set2arrayChar(raw.asInstanceOf[Set[Char]]))
  }

  def forSetOpt(attr: AttrSetOpt, raw: Any): (PS, Int) => Unit = attr match {
    case _: AttrSetOptID             => raw match {
      case Some(s: Set[_]) => setArray("BIGINT",   set2arrayID(s.asInstanceOf[Set[Long]]))
      case s: Set[_]       => setArray("BIGINT",   set2arrayID(s.asInstanceOf[Set[Long]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptString         => raw match {
      case Some(s: Set[_]) => setArray("VARCHAR",  set2arrayString(s.asInstanceOf[Set[String]]))
      case s: Set[_]       => setArray("VARCHAR",  set2arrayString(s.asInstanceOf[Set[String]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptInt            => raw match {
      case Some(s: Set[_]) => setArray("INTEGER",  set2arrayInt(s.asInstanceOf[Set[Int]]))
      case s: Set[_]       => setArray("INTEGER",  set2arrayInt(s.asInstanceOf[Set[Int]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptLong           => raw match {
      case Some(s: Set[_]) => setArray("BIGINT",   set2arrayLong(s.asInstanceOf[Set[Long]]))
      case s: Set[_]       => setArray("BIGINT",   set2arrayLong(s.asInstanceOf[Set[Long]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptFloat          => raw match {
      case Some(s: Set[_]) => setArray("REAL",     set2arrayFloat(s.asInstanceOf[Set[Float]]))
      case s: Set[_]       => setArray("REAL",     set2arrayFloat(s.asInstanceOf[Set[Float]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptDouble         => raw match {
      case Some(s: Set[_]) => setArray("DOUBLE",   set2arrayDouble(s.asInstanceOf[Set[Double]]))
      case s: Set[_]       => setArray("DOUBLE",   set2arrayDouble(s.asInstanceOf[Set[Double]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptBoolean        => raw match {
      case Some(s: Set[_]) => setArray("BOOLEAN",  set2arrayBoolean(s.asInstanceOf[Set[Boolean]]))
      case s: Set[_]       => setArray("BOOLEAN",  set2arrayBoolean(s.asInstanceOf[Set[Boolean]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptBigInt         => raw match {
      case Some(s: Set[_]) => setArray("DECIMAL",  set2arrayBigInt(s.asInstanceOf[Set[BigInt]]))
      case s: Set[_]       => setArray("DECIMAL",  set2arrayBigInt(s.asInstanceOf[Set[BigInt]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptBigDecimal     => raw match {
      case Some(s: Set[_]) => setArray("DECIMAL",  set2arrayBigDecimal(s.asInstanceOf[Set[BigDecimal]]))
      case s: Set[_]       => setArray("DECIMAL",  set2arrayBigDecimal(s.asInstanceOf[Set[BigDecimal]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptDate           => raw match {
      case Some(s: Set[_]) => setArray("BIGINT",   set2arrayDate(s.asInstanceOf[Set[java.util.Date]]))
      case s: Set[_]       => setArray("BIGINT",   set2arrayDate(s.asInstanceOf[Set[java.util.Date]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptDuration       => raw match {
      case Some(s: Set[_]) => setArray("VARCHAR",  set2arrayDuration(s.asInstanceOf[Set[java.time.Duration]]))
      case s: Set[_]       => setArray("VARCHAR",  set2arrayDuration(s.asInstanceOf[Set[java.time.Duration]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptInstant        => raw match {
      case Some(s: Set[_]) => setArray("VARCHAR",  set2arrayInstant(s.asInstanceOf[Set[java.time.Instant]]))
      case s: Set[_]       => setArray("VARCHAR",  set2arrayInstant(s.asInstanceOf[Set[java.time.Instant]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptLocalDate      => raw match {
      case Some(s: Set[_]) => setArray("VARCHAR",  set2arrayLocalDate(s.asInstanceOf[Set[java.time.LocalDate]]))
      case s: Set[_]       => setArray("VARCHAR",  set2arrayLocalDate(s.asInstanceOf[Set[java.time.LocalDate]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptLocalTime      => raw match {
      case Some(s: Set[_]) => setArray("VARCHAR",  set2arrayLocalTime(s.asInstanceOf[Set[java.time.LocalTime]]))
      case s: Set[_]       => setArray("VARCHAR",  set2arrayLocalTime(s.asInstanceOf[Set[java.time.LocalTime]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptLocalDateTime  => raw match {
      case Some(s: Set[_]) => setArray("VARCHAR",  set2arrayLocalDateTime(s.asInstanceOf[Set[java.time.LocalDateTime]]))
      case s: Set[_]       => setArray("VARCHAR",  set2arrayLocalDateTime(s.asInstanceOf[Set[java.time.LocalDateTime]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptOffsetTime     => raw match {
      case Some(s: Set[_]) => setArray("VARCHAR",  set2arrayOffsetTime(s.asInstanceOf[Set[java.time.OffsetTime]]))
      case s: Set[_]       => setArray("VARCHAR",  set2arrayOffsetTime(s.asInstanceOf[Set[java.time.OffsetTime]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptOffsetDateTime => raw match {
      case Some(s: Set[_]) => setArray("VARCHAR",  set2arrayOffsetDateTime(s.asInstanceOf[Set[java.time.OffsetDateTime]]))
      case s: Set[_]       => setArray("VARCHAR",  set2arrayOffsetDateTime(s.asInstanceOf[Set[java.time.OffsetDateTime]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptZonedDateTime  => raw match {
      case Some(s: Set[_]) => setArray("VARCHAR",  set2arrayZonedDateTime(s.asInstanceOf[Set[java.time.ZonedDateTime]]))
      case s: Set[_]       => setArray("VARCHAR",  set2arrayZonedDateTime(s.asInstanceOf[Set[java.time.ZonedDateTime]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptUUID           => raw match {
      case Some(s: Set[_]) => setArray("UUID",     set2arrayUUID(s.asInstanceOf[Set[java.util.UUID]]))
      case s: Set[_]       => setArray("UUID",     set2arrayUUID(s.asInstanceOf[Set[java.util.UUID]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptURI            => raw match {
      case Some(s: Set[_]) => setArray("VARCHAR",  set2arrayURI(s.asInstanceOf[Set[java.net.URI]]))
      case s: Set[_]       => setArray("VARCHAR",  set2arrayURI(s.asInstanceOf[Set[java.net.URI]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptByte           => raw match {
      case Some(s: Set[_]) => setArray("TINYINT",  set2arrayByte(s.asInstanceOf[Set[Byte]]))
      case s: Set[_]       => setArray("TINYINT",  set2arrayByte(s.asInstanceOf[Set[Byte]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptShort          => raw match {
      case Some(s: Set[_]) => setArray("SMALLINT", set2arrayShort(s.asInstanceOf[Set[Short]]))
      case s: Set[_]       => setArray("SMALLINT", set2arrayShort(s.asInstanceOf[Set[Short]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSetOptChar           => raw match {
      case Some(s: Set[_]) => setArray("VARCHAR",  set2arrayChar(s.asInstanceOf[Set[Char]]))
      case s: Set[_]       => setArray("VARCHAR",  set2arrayChar(s.asInstanceOf[Set[Char]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
  }
}
