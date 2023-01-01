package molecule.core.marshalling.unpack

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._

trait UnpackValues[Tpl] { self: DTO2tpls[Tpl] =>

  protected def unpackValues(element: Element): List[Tpl] = {
    val list = element match {
      case a: AttrOneMan => a match {
        case _: AttrOneManString     => dto.oneString.toList
        case _: AttrOneManInt        => dto.oneInt.toList
        case _: AttrOneManLong       => dto.oneLong.toList
        case _: AttrOneManFloat      => dto.oneFloat.toList
        case _: AttrOneManDouble     => dto.oneDouble.toList
        case _: AttrOneManBoolean    => dto.oneBoolean.toList
        case _: AttrOneManBigInt     => dto.oneBigInt.toList
        case _: AttrOneManBigDecimal => dto.oneBigDecimal.toList
        case _: AttrOneManDate       => dto.oneDate.toList
        case _: AttrOneManUUID       => dto.oneUUID.toList
        case _: AttrOneManURI        => dto.oneURI.toList
        case _: AttrOneManByte       => dto.oneByte.toList
        case _: AttrOneManShort      => dto.oneShort.toList
        case _: AttrOneManChar       => dto.oneChar.toList
      }
      case a: AttrOneOpt => a match {
        case _: AttrOneOptString     => dto.oneOptString.toList
        case _: AttrOneOptInt        => dto.oneOptInt.toList
        case _: AttrOneOptLong       => dto.oneOptLong.toList
        case _: AttrOneOptFloat      => dto.oneOptFloat.toList
        case _: AttrOneOptDouble     => dto.oneOptDouble.toList
        case _: AttrOneOptBoolean    => dto.oneOptBoolean.toList
        case _: AttrOneOptBigInt     => dto.oneOptBigInt.toList
        case _: AttrOneOptBigDecimal => dto.oneOptBigDecimal.toList
        case _: AttrOneOptDate       => dto.oneOptDate.toList
        case _: AttrOneOptUUID       => dto.oneOptUUID.toList
        case _: AttrOneOptURI        => dto.oneOptURI.toList
        case _: AttrOneOptByte       => dto.oneOptByte.toList
        case _: AttrOneOptShort      => dto.oneOptShort.toList
        case _: AttrOneOptChar       => dto.oneOptChar.toList
      }
      case a: AttrSetMan => a match {
        case _: AttrSetManString     => dto.setString.toList
        case _: AttrSetManInt        => dto.setInt.toList
        case _: AttrSetManLong       => dto.setLong.toList
        case _: AttrSetManFloat      => dto.setFloat.toList
        case _: AttrSetManDouble     => dto.setDouble.toList
        case _: AttrSetManBoolean    => dto.setBoolean.toList
        case _: AttrSetManBigInt     => dto.setBigInt.toList
        case _: AttrSetManBigDecimal => dto.setBigDecimal.toList
        case _: AttrSetManDate       => dto.setDate.toList
        case _: AttrSetManUUID       => dto.setUUID.toList
        case _: AttrSetManURI        => dto.setURI.toList
        case _: AttrSetManByte       => dto.setByte.toList
        case _: AttrSetManShort      => dto.setShort.toList
        case _: AttrSetManChar       => dto.setChar.toList
      }
      case a: AttrSetOpt => a match {
        case _: AttrSetOptString     => dto.setOptString.toList
        case _: AttrSetOptInt        => dto.setOptInt.toList
        case _: AttrSetOptLong       => dto.setOptLong.toList
        case _: AttrSetOptFloat      => dto.setOptFloat.toList
        case _: AttrSetOptDouble     => dto.setOptDouble.toList
        case _: AttrSetOptBoolean    => dto.setOptBoolean.toList
        case _: AttrSetOptBigInt     => dto.setOptBigInt.toList
        case _: AttrSetOptBigDecimal => dto.setOptBigDecimal.toList
        case _: AttrSetOptDate       => dto.setOptDate.toList
        case _: AttrSetOptUUID       => dto.setOptUUID.toList
        case _: AttrSetOptURI        => dto.setOptURI.toList
        case _: AttrSetOptByte       => dto.setOptByte.toList
        case _: AttrSetOptShort      => dto.setOptShort.toList
        case _: AttrSetOptChar       => dto.setOptChar.toList
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
