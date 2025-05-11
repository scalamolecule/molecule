package molecule.db.sql.core.transaction

import java.net.URI
import java.sql.PreparedStatement as PS
import java.time.*
import java.util.{Date, UUID}
import molecule.db.core.transaction.ops.BaseOps

trait SqlBaseOps { self: BaseOps =>

  override protected lazy val transformID             = (v: Long) => (ps: PS, n: Int) => ps.setLong(n, v)
  override protected lazy val transformString         = (v: String) => (ps: PS, n: Int) => ps.setString(n, v)
  override protected lazy val transformInt            = (v: Int) => (ps: PS, n: Int) => ps.setInt(n, v)
  override protected lazy val transformLong           = (v: Long) => (ps: PS, n: Int) => ps.setLong(n, v)
  override protected lazy val transformFloat          = (v: Float) => (ps: PS, n: Int) => ps.setFloat(n, v)
  override protected lazy val transformDouble         = (v: Double) => (ps: PS, n: Int) => ps.setDouble(n, v)
  override protected lazy val transformBoolean        = (v: Boolean) => (ps: PS, n: Int) => ps.setBoolean(n, v)
  override protected lazy val transformBigInt         = (v: BigInt) => (ps: PS, n: Int) => ps.setBigDecimal(n, BigDecimal(v).bigDecimal)
  override protected lazy val transformBigDecimal     = (v: BigDecimal) => (ps: PS, n: Int) => ps.setBigDecimal(n, v.bigDecimal)
  override protected lazy val transformDate           = (v: Date) => (ps: PS, n: Int) => ps.setLong(n, v.getTime)
  override protected lazy val transformDuration       = (v: Duration) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val transformInstant        = (v: Instant) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val transformLocalDate      = (v: LocalDate) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val transformLocalTime      = (v: LocalTime) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val transformLocalDateTime  = (v: LocalDateTime) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val transformOffsetTime     = (v: OffsetTime) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val transformOffsetDateTime = (v: OffsetDateTime) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val transformZonedDateTime  = (v: ZonedDateTime) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val transformUUID           = (v: UUID) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val transformURI            = (v: URI) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val transformByte           = (v: Byte) => (ps: PS, n: Int) => ps.setByte(n, v)
  override protected lazy val transformShort          = (v: Short) => (ps: PS, n: Int) => ps.setShort(n, v)
  override protected lazy val transformChar           = (v: Char) => (ps: PS, n: Int) => ps.setString(n, v.toString)
}
