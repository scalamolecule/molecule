package molecule.core.transaction.ops

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error.ExecutionError
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

  protected lazy val set2arrayID            : Set[String] => Array[AnyRef]         = (set: Set[String]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayString        : Set[String] => Array[AnyRef]         = (set: Set[String]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayInt           : Set[Int] => Array[AnyRef]            = (set: Set[Int]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayLong          : Set[Long] => Array[AnyRef]           = (set: Set[Long]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayFloat         : Set[Float] => Array[AnyRef]          = (set: Set[Float]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayDouble        : Set[Double] => Array[AnyRef]         = (set: Set[Double]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayBoolean       : Set[Boolean] => Array[AnyRef]        = (set: Set[Boolean]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayBigInt        : Set[BigInt] => Array[AnyRef]         = (set: Set[BigInt]) => set.map(v => BigDecimal(v).bigDecimal.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayBigDecimal    : Set[BigDecimal] => Array[AnyRef]     = (set: Set[BigDecimal]) => set.map(v => v.bigDecimal.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayDate          : Set[Date] => Array[AnyRef]           = (set: Set[Date]) => set.map(_.getTime.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayDuration      : Set[Duration] => Array[AnyRef]       = (set: Set[Duration]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayInstant       : Set[Instant] => Array[AnyRef]        = (set: Set[Instant]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayLocalDate     : Set[LocalDate] => Array[AnyRef]      = (set: Set[LocalDate]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayLocalTime     : Set[LocalTime] => Array[AnyRef]      = (set: Set[LocalTime]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayLocalDateTime : Set[LocalDateTime] => Array[AnyRef]  = (set: Set[LocalDateTime]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayOffsetTime    : Set[OffsetTime] => Array[AnyRef]     = (set: Set[OffsetTime]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayOffsetDateTime: Set[OffsetDateTime] => Array[AnyRef] = (set: Set[OffsetDateTime]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayZonedDateTime : Set[ZonedDateTime] => Array[AnyRef]  = (set: Set[ZonedDateTime]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayUUID          : Set[UUID] => Array[AnyRef]           = (set: Set[UUID]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayURI           : Set[URI] => Array[AnyRef]            = (set: Set[URI]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val set2arrayByte          : Set[Byte] => Array[AnyRef]           = (set: Set[Byte]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayShort         : Set[Short] => Array[AnyRef]          = (set: Set[Short]) => set.asInstanceOf[Set[AnyRef]].toArray
  protected lazy val set2arrayChar          : Set[Char] => Array[AnyRef]           = (set: Set[Char]) => set.asInstanceOf[Set[AnyRef]].toArray

  protected lazy val seq2arrayID            : Seq[String] => Array[AnyRef]         = (seq: Seq[String]) => seq.asInstanceOf[Seq[AnyRef]].toArray
  protected lazy val seq2arrayString        : Seq[String] => Array[AnyRef]         = (seq: Seq[String]) => seq.asInstanceOf[Seq[AnyRef]].toArray
  protected lazy val seq2arrayInt           : Seq[Int] => Array[AnyRef]            = (seq: Seq[Int]) => seq.asInstanceOf[Seq[AnyRef]].toArray
  protected lazy val seq2arrayLong          : Seq[Long] => Array[AnyRef]           = (seq: Seq[Long]) => seq.asInstanceOf[Seq[AnyRef]].toArray
  protected lazy val seq2arrayFloat         : Seq[Float] => Array[AnyRef]          = (seq: Seq[Float]) => seq.asInstanceOf[Seq[AnyRef]].toArray
  protected lazy val seq2arrayDouble        : Seq[Double] => Array[AnyRef]         = (seq: Seq[Double]) => seq.asInstanceOf[Seq[AnyRef]].toArray
  protected lazy val seq2arrayBoolean       : Seq[Boolean] => Array[AnyRef]        = (seq: Seq[Boolean]) => seq.asInstanceOf[Seq[AnyRef]].toArray
  protected lazy val seq2arrayBigInt        : Seq[BigInt] => Array[AnyRef]         = (seq: Seq[BigInt]) => seq.map(v => BigDecimal(v).bigDecimal.asInstanceOf[AnyRef]).toArray
  protected lazy val seq2arrayBigDecimal    : Seq[BigDecimal] => Array[AnyRef]     = (seq: Seq[BigDecimal]) => seq.map(v => v.bigDecimal.asInstanceOf[AnyRef]).toArray
  protected lazy val seq2arrayDate          : Seq[Date] => Array[AnyRef]           = (seq: Seq[Date]) => seq.map(_.getTime.asInstanceOf[AnyRef]).toArray
  protected lazy val seq2arrayDuration      : Seq[Duration] => Array[AnyRef]       = (seq: Seq[Duration]) => seq.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val seq2arrayInstant       : Seq[Instant] => Array[AnyRef]        = (seq: Seq[Instant]) => seq.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val seq2arrayLocalDate     : Seq[LocalDate] => Array[AnyRef]      = (seq: Seq[LocalDate]) => seq.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val seq2arrayLocalTime     : Seq[LocalTime] => Array[AnyRef]      = (seq: Seq[LocalTime]) => seq.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val seq2arrayLocalDateTime : Seq[LocalDateTime] => Array[AnyRef]  = (seq: Seq[LocalDateTime]) => seq.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val seq2arrayOffsetTime    : Seq[OffsetTime] => Array[AnyRef]     = (seq: Seq[OffsetTime]) => seq.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val seq2arrayOffsetDateTime: Seq[OffsetDateTime] => Array[AnyRef] = (seq: Seq[OffsetDateTime]) => seq.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val seq2arrayZonedDateTime : Seq[ZonedDateTime] => Array[AnyRef]  = (seq: Seq[ZonedDateTime]) => seq.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val seq2arrayUUID          : Seq[UUID] => Array[AnyRef]           = (seq: Seq[UUID]) => seq.asInstanceOf[Seq[AnyRef]].toArray
  protected lazy val seq2arrayURI           : Seq[URI] => Array[AnyRef]            = (seq: Seq[URI]) => seq.map(_.toString.asInstanceOf[AnyRef]).toArray
  protected lazy val seq2arrayByte          : Seq[Byte] => Array[AnyRef]           = (seq: Seq[Byte]) => seq.asInstanceOf[Seq[AnyRef]].toArray
  protected lazy val seq2arrayShort         : Seq[Short] => Array[AnyRef]          = (seq: Seq[Short]) => seq.asInstanceOf[Seq[AnyRef]].toArray
  protected lazy val seq2arrayChar          : Seq[Char] => Array[AnyRef]           = (seq: Seq[Char]) => seq.asInstanceOf[Seq[AnyRef]].toArray

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

  protected def optArray(array: Array[Byte]): Option[Array[Byte]] = if (array.nonEmpty) Some(array) else None

}
