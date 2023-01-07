package molecule.core.util

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec


trait ModelUtils {

  protected def countValueAttrs(elements: Seq[Element]): Int = {
    def countValueAttrs(es: Seq[Element]): Int = {
      es.count {
        case _: Mandatory@unchecked => true
        case _: Optional@unchecked  => true
        case _                      => false
      }
    }
    elements.headOption match {
      case Some(_: Composite) =>
        elements.count {
          case Composite(es) => countValueAttrs(es) > 0
          case _             => false
        }
      case _                  => countValueAttrs(elements)
    }
  }

  @tailrec
  final protected def getInitialNs(elements: Seq[Element]): String = elements.head match {
    case a: Attr       => a.ns
    case b: Ref        => b.ns
    case Composite(es) => getInitialNs(es)
    case other         => throw MoleculeException("Unexpected head element: " + other)
  }


  def splitElements(elements: Seq[Element]): (Seq[Element], Seq[Element]) = {
    elements.last match {
      case TxMetaData(txMetaElements) => (elements.init, txMetaElements)
      case _                          => (elements, Nil)
    }
  }
}
