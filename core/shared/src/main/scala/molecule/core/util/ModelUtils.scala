package molecule.core.util

import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec


trait ModelUtils {

  protected def countValueAttrs(elements: List[Element]): Int = {
    def countValueAttrs(es: List[Element]): Int = {
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
  final protected def getInitialNs(elements: List[Element]): String = {
    elements.head match {
      case a: Attr       => a.ns
      case b: Ref        => b.ns
      case Composite(es) => getInitialNs(es)
      case other         => throw MoleculeError("Unexpected head element: " + other)
    }
  }

//  @tailrec
//  final protected def attrsHaveAppliedValue(elements: List[Element]): Unit = {
//    elements match {
//      case element :: tail => element match {
//        case a: Attr if a.op != Appl => missingAppliedValue(a)
//        case _: Attr                 => attrsHaveAppliedValue(tail)
//        case _: Ref                  => attrsHaveAppliedValue(tail)
//        case _: BackRef              => attrsHaveAppliedValue(tail)
//        case Composite(es)           => attrsHaveAppliedValue(es)
//        case other                   => throw MoleculeError("Unexpected element: " + other)
//      }
//      case Nil             => ()
//    }
//  }

  //  @tailrec
  //  final protected def nestedAttrsHaveNoExpr(elements: List[Element]): Unit = {
  //    elements match {
  //      case element :: tail => element match {
  //        case a: Attr if a.op != V => noExprInNested(a)
  //        case _: Attr              => nestedAttrsHaveNoExpr(tail)
  //        case _: Ref               => nestedAttrsHaveNoExpr(tail)
  //        case _: BackRef           => nestedAttrsHaveNoExpr(tail)
  //        case Composite(es)        => nestedAttrsHaveNoExpr(es)
  //        case Nested(_, es)        => nestedAttrsHaveNoExpr(es)
  //        case NestedOpt(_, es)     => nestedAttrsHaveNoExpr(es)
  //        case TxMetaData(es)       => nestedAttrsHaveNoExpr(es)
  //        case other                => throw MoleculeError("Unexpected element: " + other)
  //      }
  //      case Nil             => ()
  //    }
  //  }

//  def missingAppliedValue(a: Attr) =
//    throw MoleculeError("Missing applied value for attribute:\n" + a)

//  def noExprInNested(a: Attr) =
//    throw MoleculeError(
//      "Expressions not allowed in optional nested data structure. Found:\n" + a
//    )

  def splitElements(elements: List[Element]): (List[Element], List[Element]) = {
    elements.last match {
      case TxMetaData(txMetaElements) => (elements.init, txMetaElements)
      case _                          => (elements, Nil)
    }
  }
}
