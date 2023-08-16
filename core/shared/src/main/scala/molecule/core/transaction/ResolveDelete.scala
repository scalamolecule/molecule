package molecule.core.transaction

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec

class ResolveDelete {

  @tailrec
  final def resolve(
    elements: List[Element],
    ids: Seq[AnyRef],
    filterElements: List[Element],
    topLevel: Boolean
  ): (Seq[AnyRef], List[Element]) = {
    elements match {
      case element :: tail => element match {
        case attr: Attr => attr match {
          case AttrOneTacLong(_, "id", Eq, ids1, _, _, _, _, _, _) =>
            if (ids.nonEmpty)
              throw ExecutionError(s"Can't apply entity ids twice for delete.")
            if (!topLevel)
              throw ModelError(
                s"Can only apply entity ids to be deleted at top level/first composite group of molecule.")
            resolve(tail, ids1.asInstanceOf[Seq[AnyRef]], filterElements, topLevel)

//          case AttrOneTacLong(_, "id", Eq, _, _, _, _, _, _, _) => throw ModelError(
//            "Can't delete by applying entity ids to id_")

//          case a if a.ns == "_Generic" => throw ModelError(
          case a if a.attr == "id" || a.attr == "tx" => throw ModelError(
            s"Generic attributes not allowed in update molecule. Found:\n" + a)

          case _: AttrOneTac => resolve(tail, ids, filterElements :+ attr, topLevel)
          case _             => throw ModelError(
            "Can only filter delete by values applied to tacit card-one attributes. Found:\n" + attr
          )
        }

        case _: Nested    => throw ModelError(s"Nested data structure not allowed in delete molecule.")
        case _: NestedOpt => throw ModelError(s"Optional nested data structure not allowed in delete molecule.")
        case r: Ref       => resolve(tail, ids, filterElements :+ r, false)
        case b: BackRef   => resolve(tail, ids, filterElements :+ b, false)

        case Composite(es) =>
          val (ids1, filters1) = extractSubElements(es, topLevel)
          resolve(tail, ids ++ ids1, filterElements ++ filters1, false)

        case TxMetaData(es) =>
          val (ids1, filters1) = extractSubElements(es, false)
          resolve(tail, ids ++ ids1, filterElements ++ filters1, false)
      }
      case Nil             => (ids, filterElements)
    }
  }

  private def extractSubElements(
    elements: List[Element],
    topLevel: Boolean
  ): (Seq[AnyRef], List[Element]) = {
    resolve(elements, Nil, Nil, topLevel)
  }
}