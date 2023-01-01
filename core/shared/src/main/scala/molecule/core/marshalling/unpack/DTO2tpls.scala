package molecule.core.marshalling.unpack

import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.DTO


case class DTO2tpls[Tpl](elements: Seq[Element], dto: DTO)
  extends UnpackValue[Tpl] {


  def unpack: List[Tpl] = {
    if (elements.size == 1) {
      unpackValues(elements.head)
    } else {
      ???
    }
  }
}
