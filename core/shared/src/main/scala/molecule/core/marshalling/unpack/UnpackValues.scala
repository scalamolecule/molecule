package molecule.core.marshalling.unpack

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._

trait UnpackValues[Tpl] { self: DTO2tpls[Tpl] =>

  protected def unpackValues(element: Element): List[Tpl] = {
    val list = element match {
      case a: AttrOneMan => a match {
        case _: AttrOneManString     => dto.oneString
        case _: AttrOneManInt        => dto.oneInt
        case _: AttrOneManLong       => dto.oneLong
        case _: AttrOneManFloat      => dto.oneFloat
        case _: AttrOneManDouble     => dto.oneDouble
        case _: AttrOneManBoolean    => dto.oneBoolean
        case _: AttrOneManBigInt     => dto.oneBigInt
        case _: AttrOneManBigDecimal => dto.oneBigDecimal
        case _: AttrOneManDate       => dto.oneDate
        case _: AttrOneManUUID       => dto.oneUUID
        case _: AttrOneManURI        => dto.oneURI
        case _: AttrOneManByte       => dto.oneByte
        case _: AttrOneManShort      => dto.oneShort
        case _: AttrOneManChar       => dto.oneChar
      }
      case a: AttrOneOpt => a match {
        case _: AttrOneOptString     => dto.oneOptString
        case _: AttrOneOptInt        => dto.oneOptInt
        case _: AttrOneOptLong       => dto.oneOptLong
        case _: AttrOneOptFloat      => dto.oneOptFloat
        case _: AttrOneOptDouble     => dto.oneOptDouble
        case _: AttrOneOptBoolean    => dto.oneOptBoolean
        case _: AttrOneOptBigInt     => dto.oneOptBigInt
        case _: AttrOneOptBigDecimal => dto.oneOptBigDecimal
        case _: AttrOneOptDate       => dto.oneOptDate
        case _: AttrOneOptUUID       => dto.oneOptUUID
        case _: AttrOneOptURI        => dto.oneOptURI
        case _: AttrOneOptByte       => dto.oneOptByte
        case _: AttrOneOptShort      => dto.oneOptShort
        case _: AttrOneOptChar       => dto.oneOptChar
      }
      case a: AttrSetMan => a match {
        case _: AttrSetManString     => dto.setString
        case _: AttrSetManInt        => dto.setInt
        case _: AttrSetManLong       => dto.setLong
        case _: AttrSetManFloat      => dto.setFloat
        case _: AttrSetManDouble     => dto.setDouble
        case _: AttrSetManBoolean    => dto.setBoolean
        case _: AttrSetManBigInt     => dto.setBigInt
        case _: AttrSetManBigDecimal => dto.setBigDecimal
        case _: AttrSetManDate       => dto.setDate
        case _: AttrSetManUUID       => dto.setUUID
        case _: AttrSetManURI        => dto.setURI
        case _: AttrSetManByte       => dto.setByte
        case _: AttrSetManShort      => dto.setShort
        case _: AttrSetManChar       => dto.setChar
      }
      case a: AttrSetOpt => a match {
        case _: AttrSetOptString     => dto.setOptString
        case _: AttrSetOptInt        => dto.setOptInt
        case _: AttrSetOptLong       => dto.setOptLong
        case _: AttrSetOptFloat      => dto.setOptFloat
        case _: AttrSetOptDouble     => dto.setOptDouble
        case _: AttrSetOptBoolean    => dto.setOptBoolean
        case _: AttrSetOptBigInt     => dto.setOptBigInt
        case _: AttrSetOptBigDecimal => dto.setOptBigDecimal
        case _: AttrSetOptDate       => dto.setOptDate
        case _: AttrSetOptUUID       => dto.setOptUUID
        case _: AttrSetOptURI        => dto.setOptURI
        case _: AttrSetOptByte       => dto.setOptByte
        case _: AttrSetOptShort      => dto.setOptShort
        case _: AttrSetOptChar       => dto.setOptChar
      }
      case element       => err(element)
    }
    list.asInstanceOf[List[Tpl]]
  }


  private def err(element: Element): Nothing = {
    throw MoleculeException(
      s"""Unexpected element/value when unpacking single-value DTO:
         |  element: $element""".stripMargin
    )
  }
}
