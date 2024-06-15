package molecule.sql.h2.transaction

import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveUpdate
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.SqlUpdate
import molecule.sql.h2.query.Model2SqlQuery_h2

trait Update_h2 extends SqlUpdate { self: ResolveUpdate =>

  //  doPrint = false
  doPrint = true

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any] =
    new Model2SqlQuery_h2[Any](elements)
}