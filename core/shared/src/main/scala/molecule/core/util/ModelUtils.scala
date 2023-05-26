package molecule.core.util

import molecule.base.error.ModelError
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
        case TxData(es)             => count(tail, acc + countTxDataElements(es))
        case _: Nested              => count(tail, acc + 1)
        case _: NestedOpt           => count(tail, acc + 1)
        case _                      => count(tail, acc)
      }
      case Nil       => acc
    }
  }
  private def countComposite(es: List[Element]): Int = count(es, 0).min(1)
  private def countTxDataElements(es: List[Element]): Int = count(es, 0)

  protected def countValueAttrs(elements: List[Element]): Int = {
    count(elements, 0)
  }


  protected def liftTxData(elements: List[Element]): List[Element] = {
    elements.last match {
      case Composite(es) if es.last.isInstanceOf[TxData] =>
        // Lift TxData up to top level
        elements.init :+ Composite(es.init) :+ es.last
      case _                                             => elements
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

  @tailrec
  final protected def getInitialNonGenericNs(elements: List[Element]): String = {
    elements.head match {
      case a: Attr if a.ns == "_Generic" => getInitialNonGenericNs(elements.tail)
      case a: Attr                       => a.ns
      case b: Ref                        => b.ns
      case Composite(es)                 => getInitialNonGenericNs(es)
      case other                         => throw ModelError("Unexpected head element: " + other)
    }
  }


  def separateTxElements(elements: List[Element]): (List[Element], List[Element]) = {
    elements.last match {
      case TxData(txElements) => (elements.init, txElements)
      case _                  => (elements, Nil)
    }
  }
}
