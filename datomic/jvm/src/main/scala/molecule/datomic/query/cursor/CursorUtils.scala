package molecule.datomic.query.cursor

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.BaseHelpers
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._

trait CursorUtils extends BaseHelpers {

  protected def tpeEncode(element: AttrOne): (String, Any => String) = {
    element match {
      case a: AttrOneMan =>
        a match {
          case _: AttrOneManString     => ("String", (v: Any) => escStr(v.toString))
          case _: AttrOneManInt        => ("Int", (v: Any) => v.toString)
          case _: AttrOneManLong       => ("Long", (v: Any) => v.toString)
          case _: AttrOneManFloat      => ("Float", (v: Any) => v.toString)
          case _: AttrOneManDouble     => ("Double", (v: Any) => v.toString)
          case _: AttrOneManBoolean    => ("Boolean", (v: Any) => v.toString)
          case _: AttrOneManBigInt     => ("BigInt", (v: Any) => v.toString)
          case _: AttrOneManBigDecimal => ("BigDecimal", (v: Any) => v.toString)
          case _: AttrOneManDate       => ("Date", (v: Any) => date2str(v.asInstanceOf[Date]))
          case _: AttrOneManUUID       => ("UUID", (v: Any) => v.toString)
          case _: AttrOneManURI        => ("URI", (v: Any) => v.toString)
          case _: AttrOneManByte       => ("Byte", (v: Any) => v.toString)
          case _: AttrOneManShort      => ("Short", (v: Any) => v.toString)
          case _: AttrOneManChar       => ("Char", (v: Any) => v.toString)
        }
      case a: AttrOneOpt =>
        a match {
          case _: AttrOneOptString     => ("String", (v: Any) => escStr(v.toString))
          case _: AttrOneOptInt        => ("Int", (v: Any) => v.toString)
          case _: AttrOneOptLong       => ("Long", (v: Any) => v.toString)
          case _: AttrOneOptFloat      => ("Float", (v: Any) => v.toString)
          case _: AttrOneOptDouble     => ("Double", (v: Any) => v.toString)
          case _: AttrOneOptBoolean    => ("Boolean", (v: Any) => v.toString)
          case _: AttrOneOptBigInt     => ("BigInt", (v: Any) => v.toString)
          case _: AttrOneOptBigDecimal => ("BigDecimal", (v: Any) => v.toString)
          case _: AttrOneOptDate       => ("Date", (v: Any) => v.toString) // (row hash used instead)
          case _: AttrOneOptUUID       => ("UUID", (v: Any) => v.toString)
          case _: AttrOneOptURI        => ("URI", (v: Any) => v.toString)
          case _: AttrOneOptByte       => ("Byte", (v: Any) => v.toString)
          case _: AttrOneOptShort      => ("Short", (v: Any) => v.toString)
          case _: AttrOneOptChar       => ("Char", (v: Any) => v.toString)
        }
      case other         => throw MoleculeError("Unexpected element for tpeEncode: " + other)
    }
  }

  protected def encoder(tpe: String, kind: String): Any => String = {
    if (kind == "OPTIONAL") {
      tpe match {
        case "String"     => (v: Any) => v.toString
        case "Int"        => (v: Any) => v.toString
        case "Long"       => (v: Any) => v.toString
        case "Float"      => (v: Any) => v.toString
        case "Double"     => (v: Any) => v.toString
        case "Boolean"    => (v: Any) => v.toString
        case "BigInt"     => (v: Any) => v.toString
        case "BigDecimal" => (v: Any) => v.toString
        case "Date"       => (v: Any) => v.asInstanceOf[Option[Date]].map(date2str(_)).toString
        case "UUID"       => (v: Any) => v.toString
        case "URI"        => (v: Any) => v.toString
        case "Byte"       => (v: Any) => v.toString
        case "Short"      => (v: Any) => v.toString
        case "Char"       => (v: Any) => v.toString
      }
    }
    else
      tpe match {
        case "String"     => (v: Any) => v.toString
        case "Int"        => (v: Any) => v.toString
        case "Long"       => (v: Any) => v.toString
        case "Float"      => (v: Any) => v.toString
        case "Double"     => (v: Any) => v.toString
        case "Boolean"    => (v: Any) => v.toString
        case "BigInt"     => (v: Any) => v.toString
        case "BigDecimal" => (v: Any) => v.toString
        case "Date"       => (v: Any) => date2str(v.asInstanceOf[Date])
        case "UUID"       => (v: Any) => v.toString
        case "URI"        => (v: Any) => v.toString
        case "Byte"       => (v: Any) => v.toString
        case "Short"      => (v: Any) => v.toString
        case "Char"       => (v: Any) => v.toString
      }
  }

  // Decode String value from cursor to java value for direct comparison with raw row value
  protected def decoder(tpe: String): String => Any = {
    tpe match {
      case "String"     => (v: String) => unescStr(v)
      case "Int"        => (v: String) => v.toInt
      case "Long"       => (v: String) => v.toLong
      case "Float"      => (v: String) => v.toFloat
      case "Double"     => (v: String) => v.toDouble
      case "Boolean"    => (v: String) => v.toBoolean
      case "BigInt"     => (v: String) => BigInt(v).bigInteger
      case "BigDecimal" => (v: String) => BigDecimal(v).bigDecimal
      case "Date"       => (v: String) => str2date(v)
      case "UUID"       => (v: String) => UUID.fromString(v)
      case "URI"        => (v: String) => new URI(v)
      case "Byte"       => (v: String) => v.toByte
      case "Short"      => (v: String) => v.toShort
      case "Char"       => (v: String) => v // compare String
    }
  }

  protected def getFilterAttr(tpe: String, ns: String, attr: String, fn: Op, v: String): AttrOneTac = {
    tpe match {
      case "String"     => AttrOneTacString(ns, attr, fn, Seq(unescStr(v)))
      case "Int"        => AttrOneTacInt(ns, attr, fn, Seq(v.toInt))
      case "Long"       => AttrOneTacLong(ns, attr, fn, Seq(v.toLong))
      case "Float"      => AttrOneTacFloat(ns, attr, fn, Seq(v.toFloat))
      case "Double"     => AttrOneTacDouble(ns, attr, fn, Seq(v.toDouble))
      case "Boolean"    => AttrOneTacBoolean(ns, attr, fn, Seq(v.toBoolean))
      case "BigInt"     => AttrOneTacBigInt(ns, attr, fn, Seq(BigInt(v)))
      case "BigDecimal" => AttrOneTacBigDecimal(ns, attr, fn, Seq(BigDecimal(v)))
      case "Date"       => AttrOneTacDate(ns, attr, fn, Seq(str2date(v)))
      case "UUID"       => AttrOneTacUUID(ns, attr, fn, Seq(UUID.fromString(v)))
      case "URI"        => AttrOneTacURI(ns, attr, fn, Seq(new URI(v)))
      case "Byte"       => AttrOneTacByte(ns, attr, fn, Seq(v.toByte))
      case "Short"      => AttrOneTacShort(ns, attr, fn, Seq(v.toShort))
      case "Char"       => AttrOneTacChar(ns, attr, fn, Seq(v.charAt(0)))
    }
  }
}
