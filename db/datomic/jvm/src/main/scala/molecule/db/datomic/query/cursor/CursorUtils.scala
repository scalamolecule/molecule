package molecule.db.datomic.query.cursor

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.BaseHelpers
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._

trait CursorUtils extends BaseHelpers {

  protected def tpeEncode(element: Element): (String, Any => String) = {
    element match {
      case a: AttrOneMan =>
        a match {
          case AttrOneManString(_, _, _, _, _, _, _)     => ("String", (v: Any) => escStr(v.toString))
          case AttrOneManInt(_, _, _, _, _, _, _)        => ("Int", (v: Any) => v.toString)
          case AttrOneManLong(_, _, _, _, _, _, _, _)    => ("Long", (v: Any) => v.toString)
          case AttrOneManFloat(_, _, _, _, _, _, _)      => ("Float", (v: Any) => v.toString)
          case AttrOneManDouble(_, _, _, _, _, _, _)     => ("Double", (v: Any) => v.toString)
          case AttrOneManBoolean(_, _, _, _, _, _, _)    => ("Boolean", (v: Any) => v.toString)
          case AttrOneManBigInt(_, _, _, _, _, _, _)     => ("BigInt", (v: Any) => v.toString)
          case AttrOneManBigDecimal(_, _, _, _, _, _, _) => ("BigDecimal", (v: Any) => v.toString)
          case AttrOneManDate(_, _, _, _, _, _, _)       => ("Date", (v: Any) => date2str(v.asInstanceOf[Date]))
          case AttrOneManUUID(_, _, _, _, _, _, _)       => ("UUID", (v: Any) => v.toString)
          case AttrOneManURI(_, _, _, _, _, _, _)        => ("URI", (v: Any) => v.toString)
          case AttrOneManByte(_, _, _, _, _, _, _)       => ("Byte", (v: Any) => v.toString)
          case AttrOneManShort(_, _, _, _, _, _, _)      => ("Short", (v: Any) => v.toString)
          case AttrOneManChar(_, _, _, _, _, _, _)       => ("Char", (v: Any) => v.toString)
        }
      case a: AttrOneOpt =>
        a match {
          case AttrOneOptString(_, _, _, _, _, _, _)     => ("String", (v: Any) => escStr(v.toString))
          case AttrOneOptInt(_, _, _, _, _, _, _)        => ("Int", (v: Any) => v.toString)
          case AttrOneOptLong(_, _, _, _, _, _, _, _)    => ("Long", (v: Any) => v.toString)
          case AttrOneOptFloat(_, _, _, _, _, _, _)      => ("Float", (v: Any) => v.toString)
          case AttrOneOptDouble(_, _, _, _, _, _, _)     => ("Double", (v: Any) => v.toString)
          case AttrOneOptBoolean(_, _, _, _, _, _, _)    => ("Boolean", (v: Any) => v.toString)
          case AttrOneOptBigInt(_, _, _, _, _, _, _)     => ("BigInt", (v: Any) => v.toString)
          case AttrOneOptBigDecimal(_, _, _, _, _, _, _) => ("BigDecimal", (v: Any) => v.toString)
          case AttrOneOptDate(_, _, _, _, _, _, _)       => ("Date", (v: Any) => date2str(v.asInstanceOf[Date]))
          case AttrOneOptUUID(_, _, _, _, _, _, _)       => ("UUID", (v: Any) => v.toString)
          case AttrOneOptURI(_, _, _, _, _, _, _)        => ("URI", (v: Any) => v.toString)
          case AttrOneOptByte(_, _, _, _, _, _, _)       => ("Byte", (v: Any) => v.toString)
          case AttrOneOptShort(_, _, _, _, _, _, _)      => ("Short", (v: Any) => v.toString)
          case AttrOneOptChar(_, _, _, _, _, _, _)       => ("Char", (v: Any) => v.toString)
        }
      case other         => throw MoleculeError("Unexpected element for tpeEncode: " + other)
    }
  }

  protected def encoder(tpe: String): Any => String = {
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
