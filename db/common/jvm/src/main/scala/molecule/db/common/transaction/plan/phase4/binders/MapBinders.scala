package molecule.db.common.transaction.plan.phase4.binders

import java.sql.{PreparedStatement as PS, Types}
import molecule.core.dataModel.*
import molecule.db.common.transaction.ValueTransformers

object MapBinders extends ValueTransformers {

  private inline def setBytesOrNull(bytes: Array[Byte]): (PS, Int) => Unit =
    (ps: PS, i: Int) => {
      if (bytes != null && bytes.nonEmpty) ps.setBytes(i, bytes)
      else ps.setNull(i, Types.NULL)
    }

  private inline def jsonSetter[A](raw: Any, value2json: (StringBuffer, A) => StringBuffer): (PS, Int) => Unit = {
    val m = raw.asInstanceOf[Map[String, A]]
    if (m.nonEmpty) setBytesOrNull(map2jsonByteArray(m, value2json))
    else (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
  }

  private inline def jsonSetterOpt[A](raw: Any, value2json: (StringBuffer, A) => StringBuffer): (PS, Int) => Unit = raw match {
    case Some(m0: Map[_, _]) =>
      val m = m0.asInstanceOf[Map[String, A]]
      if (m.nonEmpty) setBytesOrNull(map2jsonByteArray(m, value2json))
      else (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
    case _ =>
      (ps: PS, i: Int) => ps.setNull(i, Types.NULL)
  }

  // Value encoders ------------------------------------------------------------

  private inline def v2jsonString(buf: StringBuffer, v: String): StringBuffer = quote(buf, v)
  private inline def v2jsonChar(buf: StringBuffer, v: Char): StringBuffer     = quote(buf, v.toString)
  private inline def v2jsonUUID(buf: StringBuffer, v: java.util.UUID): StringBuffer = quote(buf, v.toString)
  private inline def v2jsonURI(buf: StringBuffer, v: java.net.URI): StringBuffer    = quote(buf, v.toString)

  private inline def v2jsonInt(buf: StringBuffer, v: Int): StringBuffer           = { buf.append(v); buf }
  private inline def v2jsonLong(buf: StringBuffer, v: Long): StringBuffer         = { buf.append(v); buf }
  private inline def v2jsonFloat(buf: StringBuffer, v: Float): StringBuffer       = { buf.append(v); buf }
  private inline def v2jsonDouble(buf: StringBuffer, v: Double): StringBuffer     = { buf.append(v); buf }
  private inline def v2jsonBoolean(buf: StringBuffer, v: Boolean): StringBuffer   = { buf.append(v); buf }
  private inline def v2jsonBigInt(buf: StringBuffer, v: BigInt): StringBuffer     = { buf.append(v.toString); buf }
  private inline def v2jsonBigDecimal(buf: StringBuffer, v: BigDecimal): StringBuffer = { buf.append(v.toString); buf }
  private inline def v2jsonByte(buf: StringBuffer, v: Byte): StringBuffer         = { buf.append(v); buf }
  private inline def v2jsonShort(buf: StringBuffer, v: Short): StringBuffer       = { buf.append(v); buf }

  // Date/time: serialize to string (quoted), except Date as epoch millis (numeric)
  private inline def v2jsonDate(buf: StringBuffer, v: java.util.Date): StringBuffer = { buf.append(v.getTime); buf }
  private inline def v2jsonDuration(buf: StringBuffer, v: java.time.Duration): StringBuffer = quote(buf, v.toString)
  private inline def v2jsonInstant(buf: StringBuffer, v: java.time.Instant): StringBuffer   = quote(buf, v.toString)
  private inline def v2jsonLocalDate(buf: StringBuffer, v: java.time.LocalDate): StringBuffer = quote(buf, v.toString)
  private inline def v2jsonLocalTime(buf: StringBuffer, v: java.time.LocalTime): StringBuffer = quote(buf, v.toString)
  private inline def v2jsonLocalDateTime(buf: StringBuffer, v: java.time.LocalDateTime): StringBuffer = quote(buf, v.toString)
  private inline def v2jsonOffsetTime(buf: StringBuffer, v: java.time.OffsetTime): StringBuffer = quote(buf, v.toString)
  private inline def v2jsonOffsetDateTime(buf: StringBuffer, v: java.time.OffsetDateTime): StringBuffer = quote(buf, v.toString)
  private inline def v2jsonZonedDateTime(buf: StringBuffer, v: java.time.ZonedDateTime): StringBuffer = quote(buf, v.toString)

  // Public API ----------------------------------------------------------------

  def forMapMan(attr: AttrMapMan, raw: Any): (PS, Int) => Unit = attr match {
    case _: AttrMapManID             => jsonSetter[Long](raw, v2jsonLong)
    case _: AttrMapManString         => jsonSetter[String](raw, v2jsonString)
    case _: AttrMapManInt            => jsonSetter[Int](raw, v2jsonInt)
    case _: AttrMapManLong           => jsonSetter[Long](raw, v2jsonLong)
    case _: AttrMapManFloat          => jsonSetter[Float](raw, v2jsonFloat)
    case _: AttrMapManDouble         => jsonSetter[Double](raw, v2jsonDouble)
    case _: AttrMapManBoolean        => jsonSetter[Boolean](raw, v2jsonBoolean)
    case _: AttrMapManBigInt         => jsonSetter[BigInt](raw, v2jsonBigInt)
    case _: AttrMapManBigDecimal     => jsonSetter[BigDecimal](raw, v2jsonBigDecimal)
    case _: AttrMapManDate           => jsonSetter[java.util.Date](raw, v2jsonDate)
    case _: AttrMapManDuration       => jsonSetter[java.time.Duration](raw, v2jsonDuration)
    case _: AttrMapManInstant        => jsonSetter[java.time.Instant](raw, v2jsonInstant)
    case _: AttrMapManLocalDate      => jsonSetter[java.time.LocalDate](raw, v2jsonLocalDate)
    case _: AttrMapManLocalTime      => jsonSetter[java.time.LocalTime](raw, v2jsonLocalTime)
    case _: AttrMapManLocalDateTime  => jsonSetter[java.time.LocalDateTime](raw, v2jsonLocalDateTime)
    case _: AttrMapManOffsetTime     => jsonSetter[java.time.OffsetTime](raw, v2jsonOffsetTime)
    case _: AttrMapManOffsetDateTime => jsonSetter[java.time.OffsetDateTime](raw, v2jsonOffsetDateTime)
    case _: AttrMapManZonedDateTime  => jsonSetter[java.time.ZonedDateTime](raw, v2jsonZonedDateTime)
    case _: AttrMapManUUID           => jsonSetter[java.util.UUID](raw, v2jsonUUID)
    case _: AttrMapManURI            => jsonSetter[java.net.URI](raw, v2jsonURI)
    case _: AttrMapManByte           => jsonSetter[Byte](raw, v2jsonByte)
    case _: AttrMapManShort          => jsonSetter[Short](raw, v2jsonShort)
    case _: AttrMapManChar           => jsonSetter[Char](raw, v2jsonChar)
  }

  def forMapOpt(attr: AttrMapOpt, raw: Any): (PS, Int) => Unit = attr match {
    case _: AttrMapOptID             => jsonSetterOpt[Long](raw, v2jsonLong)
    case _: AttrMapOptString         => jsonSetterOpt[String](raw, v2jsonString)
    case _: AttrMapOptInt            => jsonSetterOpt[Int](raw, v2jsonInt)
    case _: AttrMapOptLong           => jsonSetterOpt[Long](raw, v2jsonLong)
    case _: AttrMapOptFloat          => jsonSetterOpt[Float](raw, v2jsonFloat)
    case _: AttrMapOptDouble         => jsonSetterOpt[Double](raw, v2jsonDouble)
    case _: AttrMapOptBoolean        => jsonSetterOpt[Boolean](raw, v2jsonBoolean)
    case _: AttrMapOptBigInt         => jsonSetterOpt[BigInt](raw, v2jsonBigInt)
    case _: AttrMapOptBigDecimal     => jsonSetterOpt[BigDecimal](raw, v2jsonBigDecimal)
    case _: AttrMapOptDate           => jsonSetterOpt[java.util.Date](raw, v2jsonDate)
    case _: AttrMapOptDuration       => jsonSetterOpt[java.time.Duration](raw, v2jsonDuration)
    case _: AttrMapOptInstant        => jsonSetterOpt[java.time.Instant](raw, v2jsonInstant)
    case _: AttrMapOptLocalDate      => jsonSetterOpt[java.time.LocalDate](raw, v2jsonLocalDate)
    case _: AttrMapOptLocalTime      => jsonSetterOpt[java.time.LocalTime](raw, v2jsonLocalTime)
    case _: AttrMapOptLocalDateTime  => jsonSetterOpt[java.time.LocalDateTime](raw, v2jsonLocalDateTime)
    case _: AttrMapOptOffsetTime     => jsonSetterOpt[java.time.OffsetTime](raw, v2jsonOffsetTime)
    case _: AttrMapOptOffsetDateTime => jsonSetterOpt[java.time.OffsetDateTime](raw, v2jsonOffsetDateTime)
    case _: AttrMapOptZonedDateTime  => jsonSetterOpt[java.time.ZonedDateTime](raw, v2jsonZonedDateTime)
    case _: AttrMapOptUUID           => jsonSetterOpt[java.util.UUID](raw, v2jsonUUID)
    case _: AttrMapOptURI            => jsonSetterOpt[java.net.URI](raw, v2jsonURI)
    case _: AttrMapOptByte           => jsonSetterOpt[Byte](raw, v2jsonByte)
    case _: AttrMapOptShort          => jsonSetterOpt[Short](raw, v2jsonShort)
    case _: AttrMapOptChar           => jsonSetterOpt[Char](raw, v2jsonChar)
  }
}
