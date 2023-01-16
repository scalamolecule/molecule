package molecule.core.validation

import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec

object CheckConflictingAttrs {
  private def dup(element: String) = throw MoleculeError(s"Can't transact duplicate attribute `$element`.")

  @tailrec
  final def apply(
    elements: List[Element],
    prev: Array[Array[Array[String]]] = Array(Array(Array.empty[String])),
    level: Int = 0,
    group: Int = 0,
    refPath: Seq[String] = Seq(""),
    distinguishMode: Boolean = false
  ): Unit = {
    elements match {
      case head :: tail => head match {
        case a: Attr =>
          val attr         = a.ns + "." + a.attr
          // Distinguish multiple ref paths to the same namespace
          val attrPrefixed = if (distinguishMode) {
            val mode = a match {
              case _: AttrOneMan => "man"
              case _: AttrOneOpt => "opt"
              case _: AttrOneTac => "tac"
              case _: AttrSetMan => "man"
              case _: AttrSetOpt => "opt"
              case _: AttrSetTac => "tac"
            }
            refPath.mkString("-") + "-" + attr + "-" + mode
          } else {
            refPath.mkString("-") + "-" + attr
          }
          if (prev(level)(group).contains(attrPrefixed))
            dup(attr)
          prev(level)(group) = prev(level)(group) :+ attrPrefixed
          apply(tail, prev, level, group, refPath, distinguishMode)

        case r: Ref =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref)) {
            dup(ref)
          }
          if (refPath.contains(ref)) {
            dup(ref)
          }
          prev(level) = prev(level) :+ Array(ref)
          apply(tail, prev, level, group + 1, refPath :+ ref, distinguishMode)

        case _: BackRef =>
          if (group == 0)
            throw MoleculeError("Can't use backref from here.")
          apply(tail, prev, level, group - 1, refPath.init, distinguishMode)

        case Composite(es) =>
          apply(es ++ tail, prev, level, group, Seq(""), distinguishMode)

        case Nested(r, es) =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref))
            dup(ref)
          val prev1 = prev :+ Array(Array(ref))
          apply(es ++ tail, prev1, level + 1, 0, refPath, distinguishMode)

        case NestedOpt(r, es) =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref))
            dup(ref)
          val prev1 = prev :+ Array(Array(ref))
          apply(es ++ tail, prev1, level + 1, 0, refPath, distinguishMode)

        case TxMetaData(txElements) =>
          val prev1 = prev :+ Array(Array.empty[String])
          apply(txElements, prev1, level + 1, 0, Nil, distinguishMode)
      }
      case Nil          => ()
    }
  }
}
