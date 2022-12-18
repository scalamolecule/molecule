package molecule.db.datomic.transaction

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.db.datomic.query.DatomicModel2Query
import scala.annotation.tailrec

class DeleteStmts(
  uniqueAttrs: List[String],
  elements: Seq[Element],
  isMultiple: Boolean
) extends DatomicTransactionBase(elements) {

  def resolve: (Seq[AnyRef], Option[String], Seq[AnyRef]) = {
    val (eids, filterElements) = extract(elements, Nil, Nil, true)

    if (!isMultiple && eids.length > 1)
      multipleModifierMissing(eids.length)

    val (filterQuery, inputs) = if (eids.isEmpty && filterElements.nonEmpty) {
      val filterElements1 = AttrOneManLong("_Generic", "e", V) +: filterElements
      val (query, inputs) = new DatomicModel2Query[Any](filterElements1).getEidQueryWithInputs
      (Some(query), inputs)
    } else {
      (None, Nil)
    }
    (eids, filterQuery, inputs)
  }


  @tailrec
  final private def extract(
    elements: Seq[Element],
    eids: Seq[AnyRef],
    filterElements: Seq[Element],
    topLevel: Boolean
  ): (Seq[AnyRef], Seq[Element]) = {
    elements match {
      case element :: tail => element match {
        case attr: Attr => attr match {
          case AttrOneTacLong("_Generic", "eids", Appl, eids1, _, _, _, _) =>
            if (eids.nonEmpty)
              throw MoleculeException(s"Can't apply entity ids twice for delete.")
            if (!topLevel)
              throw MoleculeException(
                s"Can only apply entity ids to be deleted at top level/first composite group of molecule.")
            extract(tail, eids1.asInstanceOf[Seq[AnyRef]], filterElements, topLevel)

          case AttrOneTacLong("_Generic", "e", Appl, _, _, _, _, _) => throw MoleculeException(
            "Can't delete by applying entity ids to e_")

          case a if a.ns == "_Generic" => throw MoleculeException(
            s"Generic attributes not allowed in update molecule. Found:\n" + a)

          case _: AttrOneTac        => extract(tail, eids, filterElements :+ attr, topLevel)
          case _                    => throw MoleculeException(
            "Can only filter delete by values applied to tacit card-one attributes. Found:\n" + attr
          )
        }

        case _: Nested    => throw MoleculeException(s"Nested data structure not allowed in delete molecule.")
        case _: NestedOpt => throw MoleculeException(s"Optional nested data structure not allowed in delete molecule.")
        case r: Ref       => extract(tail, eids, filterElements :+ r, false)
        case b: BackRef   => extract(tail, eids, filterElements :+ b, false)

        case Composite(es) =>
          val (eids1, filters1) = extractSubElements(es, topLevel)
          extract(tail, eids ++ eids1, filterElements ++ filters1, false)

        case TxMetaData(es) =>
          val (eids1, filters1) = extractSubElements(es, false)
          extract(tail, eids ++ eids1, filterElements ++ filters1, false)

        case element => unexpected(element)
      }
      case Nil             => (eids, filterElements)
    }
  }

  private def extractSubElements(
    elements: Seq[Element],
    topLevel: Boolean
  ): (Seq[AnyRef], Seq[Element]) = {
    extract(elements, Nil, Nil, topLevel)
  }
}