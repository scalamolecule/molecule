package molecule.sql.h2.transaction

import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveUpdate
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.{SqlUpdate, SqlUpdateOLD}
import molecule.sql.h2.query.Model2SqlQuery_h2

trait Update_h2OLD extends SqlUpdateOLD { self: ResolveUpdate =>

  doPrint = false
  //  doPrint = true

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery =
    new Model2SqlQuery_h2(elements)
}