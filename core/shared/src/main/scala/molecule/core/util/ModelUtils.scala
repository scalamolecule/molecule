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
        case _: Nested              => count(tail, acc + 1)
        case _: NestedOpt           => count(tail, acc + 1)
        case _                      => count(tail, acc)
      }
      case Nil       => acc
    }
  }

  protected def countValueAttrs(elements: List[Element]): Int = {
    count(elements, 0)
  }

  final protected def getInitialNs(elements: List[Element]): String = {
    elements.head match {
      case a: Attr => a.ns
      case b: Ref  => b.ns
      case other   => throw ModelError("Unexpected head element: " + other)
    }
  }

  @tailrec
  final protected def getInitialNonGenericNs(elements: List[Element]): String = {
    elements.head match {
      case a: Attr if a.attr == "id"      => getInitialNonGenericNs(elements.tail)
      case a: Attr                        => a.ns
      case b: Ref                         => b.ns
      case Nested(Ref(ns, _, _, _), _)    => ns
      case NestedOpt(Ref(ns, _, _, _), _) => ns
      case other                          => throw ModelError("Unexpected head element: " + other)
    }
  }


  def isRefUpdate(elements: List[Element]) = {
    elements.exists {
      case _: Ref => true
      case _      => false
    }
  }


  @tailrec
  final def getAttrNames(elements: List[Element], attrs: Set[String] = Set.empty[String]): Set[String] = {
    elements match {
      case element :: tail => element match {
        case a: Attr          => getAttrNames(tail, attrs + a.name)
        case Nested(_, es)    => getAttrNames(tail ++ es, attrs)
        case NestedOpt(_, es) => getAttrNames(tail ++ es, attrs)
        case _                => getAttrNames(tail, attrs)
      }
      case Nil             => attrs
    }
  }
}
