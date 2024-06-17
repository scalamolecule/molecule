package molecule.sql.sqlite.transaction

import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveUpdate
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.SqlUpdate
import molecule.sql.sqlite.query.Model2SqlQuery_sqlite

trait Update_sqlite extends SqlUpdate { self: ResolveUpdate =>

  //  doPrint = false
  doPrint = true

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any] =
    new Model2SqlQuery_sqlite[Any](elements)
}