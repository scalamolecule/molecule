package molecule.core.marshalling.pack

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._


trait PackValue { self: Tpls2DTO =>

  protected lazy val packSingleValue: Element => Any => Unit = {
    case a: AttrOneMan => a match {
      case _: AttrOneManString     => (v: Any) => oneString.addOne(v.asInstanceOf[String])
      case _: AttrOneManInt        => (v: Any) => oneInt.addOne(v.asInstanceOf[Int])
      case _: AttrOneManLong       => (v: Any) => oneLong.addOne(v.asInstanceOf[Long])
      case _: AttrOneManFloat      => (v: Any) => oneFloat.addOne(v.asInstanceOf[Float])
      case _: AttrOneManDouble     => (v: Any) => oneDouble.addOne(v.asInstanceOf[Double])
      case _: AttrOneManBoolean    => (v: Any) => oneBoolean.addOne(v.asInstanceOf[Boolean])
      case _: AttrOneManBigInt     => (v: Any) => oneBigInt.addOne(v.asInstanceOf[BigInt])
      case _: AttrOneManBigDecimal => (v: Any) => oneBigDecimal.addOne(v.asInstanceOf[BigDecimal])
      case _: AttrOneManDate       => (v: Any) => oneDate.addOne(v.asInstanceOf[Date])
      case _: AttrOneManUUID       => (v: Any) => oneUUID.addOne(v.asInstanceOf[UUID])
      case _: AttrOneManURI        => (v: Any) => oneURI.addOne(v.asInstanceOf[URI])
      case _: AttrOneManByte       => (v: Any) => oneByte.addOne(v.asInstanceOf[Byte])
      case _: AttrOneManShort      => (v: Any) => oneShort.addOne(v.asInstanceOf[Short])
      case _: AttrOneManChar       => (v: Any) => oneChar.addOne(v.asInstanceOf[Char])
    }
    case a: AttrOneOpt => a match {
      case _: AttrOneOptString     => (v: Any) => oneOptString.addOne(v.asInstanceOf[Option[String]])
      case _: AttrOneOptInt        => (v: Any) => oneOptInt.addOne(v.asInstanceOf[Option[Int]])
      case _: AttrOneOptLong       => (v: Any) => oneOptLong.addOne(v.asInstanceOf[Option[Long]])
      case _: AttrOneOptFloat      => (v: Any) => oneOptFloat.addOne(v.asInstanceOf[Option[Float]])
      case _: AttrOneOptDouble     => (v: Any) => oneOptDouble.addOne(v.asInstanceOf[Option[Double]])
      case _: AttrOneOptBoolean    => (v: Any) => oneOptBoolean.addOne(v.asInstanceOf[Option[Boolean]])
      case _: AttrOneOptBigInt     => (v: Any) => oneOptBigInt.addOne(v.asInstanceOf[Option[BigInt]])
      case _: AttrOneOptBigDecimal => (v: Any) => oneOptBigDecimal.addOne(v.asInstanceOf[Option[BigDecimal]])
      case _: AttrOneOptDate       => (v: Any) => oneOptDate.addOne(v.asInstanceOf[Option[Date]])
      case _: AttrOneOptUUID       => (v: Any) => oneOptUUID.addOne(v.asInstanceOf[Option[UUID]])
      case _: AttrOneOptURI        => (v: Any) => oneOptURI.addOne(v.asInstanceOf[Option[URI]])
      case _: AttrOneOptByte       => (v: Any) => oneOptByte.addOne(v.asInstanceOf[Option[Byte]])
      case _: AttrOneOptShort      => (v: Any) => oneOptShort.addOne(v.asInstanceOf[Option[Short]])
      case _: AttrOneOptChar       => (v: Any) => oneOptChar.addOne(v.asInstanceOf[Option[Char]])
    }
    case a: AttrSetMan => a match {
      case _: AttrSetManString     => (v: Any) => setString.addOne(v.asInstanceOf[Set[String]])
      case _: AttrSetManInt        => (v: Any) => setInt.addOne(v.asInstanceOf[Set[Int]])
      case _: AttrSetManLong       => (v: Any) => setLong.addOne(v.asInstanceOf[Set[Long]])
      case _: AttrSetManFloat      => (v: Any) => setFloat.addOne(v.asInstanceOf[Set[Float]])
      case _: AttrSetManDouble     => (v: Any) => setDouble.addOne(v.asInstanceOf[Set[Double]])
      case _: AttrSetManBoolean    => (v: Any) => setBoolean.addOne(v.asInstanceOf[Set[Boolean]])
      case _: AttrSetManBigInt     => (v: Any) => setBigInt.addOne(v.asInstanceOf[Set[BigInt]])
      case _: AttrSetManBigDecimal => (v: Any) => setBigDecimal.addOne(v.asInstanceOf[Set[BigDecimal]])
      case _: AttrSetManDate       => (v: Any) => setDate.addOne(v.asInstanceOf[Set[Date]])
      case _: AttrSetManUUID       => (v: Any) => setUUID.addOne(v.asInstanceOf[Set[UUID]])
      case _: AttrSetManURI        => (v: Any) => setURI.addOne(v.asInstanceOf[Set[URI]])
      case _: AttrSetManByte       => (v: Any) => setByte.addOne(v.asInstanceOf[Set[Byte]])
      case _: AttrSetManShort      => (v: Any) => setShort.addOne(v.asInstanceOf[Set[Short]])
      case _: AttrSetManChar       => (v: Any) => setChar.addOne(v.asInstanceOf[Set[Char]])
    }
    case a: AttrSetOpt => a match {
      case _: AttrSetOptString     => (v: Any) => setOptString.addOne(v.asInstanceOf[Option[Set[String]]])
      case _: AttrSetOptInt        => (v: Any) => setOptInt.addOne(v.asInstanceOf[Option[Set[Int]]])
      case _: AttrSetOptLong       => (v: Any) => setOptLong.addOne(v.asInstanceOf[Option[Set[Long]]])
      case _: AttrSetOptFloat      => (v: Any) => setOptFloat.addOne(v.asInstanceOf[Option[Set[Float]]])
      case _: AttrSetOptDouble     => (v: Any) => setOptDouble.addOne(v.asInstanceOf[Option[Set[Double]]])
      case _: AttrSetOptBoolean    => (v: Any) => setOptBoolean.addOne(v.asInstanceOf[Option[Set[Boolean]]])
      case _: AttrSetOptBigInt     => (v: Any) => setOptBigInt.addOne(v.asInstanceOf[Option[Set[BigInt]]])
      case _: AttrSetOptBigDecimal => (v: Any) => setOptBigDecimal.addOne(v.asInstanceOf[Option[Set[BigDecimal]]])
      case _: AttrSetOptDate       => (v: Any) => setOptDate.addOne(v.asInstanceOf[Option[Set[Date]]])
      case _: AttrSetOptUUID       => (v: Any) => setOptUUID.addOne(v.asInstanceOf[Option[Set[UUID]]])
      case _: AttrSetOptURI        => (v: Any) => setOptURI.addOne(v.asInstanceOf[Option[Set[URI]]])
      case _: AttrSetOptByte       => (v: Any) => setOptByte.addOne(v.asInstanceOf[Option[Set[Byte]]])
      case _: AttrSetOptShort      => (v: Any) => setOptShort.addOne(v.asInstanceOf[Option[Set[Short]]])
      case _: AttrSetOptChar       => (v: Any) => setOptChar.addOne(v.asInstanceOf[Option[Set[Char]]])
    }
    case element       => err(element)
  }

  def err(element: Element): Nothing = {
    throw MoleculeException(
      s"""Unexpected element/value when packing single-value DTO:
         |  element: $element""".stripMargin
    )
  }
}
