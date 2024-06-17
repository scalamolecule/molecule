package molecule.sql.sqlite.transaction

import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveDelete
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.SqlDelete
import molecule.sql.sqlite.query.Model2SqlQuery_sqlite

trait Delete_sqlite extends SqlDelete { self: ResolveDelete =>

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any] =
    new Model2SqlQuery_sqlite[Any](elements)
}