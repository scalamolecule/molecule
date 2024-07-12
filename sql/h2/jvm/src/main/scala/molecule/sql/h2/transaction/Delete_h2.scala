package molecule.sql.h2.transaction

import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveDelete
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.SqlDelete
import molecule.sql.h2.query.Model2SqlQuery_h2

trait Delete_h2 extends SqlDelete { self: ResolveDelete =>

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery =
    new Model2SqlQuery_h2(elements)
}