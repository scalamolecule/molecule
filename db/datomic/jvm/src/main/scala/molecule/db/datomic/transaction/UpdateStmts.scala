package molecule.db.datomic.transaction

import java.util.{Collections, List => jList}
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.db.datomic.query.DatomicModel2Query
import scala.annotation.tailrec


class UpdateStmts(
  uniqueAttrs: List[String],
  elements: Seq[Element],
  isMultiple: Boolean,
) extends DatomicTransactionBase(elements) {

  def getStmts: (Seq[Any], Option[String], Seq[AnyRef], jList[jList[AnyRef]]) = {
    println("\n--- UPDATE --------------")
    elements.foreach(println)
    checkConflictingAttributes(elements)

    val (eids, filterElements, dataElements) = separate(elements, Nil, Nil, Nil, uniqueAttrs)

    if (!isMultiple && eids.length > 1)
      throw MoleculeException("Please provide explicit `update.multiple` to update multiple entities.")

    val (idQuery, inputs) = if (eids.isEmpty && filterElements.nonEmpty) {
      val filterElements1 = AttrOneManLong("Generic", "e", V) +: filterElements
      val (query, inputs) = new DatomicModel2Query[Any](filterElements1).getEidQueryWithInputs
      (Some(query), inputs)
    } else {
      (None, Nil)
    }

    val dataStmts = new SaveStmts(dataElements, debug = false).getRawStmts("eid to be replaced")
    (eids, idQuery, inputs, dataStmts)
  }


  // Separate eids and filter/data attributes
  @tailrec
  final protected def separate(
    elements: Seq[Element],
    eids: Seq[Any],
    filter: Seq[Element],
    data: Seq[Element],
    uniqueAttrs: List[String]
  ): (Seq[Any], Seq[Element], Seq[Element]) = {
    elements match {
      case element :: tail => element match {
        case attr: Attr =>
          if (attr.op != Appl) {
            throw MoleculeException("Can't update attributes without an applied value. Found:\n" + attr)
          }
          attr match {
            case attr: AttrOneTac => attr match {
              case AttrOneTacLong("Generic", "e", _, eids1, _, _, _) =>
                if (eids.nonEmpty)
                  throw MoleculeException("Can't apply entity ids twice in update.")
                separate(tail, eids1, filter, data, uniqueAttrs)

              case a if uniqueAttrs.contains(a.ns + "." + a.attr) =>
                if (eids.nonEmpty)
                  throw MoleculeException("Can only apply one unique attribute value for update. Found:\n" + a)
                val at        = s":${a.ns}/${a.attr}"
                val lookupRef = attr match {
                  case AttrOneTacString(_, _, _, Seq(v), _, _, _)     => datomic.Util.list(at, v)
                  case AttrOneTacInt(_, _, _, Seq(v), _, _, _)        => datomic.Util.list(at, v)
                  case AttrOneTacLong(_, _, _, Seq(v), _, _, _)       => datomic.Util.list(at, v)
                  case AttrOneTacFloat(_, _, _, Seq(v), _, _, _)      => datomic.Util.list(at, v)
                  case AttrOneTacDouble(_, _, _, Seq(v), _, _, _)     => datomic.Util.list(at, v)
                  case AttrOneTacBoolean(_, _, _, Seq(v), _, _, _)    => datomic.Util.list(at, v)
                  case AttrOneTacBigInt(_, _, _, Seq(v), _, _, _)     => datomic.Util.list(at, v)
                  case AttrOneTacBigDecimal(_, _, _, Seq(v), _, _, _) => datomic.Util.list(at, v)
                  case AttrOneTacDate(_, _, _, Seq(v), _, _, _)       => datomic.Util.list(at, v)
                  case AttrOneTacUUID(_, _, _, Seq(v), _, _, _)       => datomic.Util.list(at, v)
                  case AttrOneTacURI(_, _, _, Seq(v), _, _, _)        => datomic.Util.list(at, v)
                  case AttrOneTacByte(_, _, _, Seq(v), _, _, _)       => datomic.Util.list(at, v)
                  case AttrOneTacShort(_, _, _, Seq(v), _, _, _)      => datomic.Util.list(at, v)
                  case AttrOneTacChar(_, _, _, Seq(v), _, _, _)       => datomic.Util.list(at, v)
                  case _                                              =>
                    val an = a.ns + "." + a.attr + "_"
                    throw MoleculeException(
                      s"Can only update with one applied value for tacit attribute `$an`. Found:\n" + a
                    )
                }
                separate(tail, eids :+ lookupRef, filter, data, uniqueAttrs)

              case _ => separate(tail, eids, filter :+ attr, data, uniqueAttrs)
            }
            case attr             => separate(tail, eids, filter, data :+ attr, uniqueAttrs)
          }

        case _: Nested     => throw MoleculeException("Nested data structure not allowed in update molecule.")
        case _: NestedOpt  => throw MoleculeException("Optional nested data structure not allowed in update molecule.")
        case r: Ref        => separate(tail, eids, filter :+ r, data :+ r, uniqueAttrs)
        case b: BackRef    => separate(tail, eids, filter :+ b, data :+ b, uniqueAttrs)
        case Composite(es) =>
          val (eids1, filters1, data1) = separateSubElements(es)
          separate(tail, eids1, filter ++ filters1, data ++ data1, uniqueAttrs)

        case TxMetaData(es) =>
          val (eids1, filters1, data1) = separateSubElements(es)
          separate(tail, eids1, filter ++ filters1, data ++ data1, uniqueAttrs)

        case element => unexpected(element)
      }
      case Nil             => (eids, filter, data)
    }
  }

  private def separateSubElements(elements: Seq[Element]): (Seq[Any], Seq[Element], Seq[Element]) = {
    separate(elements, Nil, Nil, Nil, Nil)
  }


}