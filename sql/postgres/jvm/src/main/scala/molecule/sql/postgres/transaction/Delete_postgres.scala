package molecule.sql.postgres.transaction

import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveDelete
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.SqlDelete
import molecule.sql.postgres.query.Model2SqlQuery_postgres

trait Delete_postgres extends SqlDelete { self: ResolveDelete =>

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any] =
    new Model2SqlQuery_postgres[Any](elements)
}