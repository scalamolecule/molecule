package molecule.core.transaction

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec

class DeleteExtraction {

  @tailrec
  final def resolve(
    elements: List[Element],
    eids: Seq[AnyRef],
    filterElements: List[Element],
    topLevel: Boolean
  ): (Seq[AnyRef], List[Element]) = {
    elements match {
      case element :: tail => element match {
        case attr: Attr => attr match {
          case AttrOneTacLong("_Generic", "eids", Eq, eids1, _, _, _, _, _, _) =>
            if (eids.nonEmpty)
              throw ExecutionError(s"Can't apply entity ids twice for delete.")
            if (!topLevel)
              throw ModelError(
                s"Can only apply entity ids to be deleted at top level/first composite group of molecule.")
            resolve(tail, eids1.asInstanceOf[Seq[AnyRef]], filterElements, topLevel)

          case AttrOneTacLong("_Generic", "eid", Eq, _, _, _, _, _, _, _) => throw ModelError(
            "Can't delete by applying entity ids to eid_")

          case a if a.ns == "_Generic" => throw ModelError(
            s"Generic attributes not allowed in update molecule. Found:\n" + a)

          case _: AttrOneTac => resolve(tail, eids, filterElements :+ attr, topLevel)
          case _             => throw ModelError(
            "Can only filter delete by values applied to tacit card-one attributes. Found:\n" + attr
          )
        }

        case _: Nested    => throw ModelError(s"Nested data structure not allowed in delete molecule.")
        case _: NestedOpt => throw ModelError(s"Optional nested data structure not allowed in delete molecule.")
        case r: Ref       => resolve(tail, eids, filterElements :+ r, false)
        case b: BackRef   => resolve(tail, eids, filterElements :+ b, false)

        case Composite(es) =>
          val (eids1, filters1) = extractSubElements(es, topLevel)
          resolve(tail, eids ++ eids1, filterElements ++ filters1, false)

        case TxData(es) =>
          val (eids1, filters1) = extractSubElements(es, false)
          resolve(tail, eids ++ eids1, filterElements ++ filters1, false)
      }
      case Nil             => (eids, filterElements)
    }
  }

  private def extractSubElements(
    elements: List[Element],
    topLevel: Boolean
  ): (Seq[AnyRef], List[Element]) = {
    resolve(elements, Nil, Nil, topLevel)
  }
}