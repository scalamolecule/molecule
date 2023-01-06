package molecule.core.util

import molecule.boilerplate.ast.Model._


trait ModelUtils {

  protected def countValueAttrs(elements: Seq[Element]): Int = {
    elements.count {
      case _: Mandatory@unchecked => true
      case _: Optional@unchecked  => true
      case _                      => false
    }
  }

  def splitElements(elements: Seq[Element]): (Seq[Element], Seq[Element]) = {
    elements.last match {
      case TxMetaData(txMetaElements) => (elements.init, txMetaElements)
      case _                          => (elements, Nil)
    }
  }
}
