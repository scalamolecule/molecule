package molecule.document.mongodb.transaction

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.action.Query
import molecule.core.transaction.ResolveDelete
import molecule.core.transaction.ops.DeleteOps
import molecule.core.util.{MetaModelUtils, ModelUtils}
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.query.LambdasOne
import molecule.document.mongodb.spi.SpiSync_mongodb
import org.bson._
import org.bson.types.ObjectId


trait Delete_mongodb
  extends Base_JVM_mongodb
    with DeleteOps
    with LambdasOne
    with MetaModelUtils
    with ModelUtils
    with MoleculeLogging { self: ResolveDelete =>


  def getData(elements: List[Element], conn: MongoConn_JVM): Data = {
    val ns = getInitialNs(elements)
    resolve(elements, true)

    val ids1 = if (optIds.nonEmpty) {
      optIds.get
    } else if (filterElements.nonEmpty) {
      val filterElements1 = AttrOneManID(ns, "id", V) +: filterElements
      SpiSync_mongodb.query_get[String](Query(filterElements1))(conn)
    } else {
      throw ModelError("Missing filters in delete molecule:\n" + elements.mkString("\n"))
    }

    val idArray = new BsonArray()
    ids1.map(id => idArray.add(new BsonObjectId(new ObjectId(id))))
    val filter = new BsonDocument("_id", new BsonDocument("$in", idArray))

    val data = new BsonDocument()
      .append("_action", new BsonString("delete"))
      .append("_ids", idArray)
    data.append(ns, filter)
  }

  override def addIds(ids0: Seq[String]): Unit = {
    optIds = Some(ids0)
  }

  override def addFilterElement(element: Element): Unit = {
    filterElements = filterElements :+ element
  }
}