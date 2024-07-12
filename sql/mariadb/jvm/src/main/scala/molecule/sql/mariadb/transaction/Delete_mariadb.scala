package molecule.sql.mariadb.transaction

import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveDelete
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.SqlDelete
import molecule.sql.mariadb.query.Model2SqlQuery_mariadb

trait Delete_mariadb extends SqlDelete { self: ResolveDelete =>

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery =
    new Model2SqlQuery_mariadb(elements)
}