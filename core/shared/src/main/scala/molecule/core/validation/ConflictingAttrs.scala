package molecule.core.validation

import molecule.base.error.ExecutionError
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec

object ConflictingAttrs {
  private def dup(element: String) = throw ExecutionError(s"Can't transact duplicate attribute `$element`.")

  @tailrec
  final def check(
    elements: List[Element],
    prev: Array[Array[Array[String]]] = Array(Array(Array.empty[String])),
    level: Int = 0,
    group: Int = 0,
    refPath: Seq[String] = Seq(""),
    distinguishMode: Boolean = false,
    validationErrors: Map[String, Seq[String]] = Map.empty[String, Seq[String]]
  ): Map[String, Seq[String]] = {
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

          val validationErrors1 = if (a.errors.isEmpty) validationErrors else {
            validationErrors.+(attr -> a.errors)
          }
          check(tail, prev, level, group, refPath, distinguishMode, validationErrors1)

        case r: Ref =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref)) {
            dup(ref)
          }
          if (refPath.contains(ref)) {
            dup(ref)
          }
          prev(level) = prev(level) :+ Array(ref)
          check(tail, prev, level, group + 1, refPath :+ ref, distinguishMode, validationErrors)

        case _: BackRef =>
          if (group == 0)
            throw ExecutionError("Can't use backref from here.")
          check(tail, prev, level, group - 1, refPath.init, distinguishMode, validationErrors)

        case Composite(es) =>
          check(es ++ tail, prev, level, group, Seq(""), distinguishMode, validationErrors)

        case Nested(r, es) =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref))
            dup(ref)
          val prev1 = prev :+ Array(Array(ref))
          check(es ++ tail, prev1, level + 1, 0, refPath, distinguishMode, validationErrors)

        case NestedOpt(r, es) =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref))
            dup(ref)
          val prev1 = prev :+ Array(Array(ref))
          check(es ++ tail, prev1, level + 1, 0, refPath, distinguishMode, validationErrors)

        case TxMetaData(txElements) =>
          val prev1 = prev :+ Array(Array.empty[String])
          check(txElements, prev1, level + 1, 0, Nil, distinguishMode, validationErrors)
      }
      case Nil          => validationErrors
    }
  }
}
