package molecule.db.datomic.transaction

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.{Delete, Delete2Data}
import molecule.db.datomic.query.DatomicModel2Query

trait Delete_stmts extends Delete2Data { self: Delete =>

  def getStmtsData(
    elements: Seq[Element],
    isMultiple: Boolean
  ): (Seq[AnyRef], Option[String], Seq[AnyRef]) = {

    val (eids, filterElements) = resolve(elements, Nil, Nil, true)

    if (!isMultiple && eids.length > 1)
      throw MoleculeException(
        s"Please provide explicit `delete.multiple` to delete multiple entities " +
          s"(found ${eids.length} matching entities)."
      )

    val (filterQuery, inputs) = if (eids.isEmpty && filterElements.nonEmpty) {
      val filterElements1 = AttrOneManLong("_Generic", "e", V) +: filterElements
      val (query, inputs) = new DatomicModel2Query[Any](filterElements1).getEidQueryWithInputs
      (Some(query), inputs)
    } else {
      (None, Nil)
    }
    (eids, filterQuery, inputs)
  }
}