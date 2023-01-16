package molecule.core.transaction

import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec

class Delete { //self: DeleteOps =>

  @tailrec
  final protected def resolve(
    elements: List[Element],
    eids: Seq[AnyRef],
    filterElements: List[Element],
    topLevel: Boolean
  ): (Seq[AnyRef], List[Element]) = {
    elements match {
      case element :: tail => element match {
        case attr: Attr => attr match {
          case AttrOneTacLong("_Generic", "eids", Appl, eids1, _, _, _, _) =>
            if (eids.nonEmpty)
              throw MoleculeError(s"Can't apply entity ids twice for delete.")
            if (!topLevel)
              throw MoleculeError(
                s"Can only apply entity ids to be deleted at top level/first composite group of molecule.")
            resolve(tail, eids1.asInstanceOf[Seq[AnyRef]], filterElements, topLevel)

          case AttrOneTacLong("_Generic", "e", Appl, _, _, _, _, _) => throw MoleculeError(
            "Can't delete by applying entity ids to e_")

          case a if a.ns == "_Generic" => throw MoleculeError(
            s"Generic attributes not allowed in update molecule. Found:\n" + a)

          case _: AttrOneTac => resolve(tail, eids, filterElements :+ attr, topLevel)
          case _             => throw MoleculeError(
            "Can only filter delete by values applied to tacit card-one attributes. Found:\n" + attr
          )
        }

        case _: Nested    => throw MoleculeError(s"Nested data structure not allowed in delete molecule.")
        case _: NestedOpt => throw MoleculeError(s"Optional nested data structure not allowed in delete molecule.")
        case r: Ref       => resolve(tail, eids, filterElements :+ r, false)
        case b: BackRef   => resolve(tail, eids, filterElements :+ b, false)

        case Composite(es) =>
          val (eids1, filters1) = extractSubElements(es, topLevel)
          resolve(tail, eids ++ eids1, filterElements ++ filters1, false)

        case TxMetaData(es) =>
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