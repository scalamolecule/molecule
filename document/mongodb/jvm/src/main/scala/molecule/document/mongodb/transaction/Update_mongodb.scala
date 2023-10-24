package molecule.document.mongodb.transaction

import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveUpdate
import molecule.document.mongodb.query.Model2SqlQuery
import molecule.document.mongodb.transaction.SqlUpdate
import molecule.document.mongodb.query.Model2SqlQuery_mongodb

trait Update_mongodb extends SqlUpdate { self: ResolveUpdate =>

  doPrint = false

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any] =
    new Model2SqlQuery_mongodb[Any](elements)
}