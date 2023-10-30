package molecule.core.transaction.ops

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.util.BaseHelpers
import molecule.core.transformation.JsonBase

trait BaseOps extends JsonBase with BaseHelpers {

  protected lazy val transformID            : String => Any         = identity
  protected lazy val transformString        : String => Any         = identity
  protected lazy val transformInt           : Int => Any            = identity
  protected lazy val transformLong          : Long => Any           = identity
  protected lazy val transformFloat         : Float => Any          = identity
  protected lazy val transformDouble        : Double => Any         = identity
  protected lazy val transformBoolean       : Boolean => Any        = identity
  protected lazy val transformBigInt        : BigInt => Any         = identity
  protected lazy val transformBigDecimal    : BigDecimal => Any     = identity
  protected lazy val transformDate          : Date => Any           = identity
  protected lazy val transformDuration      : Duration => Any       = identity
  protected lazy val transformInstant       : Instant => Any        = identity
  protected lazy val transformLocalDate     : LocalDate => Any      = identity
  protected lazy val transformLocalTime     : LocalTime => Any      = identity
  protected lazy val transformLocalDateTime : LocalDateTime => Any  = identity
  protected lazy val transformOffsetTime    : OffsetTime => Any     = identity
  protected lazy val transformOffsetDateTime: OffsetDateTime => Any = identity
  protected lazy val transformZonedDateTime : ZonedDateTime => Any  = identity
  protected lazy val transformUUID          : UUID => Any           = identity
  protected lazy val transformURI           : URI => Any            = identity
  protected lazy val transformByte          : Byte => Any           = identity
  protected lazy val transformShort         : Short => Any          = identity
  protected lazy val transformChar          : Char => Any           = identity

  protected lazy val handleID            : Any => Any = identity
  protected lazy val handleString        : Any => Any = identity
  protected lazy val handleInt           : Any => Any = identity
  protected lazy val handleLong          : Any => Any = identity
  protected lazy val handleFloat         : Any => Any = identity
  protected lazy val handleDouble        : Any => Any = identity
  protected lazy val handleBoolean       : Any => Any = identity
  protected lazy val handleBigInt        : Any => Any = identity
  protected lazy val handleBigDecimal    : Any => Any = identity
  protected lazy val handleDate          : Any => Any = identity
  protected lazy val handleDuration      : Any => Any = identity
  protected lazy val handleInstant       : Any => Any = identity
  protected lazy val handleLocalDate     : Any => Any = identity
  protected lazy val handleLocalTime     : Any => Any = identity
  protected lazy val handleLocalDateTime : Any => Any = identity
  protected lazy val handleOffsetTime    : Any => Any = identity
  protected lazy val handleOffsetDateTime: Any => Any = identity
  protected lazy val handleZonedDateTime : Any => Any = identity
  protected lazy val handleUUID          : Any => Any = identity
  protected lazy val handleURI           : Any => Any = identity
  protected lazy val handleByte          : Any => Any = identity
  protected lazy val handleShort         : Any => Any = identity
  protected lazy val handleChar          : Any => Any = identity

