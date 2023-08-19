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
        case attr: Attr => attr match {
          case AttrOneTacLong(_, "id", Eq, ids1, _, _, _, _, _, _) =>
            if (!topLevel)
              throw ModelError(
                s"Can only apply entity ids to be deleted at top level/first composite group of molecule."
              )
            addIds(ids1)
            resolve(tail, topLevel)

          case a if a.attr == "id" || a.attr == "tx" => throw ModelError(
            s"Generic attributes not allowed in delete molecule. Found:\n" + a)

          case _: AttrOneTac =>
            addFilterElements(Seq(element))
            resolve(tail, topLevel)

          case _ => throw ModelError(
            "Can only filter delete by values applied to tacit card-one attributes. Found:\n" + attr
          )
        }

        case _: Nested      => throw ModelError(s"Nested data structure not allowed in delete molecule.")
        case _: NestedOpt   => throw ModelError(s"Optional nested data structure not allowed in delete molecule.")
        case r: Ref         => addFilterElements(Seq(r)); resolve(tail, false)
        case b: BackRef     => addFilterElements(Seq(b)); resolve(tail, false)
        case Composite(es)  => resolveSubElements(es, topLevel); resolve(tail, false)
        case TxMetaData(es) => resolveSubElements(es, false); resolve(tail, false)
      }
      case Nil             => ()
    }
  }

  private def resolveSubElements(
    elements: List[Element],
    topLevel: Boolean
  ): Unit = {
    resolve(elements, topLevel)
  }
}