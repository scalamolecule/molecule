package molecule.core.transaction

import molecule.base.error.*
import molecule.core.ast.DataModel.*
import molecule.core.transaction.ops.DeleteOps
import scala.annotation.tailrec

trait ResolveDelete { self: DeleteOps =>

  @tailrec
  final def resolve(elements: List[Element], topLevel: Boolean): Unit = {
    elements match {
      case element :: tail => element match {
        case a: Attr => a match {
          case AttrOneTacID(_, "id", Eq, ids1, _, _, _, _, _, _, _) =>
            if (!topLevel)
              throw ModelError(
                s"Can only apply entity ids to be deleted to the initial entity."
              )
            addIds(ids1)
            resolve(tail, topLevel)

          case a if a.attr == "id" => throw ModelError(
            s"Mandatory id attribute not allowed in delete molecule (${a.name}).")

          case _: AttrOneTac =>
            addFilterElement(element)
            resolve(tail, topLevel)

          case _ => throw ModelError(
            s"Can only filter delete by values applied to tacit card-one attributes (${a.name})."
          )
        }

        case _: Nested    => throw ModelError(s"Nested data structure not allowed in delete molecule.")
        case _: OptNested => throw ModelError(s"Optional nested data structure not allowed in delete molecule.")
        case _            => // Ref, OptRef
          addFilterElement(element)
          resolve(tail, false)
      }
      case Nil             => ()
    }
  }
}