  protected lazy val set2arrayID            : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayString        : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayInt           : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayLong          : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayFloat         : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayDouble        : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayBoolean       : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayBigInt        : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[BigInt]].map(v => BigDecimal(v).bigDecimal.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayBigDecimal    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[BigDecimal]].map(v => v.bigDecimal.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayDate          : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[Date]].map(_.getTime.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayDuration      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[Duration]].map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayInstant       : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[Instant]].map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayLocalDate     : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[LocalDate]].map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayLocalTime     : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[LocalTime]].map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayLocalDateTime : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[LocalDateTime]].map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayOffsetTime    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[OffsetTime]].map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayOffsetDateTime: Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[OffsetDateTime]].map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayZonedDateTime : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[ZonedDateTime]].map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayUUID          : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayURI           : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayByte          : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayShort         : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayChar          : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray

  protected lazy val extsID             = List("", "AnyRef")
  protected lazy val extsString         = List("", "AnyRef")
  protected lazy val extsInt            = List("", "AnyRef")
  protected lazy val extsLong           = List("", "AnyRef")
  protected lazy val extsFloat          = List("", "AnyRef")
  protected lazy val extsDouble         = List("", "AnyRef")
  protected lazy val extsBoolean        = List("", "AnyRef")
  protected lazy val extsBigInt         = List("", "AnyRef")
  protected lazy val extsBigDecimal     = List("", "AnyRef")
  protected lazy val extsDate           = List("", "AnyRef")
  protected lazy val extsDuration       = List("", "AnyRef")
  protected lazy val extsInstant        = List("", "AnyRef")
  protected lazy val extsLocalDate      = List("", "AnyRef")
  protected lazy val extsLocalTime      = List("", "AnyRef")
  protected lazy val extsLocalDateTime  = List("", "AnyRef")
  protected lazy val extsOffsetTime     = List("", "AnyRef")
  protected lazy val extsOffsetDateTime = List("", "AnyRef")
  protected lazy val extsZonedDateTime  = List("", "AnyRef")
  protected lazy val extsUUID           = List("", "AnyRef")
  protected lazy val extsURI            = List("", "AnyRef")
  protected lazy val extsByte           = List("", "AnyRef")
  protected lazy val extsShort          = List("", "AnyRef")
  protected lazy val extsChar           = List("", "AnyRef")

  protected lazy val value2jsonID            : (StringBuffer, String) => StringBuffer         = (buf: StringBuffer, v: String) => quote(buf, v)
  protected lazy val value2jsonString        : (StringBuffer, String) => StringBuffer         = (buf: StringBuffer, v: String) => quote(buf, v)
  protected lazy val value2jsonInt           : (StringBuffer, Int) => StringBuffer            = (buf: StringBuffer, v: Int) => buf.append(v)
  protected lazy val value2jsonLong          : (StringBuffer, Long) => StringBuffer           = (buf: StringBuffer, v: Long) => buf.append(v)
  protected lazy val value2jsonFloat         : (StringBuffer, Float) => StringBuffer          = (buf: StringBuffer, v: Float) => buf.append(v)
  protected lazy val value2jsonDouble        : (StringBuffer, Double) => StringBuffer         = (buf: StringBuffer, v: Double) => buf.append(v)
  protected lazy val value2jsonBoolean       : (StringBuffer, Boolean) => StringBuffer        = (buf: StringBuffer, v: Boolean) => buf.append(if (v) "1" else "0")
  protected lazy val value2jsonBigInt        : (StringBuffer, BigInt) => StringBuffer         = (buf: StringBuffer, v: BigInt) => buf.append(v)
  protected lazy val value2jsonBigDecimal    : (StringBuffer, BigDecimal) => StringBuffer     = (buf: StringBuffer, v: BigDecimal) => buf.append(v)
  protected lazy val value2jsonDate          : (StringBuffer, Date) => StringBuffer           = (buf: StringBuffer, v: Date) => buf.append(v.getTime)
  protected lazy val value2jsonDuration      : (StringBuffer, Duration) => StringBuffer       = (buf: StringBuffer, v: Duration) => quote(buf, v.toString)
  protected lazy val value2jsonInstant       : (StringBuffer, Instant) => StringBuffer        = (buf: StringBuffer, v: Instant) => quote(buf, v.toString)
  protected lazy val value2jsonLocalDate     : (StringBuffer, LocalDate) => StringBuffer      = (buf: StringBuffer, v: LocalDate) => quote(buf, v.toString)
  protected lazy val value2jsonLocalTime     : (StringBuffer, LocalTime) => StringBuffer      = (buf: StringBuffer, v: LocalTime) => quote(buf, v.toString)
  protected lazy val value2jsonLocalDateTime : (StringBuffer, LocalDateTime) => StringBuffer  = (buf: StringBuffer, v: LocalDateTime) => quote(buf, v.toString)
  protected lazy val value2jsonOffsetTime    : (StringBuffer, OffsetTime) => StringBuffer     = (buf: StringBuffer, v: OffsetTime) => quote(buf, v.toString)
  protected lazy val value2jsonOffsetDateTime: (StringBuffer, OffsetDateTime) => StringBuffer = (buf: StringBuffer, v: OffsetDateTime) => quote(buf, v.toString)
  protected lazy val value2jsonZonedDateTime : (StringBuffer, ZonedDateTime) => StringBuffer  = (buf: StringBuffer, v: ZonedDateTime) => quote(buf, v.toString)
  protected lazy val value2jsonUUID          : (StringBuffer, UUID) => StringBuffer           = (buf: StringBuffer, v: UUID) => quote(buf, v.toString)
  protected lazy val value2jsonURI           : (StringBuffer, URI) => StringBuffer            = (buf: StringBuffer, v: URI) => quote(buf, v.toString)
  protected lazy val value2jsonByte          : (StringBuffer, Byte) => StringBuffer           = (buf: StringBuffer, v: Byte) => buf.append(v)
  protected lazy val value2jsonShort         : (StringBuffer, Short) => StringBuffer          = (buf: StringBuffer, v: Short) => buf.append(v)
  protected lazy val value2jsonChar          : (StringBuffer, Char) => StringBuffer           = (buf: StringBuffer, v: Char) => quote(buf, v.toString)


  protected lazy val one2jsonID            : String => String         = (v: String) => "\"" + escStr(v) + "\""
  protected lazy val one2jsonString        : String => String         = (v: String) => "\"" + escStr(v) + "\""
  protected lazy val one2jsonInt           : Int => String            = (v: Int) => s"$v"
  protected lazy val one2jsonLong          : Long => String           = (v: Long) => s"$v"
  protected lazy val one2jsonFloat         : Float => String          = (v: Float) => s"$v"
  protected lazy val one2jsonDouble        : Double => String         = (v: Double) => s"$v"
  protected lazy val one2jsonBoolean       : Boolean => String        = (v: Boolean) => if (v) "1" else "0"
  protected lazy val one2jsonBigInt        : BigInt => String         = (v: BigInt) => s"$v"
  protected lazy val one2jsonBigDecimal    : BigDecimal => String     = (v: BigDecimal) => s"$v"
  protected lazy val one2jsonDate          : Date => String           = (v: Date) => s"${v.getTime}"
  protected lazy val one2jsonDuration      : Duration => String       = (v: Duration) => "\"" + v.toString + "\""
  protected lazy val one2jsonInstant       : Instant => String        = (v: Instant) => "\"" + v.toString + "\""
  protected lazy val one2jsonLocalDate     : LocalDate => String      = (v: LocalDate) => "\"" + v.toString + "\""
  protected lazy val one2jsonLocalTime     : LocalTime => String      = (v: LocalTime) => "\"" + v.toString + "\""
  protected lazy val one2jsonLocalDateTime : LocalDateTime => String  = (v: LocalDateTime) => "\"" + v.toString + "\""
  protected lazy val one2jsonOffsetTime    : OffsetTime => String     = (v: OffsetTime) => "\"" + v.toString + "\""
  protected lazy val one2jsonOffsetDateTime: OffsetDateTime => String = (v: OffsetDateTime) => "\"" + v.toString + "\""
  protected lazy val one2jsonZonedDateTime : ZonedDateTime => String  = (v: ZonedDateTime) => "\"" + v.toString + "\""
  protected lazy val one2jsonUUID          : UUID => String           = (v: UUID) => "\"" + v.toString + "\""
  protected lazy val one2jsonURI           : URI => String            = (v: URI) => "\"" + v.toString.replace("'", "''") + "\""
  protected lazy val one2jsonByte          : Byte => String           = (v: Byte) => s"$v"
  protected lazy val one2jsonShort         : Short => String          = (v: Short) => s"$v"
  protected lazy val one2jsonChar          : Char => String           = (v: Char) => "\"" + v.toString + "\""

}
