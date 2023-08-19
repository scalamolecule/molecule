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
        case TxMetaData(es)         => count(tail, acc + countTxMetaDataElements(es))
        case _: Nested              => count(tail, acc + 1)
        case _: NestedOpt           => count(tail, acc + 1)
        case _                      => count(tail, acc)
      }
      case Nil       => acc
    }
  }
  private def countComposite(es: List[Element]): Int = count(es, 0).min(1)
  private def countTxMetaDataElements(es: List[Element]): Int = count(es, 0)

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

  @tailrec
  final protected def getInitialNonGenericNs(elements: List[Element]): String = {
    elements.head match {
      //      case a: Attr if a.ns == "_Generic" => getInitialNonGenericNs(elements.tail)
      case a: Attr if a.attr == "id" || a.attr == "tx" => getInitialNonGenericNs(elements.tail)
      case a: Attr                                     => a.ns
      case b: Ref                                      => b.ns
      case Composite(es)                               => getInitialNonGenericNs(es)
      case other                                       => throw ModelError("Unexpected head element: " + other)
    }
  }


  def separateTxElements(elements: List[Element]): (List[Element], List[Element]) = {
    val (main, tx, _) = elements.foldLeft(List.empty[Element], List.empty[Element], false) {
      //      case ((main, tx, _), ref@Ref(_, _, "Tx", _, _)) => (main, tx :+ ref, true)
      case ((main, tx, _), ref@Ref(_, _, "Tx", _, _)) => (main, tx, true)
      case ((main, tx, _), TxMetaData(txElements))    => (main, tx ++ txElements, true)
      case ((main, tx, false), element)               => (main :+ element, tx, false)
      case ((main, tx, _), element)                   => (main :+ element, tx :+ element, true)
    }
    (main, tx)
    //    elements.last match {
    //      case TxMetaData(txElements) => (elements.init, txElements)
    //      case _                      => (elements, Nil)
    //    }
  }


  def isRefUpdate(elements: List[Element]) = {
    elements.exists {
      case _: Ref => true
      case _      => false
    }
  }
}
