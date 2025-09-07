package molecule.db.common.transaction.plan.phase4.binders

import java.sql.{PreparedStatement as PS, Types}
import molecule.core.dataModel.*
import molecule.db.common.transaction.ValueTransformers

object SeqBinders extends ValueTransformers {

  private inline def setArray(sqlTpe: String, values: Array[AnyRef]): (PS, Int) => Unit =
    (ps: PS, i: Int) => {
      if (values != null && values.nonEmpty) {
        val arr = ps.getConnection.createArrayOf(sqlTpe, values)
        ps.setArray(i, arr)
      } else {
        ps.setNull(i, Types.NULL)
      }
    }

  private inline def setBytesOrNull(bytes: Array[Byte]): (PS, Int) => Unit =
    (ps: PS, i: Int) => {
      if (bytes != null && bytes.nonEmpty) ps.setBytes(i, bytes)
      else ps.setNull(i, 0)
    }

  def forSeqMan(attr: AttrSeqMan, raw: Any): (PS, Int) => Unit = attr match {
    case _: AttrSeqManByte =>
      raw match {
        case ba: Array[Byte]        => setBytesOrNull(ba)
        case a: Array[_]            => setBytesOrNull(a.asInstanceOf[Array[Byte]])
        case s: Seq[_]              => setBytesOrNull(s.asInstanceOf[Seq[Byte]].toArray)
        case _                      => (ps: PS, i: Int) => ps.setNull(i, 0)
      }

    case _: AttrSeqManID             => setArray("BIGINT",   seq2arrayID(raw.asInstanceOf[Seq[Long]]))
    case _: AttrSeqManString         => setArray("VARCHAR",  seq2arrayString(raw.asInstanceOf[Seq[String]]))
    case _: AttrSeqManInt            => setArray("INTEGER",  seq2arrayInt(raw.asInstanceOf[Seq[Int]]))
    case _: AttrSeqManLong           => setArray("BIGINT",   seq2arrayLong(raw.asInstanceOf[Seq[Long]]))
    case _: AttrSeqManFloat          => setArray("REAL",     seq2arrayFloat(raw.asInstanceOf[Seq[Float]]))
    case _: AttrSeqManDouble         => setArray("DOUBLE",   seq2arrayDouble(raw.asInstanceOf[Seq[Double]]))
    case _: AttrSeqManBoolean        => setArray("BOOLEAN",  seq2arrayBoolean(raw.asInstanceOf[Seq[Boolean]]))
    case _: AttrSeqManBigInt         => setArray("DECIMAL",  seq2arrayBigInt(raw.asInstanceOf[Seq[BigInt]]))
    case _: AttrSeqManBigDecimal     => setArray("DECIMAL",  seq2arrayBigDecimal(raw.asInstanceOf[Seq[BigDecimal]]))
    case _: AttrSeqManDate           => setArray("BIGINT",   seq2arrayDate(raw.asInstanceOf[Seq[java.util.Date]]))
    case _: AttrSeqManDuration       => setArray("VARCHAR",  seq2arrayDuration(raw.asInstanceOf[Seq[java.time.Duration]]))
    case _: AttrSeqManInstant        => setArray("VARCHAR",  seq2arrayInstant(raw.asInstanceOf[Seq[java.time.Instant]]))
    case _: AttrSeqManLocalDate      => setArray("VARCHAR",  seq2arrayLocalDate(raw.asInstanceOf[Seq[java.time.LocalDate]]))
    case _: AttrSeqManLocalTime      => setArray("VARCHAR",  seq2arrayLocalTime(raw.asInstanceOf[Seq[java.time.LocalTime]]))
    case _: AttrSeqManLocalDateTime  => setArray("VARCHAR",  seq2arrayLocalDateTime(raw.asInstanceOf[Seq[java.time.LocalDateTime]]))
    case _: AttrSeqManOffsetTime     => setArray("VARCHAR",  seq2arrayOffsetTime(raw.asInstanceOf[Seq[java.time.OffsetTime]]))
    case _: AttrSeqManOffsetDateTime => setArray("VARCHAR",  seq2arrayOffsetDateTime(raw.asInstanceOf[Seq[java.time.OffsetDateTime]]))
    case _: AttrSeqManZonedDateTime  => setArray("VARCHAR",  seq2arrayZonedDateTime(raw.asInstanceOf[Seq[java.time.ZonedDateTime]]))
    case _: AttrSeqManUUID           => setArray("UUID",     seq2arrayUUID(raw.asInstanceOf[Seq[java.util.UUID]]))
    case _: AttrSeqManURI            => setArray("VARCHAR",  seq2arrayURI(raw.asInstanceOf[Seq[java.net.URI]]))
    case _: AttrSeqManShort          => setArray("SMALLINT", seq2arrayShort(raw.asInstanceOf[Seq[Short]]))
    case _: AttrSeqManChar           => setArray("VARCHAR",  seq2arrayChar(raw.asInstanceOf[Seq[Char]]))
  }

