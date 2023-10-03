package molecule.core.transaction.ops

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.BaseHelpers
import molecule.core.transformation.JsonBase

trait BaseOps extends JsonBase with BaseHelpers {

  protected lazy val transformString    : String => Any     = identity
  protected lazy val transformInt       : Int => Any        = identity
  protected lazy val transformLong      : Long => Any       = identity
  protected lazy val transformFloat     : Float => Any      = identity
  protected lazy val transformDouble    : Double => Any     = identity
  protected lazy val transformBoolean   : Boolean => Any    = identity
  protected lazy val transformBigInt    : BigInt => Any     = identity
  protected lazy val transformBigDecimal: BigDecimal => Any = identity
  protected lazy val transformDate      : Date => Any       = identity
  protected lazy val transformUUID      : UUID => Any       = identity
  protected lazy val transformURI       : URI => Any        = identity
  protected lazy val transformByte      : Byte => Any       = identity
  protected lazy val transformShort     : Short => Any      = identity
  protected lazy val transformChar      : Char => Any       = identity

  protected lazy val handleString    : Any => Any = identity
  protected lazy val handleInt       : Any => Any = identity
  protected lazy val handleLong      : Any => Any = identity
  protected lazy val handleFloat     : Any => Any = identity
  protected lazy val handleDouble    : Any => Any = identity
  protected lazy val handleBoolean   : Any => Any = identity
  protected lazy val handleBigInt    : Any => Any = identity
  protected lazy val handleBigDecimal: Any => Any = identity
  protected lazy val handleDate      : Any => Any = identity
  protected lazy val handleUUID      : Any => Any = identity
  protected lazy val handleURI       : Any => Any = identity
  protected lazy val handleByte      : Any => Any = identity
  protected lazy val handleShort     : Any => Any = identity
  protected lazy val handleChar      : Any => Any = identity

  protected lazy val set2arrayString    : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayInt       : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayLong      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayFloat     : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayDouble    : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayBoolean   : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayBigInt    : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayBigDecimal: Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayDate      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayUUID      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayURI       : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayByte      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayShort     : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayChar      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]

  protected lazy val extsString     = List("", "")
  protected lazy val extsInt        = List("", "")
  protected lazy val extsLong       = List("", "")
  protected lazy val extsFloat      = List("", "")
  protected lazy val extsDouble     = List("", "")
  protected lazy val extsBoolean    = List("", "")
  protected lazy val extsBigInt     = List("", "")
  protected lazy val extsBigDecimal = List("", "")
  protected lazy val extsDate       = List("", "")
  protected lazy val extsUUID       = List("", "")
  protected lazy val extsURI        = List("", "")
  protected lazy val extsByte       = List("", "")
  protected lazy val extsShort      = List("", "")
  protected lazy val extsChar       = List("", "")

  protected lazy val value2jsonString    : (StringBuffer, String) => StringBuffer     = (buf: StringBuffer, v: String) => quote(buf, v)
  protected lazy val value2jsonInt       : (StringBuffer, Int) => StringBuffer        = (buf: StringBuffer, v: Int) => buf.append(v)
  protected lazy val value2jsonLong      : (StringBuffer, Long) => StringBuffer       = (buf: StringBuffer, v: Long) => buf.append(v)
  protected lazy val value2jsonFloat     : (StringBuffer, Float) => StringBuffer      = (buf: StringBuffer, v: Float) => buf.append(v)
  protected lazy val value2jsonDouble    : (StringBuffer, Double) => StringBuffer     = (buf: StringBuffer, v: Double) => buf.append(v)
  protected lazy val value2jsonBoolean   : (StringBuffer, Boolean) => StringBuffer    = (buf: StringBuffer, v: Boolean) => buf.append(if (v) "1" else "0")
  protected lazy val value2jsonBigInt    : (StringBuffer, BigInt) => StringBuffer     = (buf: StringBuffer, v: BigInt) => buf.append(v)
  protected lazy val value2jsonBigDecimal: (StringBuffer, BigDecimal) => StringBuffer = (buf: StringBuffer, v: BigDecimal) => buf.append(v)
  protected lazy val value2jsonDate      : (StringBuffer, Date) => StringBuffer       = (buf: StringBuffer, v: Date) => quote(buf, date2str(v))
  protected lazy val value2jsonUUID      : (StringBuffer, UUID) => StringBuffer       = (buf: StringBuffer, v: UUID) => quote(buf, v.toString)
  protected lazy val value2jsonURI       : (StringBuffer, URI) => StringBuffer        = (buf: StringBuffer, v: URI) => quote(buf, v.toString)
  protected lazy val value2jsonByte      : (StringBuffer, Byte) => StringBuffer       = (buf: StringBuffer, v: Byte) => buf.append(v)
  protected lazy val value2jsonShort     : (StringBuffer, Short) => StringBuffer      = (buf: StringBuffer, v: Short) => buf.append(v)
  protected lazy val value2jsonChar      : (StringBuffer, Char) => StringBuffer       = (buf: StringBuffer, v: Char) => quote(buf, v.toString)


  protected lazy val one2jsonString    : String => String     = (v: String) => "\"" + escStr(v) + "\""
  protected lazy val one2jsonInt       : Int => String        = (v: Int) => s"$v"
  protected lazy val one2jsonLong      : Long => String       = (v: Long) => s"$v"
  protected lazy val one2jsonFloat     : Float => String      = (v: Float) => s"$v"
  protected lazy val one2jsonDouble    : Double => String     = (v: Double) => s"$v"
  protected lazy val one2jsonBoolean   : Boolean => String    = (v: Boolean) => if (v) "1" else "0"
  protected lazy val one2jsonBigInt    : BigInt => String     = (v: BigInt) => s"$v"
  protected lazy val one2jsonBigDecimal: BigDecimal => String = (v: BigDecimal) => s"$v"
  protected lazy val one2jsonDate      : Date => String       = (v: Date) => "\"" + date2str(v) + "\""
  protected lazy val one2jsonUUID      : UUID => String       = (v: UUID) => "\"" + v.toString + "\""
  protected lazy val one2jsonURI       : URI => String        = (v: URI) => "\"" + v.toString.replace("'", "''") + "\""
  protected lazy val one2jsonByte      : Byte => String       = (v: Byte) => s"$v"
  protected lazy val one2jsonShort     : Short => String      = (v: Short) => s"$v"
  protected lazy val one2jsonChar      : Char => String       = (v: Char) => "\"" + v.toString + "\""

}
