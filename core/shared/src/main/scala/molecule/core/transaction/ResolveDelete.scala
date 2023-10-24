package molecule.core.transaction

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ops.DeleteOps
import scala.annotation.tailrec

class ResolveDelete { self: DeleteOps =>

  @tailrec
  final def resolve(elements: List[Element], topLevel: Boolean): Unit = {
    elements match {
      case element :: tail => element match {
        case a: Attr => a match {
          case AttrOneTacID(_, "id", Eq, ids1, _, _, _, _, _, _, _) =>
            if (!topLevel)
              throw ModelError(
                s"Can only apply entity ids to be deleted to the initial namespace."
              )
            addIds(ids1.map(_.toLong))
            resolve(tail, topLevel)

          case a if a.attr == "id" => throw ModelError(
            s"Generic id attribute not allowed in delete molecule. Found:\n" + a)

          case _: AttrOneTac =>
            addFilterElement(element)
            resolve(tail, topLevel)

          case _ => throw ModelError(
            "Can only filter delete by values applied to tacit card-one attributes. Found:\n" + a
          )
        }

        case _: Nested    => throw ModelError(s"Nested data structure not allowed in delete molecule.")
        case _: NestedOpt => throw ModelError(s"Optional nested data structure not allowed in delete molecule.")
        case _            => addFilterElement(element); resolve(tail, false)
      }
      case Nil             => ()
    }
  }
}