  def forSeqOpt(attr: AttrSeqOpt, raw: Any): (PS, Int) => Unit = attr match {
    case _: AttrSeqOptByte =>
      raw match {
        case Some(ba: Array[Byte])   => setBytesOrNull(ba)
        case Some(a: Array[_])       => setBytesOrNull(a.asInstanceOf[Array[Byte]])
        case Some(s: Seq[_])         => setBytesOrNull(s.asInstanceOf[Seq[Byte]].toArray)
        case None                    => (ps: PS, i: Int) => ps.setNull(i, 0)
        case a: Array[_]             => setBytesOrNull(a.asInstanceOf[Array[Byte]])
        case s: Seq[_]               => setBytesOrNull(s.asInstanceOf[Seq[Byte]].toArray)
        case _                       => (ps: PS, i: Int) => ps.setNull(i, 0)
      }

    case _: AttrSeqOptID             => raw match {
      case Some(s: Seq[_]) => setArray("BIGINT",   seq2arrayID(s.asInstanceOf[Seq[Long]]))
      case s: Seq[_]       => setArray("BIGINT",   seq2arrayID(s.asInstanceOf[Seq[Long]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptString         => raw match {
      case Some(s: Seq[_]) => setArray("VARCHAR",  seq2arrayString(s.asInstanceOf[Seq[String]]))
      case s: Seq[_]       => setArray("VARCHAR",  seq2arrayString(s.asInstanceOf[Seq[String]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptInt            => raw match {
      case Some(s: Seq[_]) => setArray("INTEGER",  seq2arrayInt(s.asInstanceOf[Seq[Int]]))
      case s: Seq[_]       => setArray("INTEGER",  seq2arrayInt(s.asInstanceOf[Seq[Int]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptLong           => raw match {
      case Some(s: Seq[_]) => setArray("BIGINT",   seq2arrayLong(s.asInstanceOf[Seq[Long]]))
      case s: Seq[_]       => setArray("BIGINT",   seq2arrayLong(s.asInstanceOf[Seq[Long]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptFloat          => raw match {
      case Some(s: Seq[_]) => setArray("REAL",     seq2arrayFloat(s.asInstanceOf[Seq[Float]]))
      case s: Seq[_]       => setArray("REAL",     seq2arrayFloat(s.asInstanceOf[Seq[Float]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptDouble         => raw match {
      case Some(s: Seq[_]) => setArray("DOUBLE",   seq2arrayDouble(s.asInstanceOf[Seq[Double]]))
      case s: Seq[_]       => setArray("DOUBLE",   seq2arrayDouble(s.asInstanceOf[Seq[Double]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptBoolean        => raw match {
      case Some(s: Seq[_]) => setArray("BOOLEAN",  seq2arrayBoolean(s.asInstanceOf[Seq[Boolean]]))
      case s: Seq[_]       => setArray("BOOLEAN",  seq2arrayBoolean(s.asInstanceOf[Seq[Boolean]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptBigInt         => raw match {
      case Some(s: Seq[_]) => setArray("DECIMAL",  seq2arrayBigInt(s.asInstanceOf[Seq[BigInt]]))
      case s: Seq[_]       => setArray("DECIMAL",  seq2arrayBigInt(s.asInstanceOf[Seq[BigInt]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptBigDecimal     => raw match {
      case Some(s: Seq[_]) => setArray("DECIMAL",  seq2arrayBigDecimal(s.asInstanceOf[Seq[BigDecimal]]))
      case s: Seq[_]       => setArray("DECIMAL",  seq2arrayBigDecimal(s.asInstanceOf[Seq[BigDecimal]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptDate           => raw match {
      case Some(s: Seq[_]) => setArray("BIGINT",   seq2arrayDate(s.asInstanceOf[Seq[java.util.Date]]))
      case s: Seq[_]       => setArray("BIGINT",   seq2arrayDate(s.asInstanceOf[Seq[java.util.Date]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptDuration       => raw match {
      case Some(s: Seq[_]) => setArray("VARCHAR",  seq2arrayDuration(s.asInstanceOf[Seq[java.time.Duration]]))
      case s: Seq[_]       => setArray("VARCHAR",  seq2arrayDuration(s.asInstanceOf[Seq[java.time.Duration]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptInstant        => raw match {
      case Some(s: Seq[_]) => setArray("VARCHAR",  seq2arrayInstant(s.asInstanceOf[Seq[java.time.Instant]]))
      case s: Seq[_]       => setArray("VARCHAR",  seq2arrayInstant(s.asInstanceOf[Seq[java.time.Instant]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptLocalDate      => raw match {
      case Some(s: Seq[_]) => setArray("VARCHAR",  seq2arrayLocalDate(s.asInstanceOf[Seq[java.time.LocalDate]]))
      case s: Seq[_]       => setArray("VARCHAR",  seq2arrayLocalDate(s.asInstanceOf[Seq[java.time.LocalDate]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptLocalTime      => raw match {
      case Some(s: Seq[_]) => setArray("VARCHAR",  seq2arrayLocalTime(s.asInstanceOf[Seq[java.time.LocalTime]]))
      case s: Seq[_]       => setArray("VARCHAR",  seq2arrayLocalTime(s.asInstanceOf[Seq[java.time.LocalTime]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptLocalDateTime  => raw match {
      case Some(s: Seq[_]) => setArray("VARCHAR",  seq2arrayLocalDateTime(s.asInstanceOf[Seq[java.time.LocalDateTime]]))
      case s: Seq[_]       => setArray("VARCHAR",  seq2arrayLocalDateTime(s.asInstanceOf[Seq[java.time.LocalDateTime]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptOffsetTime     => raw match {
      case Some(s: Seq[_]) => setArray("VARCHAR",  seq2arrayOffsetTime(s.asInstanceOf[Seq[java.time.OffsetTime]]))
      case s: Seq[_]       => setArray("VARCHAR",  seq2arrayOffsetTime(s.asInstanceOf[Seq[java.time.OffsetTime]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptOffsetDateTime => raw match {
      case Some(s: Seq[_]) => setArray("VARCHAR",  seq2arrayOffsetDateTime(s.asInstanceOf[Seq[java.time.OffsetDateTime]]))
      case s: Seq[_]       => setArray("VARCHAR",  seq2arrayOffsetDateTime(s.asInstanceOf[Seq[java.time.OffsetDateTime]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptZonedDateTime  => raw match {
      case Some(s: Seq[_]) => setArray("VARCHAR",  seq2arrayZonedDateTime(s.asInstanceOf[Seq[java.time.ZonedDateTime]]))
      case s: Seq[_]       => setArray("VARCHAR",  seq2arrayZonedDateTime(s.asInstanceOf[Seq[java.time.ZonedDateTime]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptUUID           => raw match {
      case Some(s: Seq[_]) => setArray("UUID",     seq2arrayUUID(s.asInstanceOf[Seq[java.util.UUID]]))
      case s: Seq[_]       => setArray("UUID",     seq2arrayUUID(s.asInstanceOf[Seq[java.util.UUID]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptURI            => raw match {
      case Some(s: Seq[_]) => setArray("VARCHAR",  seq2arrayURI(s.asInstanceOf[Seq[java.net.URI]]))
      case s: Seq[_]       => setArray("VARCHAR",  seq2arrayURI(s.asInstanceOf[Seq[java.net.URI]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptShort          => raw match {
      case Some(s: Seq[_]) => setArray("SMALLINT", seq2arrayShort(s.asInstanceOf[Seq[Short]]))
      case s: Seq[_]       => setArray("SMALLINT", seq2arrayShort(s.asInstanceOf[Seq[Short]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
    case _: AttrSeqOptChar           => raw match {
      case Some(s: Seq[_]) => setArray("VARCHAR",  seq2arrayChar(s.asInstanceOf[Seq[Char]]))
      case s: Seq[_]       => setArray("VARCHAR",  seq2arrayChar(s.asInstanceOf[Seq[Char]]))
      case None            => (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    }
  }
}