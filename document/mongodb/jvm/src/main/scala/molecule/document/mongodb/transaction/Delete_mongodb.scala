package molecule.document.mongodb.transaction

import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveDelete
import molecule.core.transaction.ops.DeleteOps
import molecule.core.util.{MetaModelUtils, ModelUtils}
import org.bson.conversions.Bson
import org.bson.types.ObjectId
import org.bson.{BsonArray, BsonDocument, BsonObjectId, BsonString}

trait Delete_mongodb
  extends Base_JVM_mongodb
    with DeleteOps
    with MetaModelUtils
    with ModelUtils
    with MoleculeLogging { self: ResolveDelete =>


  def getFilter(elements: List[Element], nsMap: Map[String, MetaNs]): Option[Bson] = {
    val refPath = List(getInitialNs(elements))
    resolve(elements, true)
    //    println("ids: " + ids)
    if (ids.nonEmpty) {
      Some(deleteTableData(refPath, nsMap, ids))
    } else if (filterElements.nonEmpty) {
      ids = getIds
      if (ids.nonEmpty)
        Some(deleteTableData(refPath, nsMap, ids))
      else {
        None
      }
    } else {
      None
    }
  }


  protected def deleteTableData(refPath: List[String], nsMap: Map[String, MetaNs], ids: Seq[String]): Bson = {
    //    val ns = refPath.head
    //    // Delete join rows matching deleted entities
    //    val joinTables: List[Table] = nsMap(ns).attrs
    //      .filter(attr => attr.refNs.nonEmpty && attr.card == CardSet && !attr.options.contains("owner"))
    //      .map { metaAttr =>
    //        val refNs     = metaAttr.refNs.get
    //        val joinTable = ss(ns, metaAttr.attr, refNs)
    //        val idCol     = ss(ns, if (ns == refNs) "1_id" else "id")
    //        prepareTable(refPath, joinTable, idCol, ids)
    //      }.toList
    //
    //    // Recursively delete owned entities
    //    val ownedTables = deleteOwned(refPath, nsMap, Seq(nsMap(ns)), Nil, ids)
    //
    //    val table: Table = prepareTable(refPath, ns, s"$ns.id", ids)
    //    ((table +: joinTables) ++ ownedTables, Nil)

    val idArray = new BsonArray()
//    ids.map(id => idArray.add(new BsonObjectId(new ObjectId(id))))
    ids.map(id => idArray.add(new BsonString(id)))
    new BsonDocument().append("_id", new BsonDocument("$in", idArray))
  }


  protected def getIds: List[String] = {
    //    val ns                    = getInitialNs(filterElements)
    //    val filterElementsWithIds = AttrOneManLong(ns, "id", V) +: filterElements
    //    val query                 = model2SqlQuery(filterElementsWithIds).getSqlQuery(Nil, None, None, None)
    //    val ps                    = sqlConn.prepareStatement(
    //      query,
    //      ResultSet.TYPE_SCROLL_INSENSITIVE,
    //      ResultSet.CONCUR_READ_ONLY,
    //      Statement.RETURN_GENERATED_KEYS
    //    )
    //    val resultSet             = ps.executeQuery()
    //    val ids                   = ListBuffer.empty[Long]
    //    while (resultSet.next()) {
    //      ids += resultSet.getLong(1)
    //    }
    ids.toList
    ???
  }


  //  protected def prepareTable(refPath: List[String], table: String, idColumn: String, ids: Seq[Long]): Table = {
  //    val ids_       = ids.mkString(", ")
  //    val stmt       = s"DELETE FROM $table WHERE $idColumn IN ($ids_)"
  //    val ps         = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
  //    val populatePS = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
  //    Table(refPath, stmt, ps, populatePS)
  //  }
  //
  //
  //  @tailrec
  //  private def deleteOwned(
  //    refPath: List[String],
  //    nsMap: Map[String, MetaNs],
  //    metaNs: Seq[MetaNs],
  //    ownedTables: Seq[Table],
  //    nsIds: Seq[Long],
  //  ): Seq[Table] = {
  //    metaNs match {
  //      case Nil              => ownedTables
  //      case metaNs :: nsTail =>
  //        val ns = metaNs.ns
  //        metaNs.attrs match {
  //          case Nil => deleteOwned(refPath, nsMap, nsTail, ownedTables, nsIds)
  //
  //          case metaAttr :: attrTail if metaAttr.options.contains("owner") =>
  //            val refNs     = metaAttr.refNs.get
  //            val refAttr   = metaAttr.attr
  //            val refMetaNs = nsMap(refNs)
  //
  //            metaAttr.card match {
  //              case _: CardOne =>
  //                val refIds       = if (nsIds.isEmpty) Nil else {
  //                  val stmt      =
  //                    s"""SELECT $refNs.id
  //                       |FROM $refNs
  //                       |INNER JOIN $ns on $ns.$refAttr = $refNs.id
  //                       |WHERE $ns.id in (${nsIds.mkString(", ")})
  //                       |""".stripMargin
  //                  val resultSet = sqlConn.createStatement().executeQuery(stmt)
  //                  var refIds    = List.empty[Long]
  //                  while (resultSet.next()) {
  //                    refIds = refIds :+ resultSet.getLong(1)
  //                  }
  //                  refIds
  //                }
  //                val ownedTables1 = if (refIds.nonEmpty) Seq(prepareTable(refPath, refNs, "id", refIds)) else Nil
  //                deleteOwned(
  //                  refPath,
  //                  nsMap,
  //                  MetaNs(ns, attrTail) +: refMetaNs +: nsTail,
  //                  ownedTables ++ ownedTables1,
  //                  nsIds
  //                )
  //
  //              case _: CardSet =>
  //                val joinTable    = ss(ns, metaAttr.attr, refNs)
  //                val refNs_id     = ss(refNs, "id")
  //                val ns_id        = ss(ns, "id")
  //                val refIds       = if (nsIds.isEmpty) Nil else {
  //                  val stmt      =
  //                    s"""SELECT $joinTable.$refNs_id
  //                       |FROM $joinTable
  //                       |INNER JOIN $ns on $ns.id = $joinTable.$ns_id
  //                       |WHERE $ns.id in (${nsIds.mkString(", ")})
  //                       |""".stripMargin
  //                  val resultSet = sqlConn.createStatement().executeQuery(stmt)
  //                  var refIds    = List.empty[Long]
  //                  while (resultSet.next()) {
  //                    refIds = refIds :+ resultSet.getLong(1)
  //                  }
  //                  refIds
  //                }
  //                val ownedTables1 = if (refIds.isEmpty) Nil else {
  //                  Seq(
  //                    prepareTable(refPath, joinTable, ss(ns, "id"), nsIds),
  //                    prepareTable(refPath, refNs, "id", refIds)
  //                  )
  //                }
  //                deleteOwned(
  //                  refPath,
  //                  nsMap,
  //                  MetaNs(ns, attrTail) +: refMetaNs +: nsTail,
  //                  ownedTables ++ ownedTables1,
  //                  nsIds)
  //            }
  //
  //          case _ :: tail => deleteOwned(refPath, nsMap, List(MetaNs(ns, tail)), ownedTables, nsIds)
  //        }
  //    }
  //  }


  override def addIds(ids1: Seq[String]): Unit = {
    ids = ids ++ ids1
  }

  override def addFilterElement(element: Element): Unit = {
    //    filterElements = filterElements :+ element
    ???
  }
}