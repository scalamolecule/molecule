package molecule.core.marshalling.unpack

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._

trait UnpackValue[Tpl] { self: DTO2tpls[Tpl] =>

  protected def unpackValues(element: Element): List[Tpl] = {
    val list = element match {
      case a: AttrOneMan => a match {
        case _: AttrOneManInt => dto.oneInt.toList
        case element          => err(element)
      }
      case element       => err(element)
    }
    list.asInstanceOf[List[Tpl]]
  }


  def err(element: Element): Nothing = {
    throw MoleculeException(
      s"""Unexpected element/value when packing single-value DTO:
         |  element: $element""".stripMargin
    )
  }
}
