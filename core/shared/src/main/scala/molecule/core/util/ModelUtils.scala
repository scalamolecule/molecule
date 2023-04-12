package molecule.core.util

import molecule.base.error.ModelError
import molecule.boilerplate.ast
import molecule.boilerplate.ast.Model
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec


trait ModelUtils {

  @tailrec
  private def count(es: List[Element], acc: Int): Int = {
    es match {
      case e :: tail => e match {
        case _: Mandatory@unchecked => count(tail, acc + 1)
        case _: Optional@unchecked  => count(tail, acc + 1)
        case Composite(es)          => count(tail, acc + countComposite(es))
        case TxMetaData(es)         => count(tail, acc + countTxMeta(es))
        case _: Nested              => count(tail, acc + 1)
        case _: NestedOpt           => count(tail, acc + 1)
        case _                      => count(tail, acc)
      }
      case Nil       => acc
    }
  }
  private def countComposite(es: List[Element]): Int = count(es, 0).min(1)
  private def countTxMeta(es: List[Element]): Int = count(es, 0)

  protected def countValueAttrs(elements: List[Element]): Int = {
    count(elements, 0)
  }


  protected def liftTxMetaData(elements: List[Element]): List[Element] = {
    elements.last match {
      case Composite(es) if es.last.isInstanceOf[TxMetaData] =>
        // Lift TxMetaData up to top level
        elements.init :+ Composite(es.init) :+ es.last
      case _                                                 => elements
    }
  }


  @tailrec
  final protected def getInitialNs(elements: List[Element]): String = {
    elements.head match {
      case a: Attr       => a.ns
      case b: Ref        => b.ns
      case Composite(es) => getInitialNs(es)
      case other         => throw ModelError("Unexpected head element: " + other)
    }
  }


  def separateTxElements(elements: List[Element]): (List[Element], List[Element]) = {
    elements.last match {
      case TxMetaData(txMetaElements) => (elements.init, txMetaElements)
      case _                          => (elements, Nil)
    }
  }
}
