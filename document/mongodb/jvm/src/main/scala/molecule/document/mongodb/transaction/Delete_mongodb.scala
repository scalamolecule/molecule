package molecule.document.mongodb.transaction

import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveDelete
import molecule.document.mongodb.query.Model2SqlQuery
import molecule.document.mongodb.transaction.SqlDelete
import molecule.document.mongodb.query.Model2SqlQuery_mongodb

trait Delete_mongodb extends SqlDelete { self: ResolveDelete =>

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any] =
    new Model2SqlQuery_mongodb[Any](elements)
}