package molecule.core.marshalling.pack

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._


trait PackValue { self: Tpls2DTO =>

  protected lazy val packSingleValue: (Element, Any) => Any => Unit = {
    case (a: AttrOneMan, v: Any) => a match {
      case _: AttrOneManString => (v: Any) => buf.oneString.addOne(v.asInstanceOf[String])
      case _: AttrOneManInt    =>
        v match {
          case _: Int  => (v: Any) => buf.oneInt.addOne(v.asInstanceOf[Int])
          case _: Long => (v: Any) => buf.oneInt.addOne(v.asInstanceOf[Long].toInt)
          case _       => err(a, v)
        }
      case _: AttrOneManLong       => (v: Any) => buf.oneLong.addOne(v.asInstanceOf[Long])
      case _: AttrOneManDouble     => (v: Any) => buf.oneDouble.addOne(v.asInstanceOf[Double])
      case _: AttrOneManBoolean    => (v: Any) => buf.oneBoolean.addOne(v.asInstanceOf[Boolean])
      case _: AttrOneManBigInt     => (v: Any) => buf.oneBigInt.addOne(v.asInstanceOf[BigInt])
      case _: AttrOneManBigDecimal => (v: Any) => buf.oneBigDecimal.addOne(v.asInstanceOf[BigDecimal])
      case _: AttrOneManDate       => (v: Any) => buf.oneDate.addOne(v.asInstanceOf[Date])
      case _: AttrOneManUUID       => (v: Any) => buf.oneUUID.addOne(v.asInstanceOf[UUID])
      case _: AttrOneManURI        => (v: Any) => buf.oneURI.addOne(v.asInstanceOf[URI])
      case _: AttrOneManByte       => (v: Any) => buf.oneByte.addOne(v.asInstanceOf[Byte])
      case _: AttrOneManShort      => (v: Any) => buf.oneShort.addOne(v.asInstanceOf[Short])
      case _: AttrOneManFloat      => (v: Any) => buf.oneFloat.addOne(v.asInstanceOf[Float])
      case _: AttrOneManChar       => (v: Any) => buf.oneChar.addOne(v.asInstanceOf[Char])
    }
    case (a: AttrOneOpt, v: Any) => a match {
      case _: AttrOneOptString     => (v: Any) => buf.oneOptString.addOne(v.asInstanceOf[Option[String]])
      case _: AttrOneOptInt        => (v: Any) => buf.oneOptInt.addOne(v.asInstanceOf[Option[Int]])
      case _: AttrOneOptLong       => (v: Any) => buf.oneOptLong.addOne(v.asInstanceOf[Option[Long]])
      case _: AttrOneOptDouble     => (v: Any) => buf.oneOptDouble.addOne(v.asInstanceOf[Option[Double]])
      case _: AttrOneOptBoolean    => (v: Any) => buf.oneOptBoolean.addOne(v.asInstanceOf[Option[Boolean]])
      case _: AttrOneOptBigInt     => (v: Any) => buf.oneOptBigInt.addOne(v.asInstanceOf[Option[BigInt]])
      case _: AttrOneOptBigDecimal => (v: Any) => buf.oneOptBigDecimal.addOne(v.asInstanceOf[Option[BigDecimal]])
      case _: AttrOneOptDate       => (v: Any) => buf.oneOptDate.addOne(v.asInstanceOf[Option[Date]])
      case _: AttrOneOptUUID       => (v: Any) => buf.oneOptUUID.addOne(v.asInstanceOf[Option[UUID]])
      case _: AttrOneOptURI        => (v: Any) => buf.oneOptURI.addOne(v.asInstanceOf[Option[URI]])
      case _: AttrOneOptByte       => (v: Any) => buf.oneOptByte.addOne(v.asInstanceOf[Option[Byte]])
      case _: AttrOneOptShort      => (v: Any) => buf.oneOptShort.addOne(v.asInstanceOf[Option[Short]])
      case _: AttrOneOptFloat      => (v: Any) => buf.oneOptFloat.addOne(v.asInstanceOf[Option[Float]])
      case _: AttrOneOptChar       => (v: Any) => buf.oneOptChar.addOne(v.asInstanceOf[Option[Char]])
    }
    case (a: AttrSetMan, v: Any) => a match {
      case _: AttrSetManString     => (v: Any) => buf.setString.addOne(v.asInstanceOf[Set[String]])
      case _: AttrSetManInt        => (v: Any) => buf.setInt.addOne(v.asInstanceOf[Set[Int]])
      case _: AttrSetManLong       => (v: Any) => buf.setLong.addOne(v.asInstanceOf[Set[Long]])
      case _: AttrSetManDouble     => (v: Any) => buf.setDouble.addOne(v.asInstanceOf[Set[Double]])
      case _: AttrSetManBoolean    => (v: Any) => buf.setBoolean.addOne(v.asInstanceOf[Set[Boolean]])
      case _: AttrSetManBigInt     => (v: Any) => buf.setBigInt.addOne(v.asInstanceOf[Set[BigInt]])
      case _: AttrSetManBigDecimal => (v: Any) => buf.setBigDecimal.addOne(v.asInstanceOf[Set[BigDecimal]])
      case _: AttrSetManDate       => (v: Any) => buf.setDate.addOne(v.asInstanceOf[Set[Date]])
      case _: AttrSetManUUID       => (v: Any) => buf.setUUID.addOne(v.asInstanceOf[Set[UUID]])
      case _: AttrSetManURI        => (v: Any) => buf.setURI.addOne(v.asInstanceOf[Set[URI]])
      case _: AttrSetManByte       => (v: Any) => buf.setByte.addOne(v.asInstanceOf[Set[Byte]])
      case _: AttrSetManShort      => (v: Any) => buf.setShort.addOne(v.asInstanceOf[Set[Short]])
      case _: AttrSetManFloat      => (v: Any) => buf.setFloat.addOne(v.asInstanceOf[Set[Float]])
      case _: AttrSetManChar       => (v: Any) => buf.setChar.addOne(v.asInstanceOf[Set[Char]])
    }
    case (a: AttrSetOpt, v: Any) => a match {
      case _: AttrSetOptString     => (v: Any) => buf.setOptString.addOne(v.asInstanceOf[Option[Set[String]]])
      case _: AttrSetOptInt        => (v: Any) => buf.setOptInt.addOne(v.asInstanceOf[Option[Set[Int]]])
      case _: AttrSetOptLong       => (v: Any) => buf.setOptLong.addOne(v.asInstanceOf[Option[Set[Long]]])
      case _: AttrSetOptDouble     => (v: Any) => buf.setOptDouble.addOne(v.asInstanceOf[Option[Set[Double]]])
      case _: AttrSetOptBoolean    => (v: Any) => buf.setOptBoolean.addOne(v.asInstanceOf[Option[Set[Boolean]]])
      case _: AttrSetOptBigInt     => (v: Any) => buf.setOptBigInt.addOne(v.asInstanceOf[Option[Set[BigInt]]])
      case _: AttrSetOptBigDecimal => (v: Any) => buf.setOptBigDecimal.addOne(v.asInstanceOf[Option[Set[BigDecimal]]])
      case _: AttrSetOptDate       => (v: Any) => buf.setOptDate.addOne(v.asInstanceOf[Option[Set[Date]]])
      case _: AttrSetOptUUID       => (v: Any) => buf.setOptUUID.addOne(v.asInstanceOf[Option[Set[UUID]]])
      case _: AttrSetOptURI        => (v: Any) => buf.setOptURI.addOne(v.asInstanceOf[Option[Set[URI]]])
      case _: AttrSetOptByte       => (v: Any) => buf.setOptByte.addOne(v.asInstanceOf[Option[Set[Byte]]])
      case _: AttrSetOptShort      => (v: Any) => buf.setOptShort.addOne(v.asInstanceOf[Option[Set[Short]]])
      case _: AttrSetOptFloat      => (v: Any) => buf.setOptFloat.addOne(v.asInstanceOf[Option[Set[Float]]])
      case _: AttrSetOptChar       => (v: Any) => buf.setOptChar.addOne(v.asInstanceOf[Option[Set[Char]]])
    }
    case (element, v)            => err(element, v)
  }

  def err(element: Element, v: Any): Nothing = {
    throw MoleculeException(
      s"""Unexpected element/value when packing single-value DTO:
         |  element: $element
         |  value  : $v""".stripMargin
    )
  }
}
