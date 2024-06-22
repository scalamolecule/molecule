package molecule.sql.core.transaction

import java.sql.{ResultSet, Statement, PreparedStatement => PS}
import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.ResolveDelete
import molecule.core.transaction.ops.DeleteOps
import molecule.core.util.{MetaModelUtils, ModelUtils}
import molecule.sql.core.query.Model2SqlQuery
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

trait SqlDelete
  extends SqlBase_JVM
    with DeleteOps
    with MetaModelUtils
    with ModelUtils
    with MoleculeLogging { self: ResolveDelete =>

  def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any]


  def getDeleteExecutioner(
    elements: List[Element],
    nsMap: Map[String, MetaNs],
    fkConstraintParam: String,
    fkOff: String,
    fkOn: String,
  ): Option[() => List[Long]] = {
    val refPath = List(getInitialNs(elements))
    resolve(elements, true)
    if (ids.nonEmpty) {
      deleteExecutioner(refPath, nsMap, ids, fkConstraintParam, fkOff, fkOn)
    } else if (filterElements.nonEmpty) {
      ids = getIds
      if (ids.nonEmpty)
        deleteExecutioner(refPath, nsMap, ids, fkConstraintParam, fkOff, fkOn)
      else
        None
    } else {
      None
    }
  }

  private def deleteExecutioner(
    refPath: List[String],
    nsMap: Map[String, MetaNs],
    ids: Seq[Long],
    fkConstraintParam: String,
    fkOff: String,
    fkOn: String,
  ): Option[() => List[Long]] = {
    val tables = getDeleteTables(refPath, nsMap, ids)
    Some(
      () => {
        val s = sqlConn.createStatement()
        //        println(s"-------------------- $fkConstraintParam = $fkOff")
        s.addBatch(s"$fkConstraintParam = $fkOff")
        tables.foreach { t =>
          //          println("  €€€€€€€€€  " + t.stmt)
          s.addBatch(t.stmt)
        }
        s.addBatch(s"$fkConstraintParam = $fkOn")
        s.executeBatch
        s.close()
        ids.toList
      }
    )
  }

  private def getDeleteTables(
    refPath: List[String], nsMap: Map[String, MetaNs], ids: Seq[Long]
  ): List[Table] = {
    val ns = refPath.head

    // Delete join rows matching deleted entities
    val joinTables: List[Table] = nsMap(ns).attrs
      .filter(attr => attr.refNs.nonEmpty && attr.card == CardSet && !attr.options.contains("owner"))
      .map { metaAttr =>
        val refNs     = metaAttr.refNs.get
        val joinTable = ss(ns, metaAttr.attr, refNs)
        val idCol     = ss(ns, if (ns == refNs) "1_id" else "id")
        prepareTable(refPath, joinTable, idCol, ids)
      }.toList

    // Recursively delete owned entities
    val ownedTables = deleteOwned(refPath, nsMap, Seq(nsMap(ns)), Set.empty[String], Nil, ids)

    val table = prepareTable(refPath, ns, s"$ns.id", ids)
    joinTables ++ ownedTables.toList ++ List(table)
  }


  def getDeleteDataForInspection(elements: List[Element], nsMap: Map[String, MetaNs]): Data = {
    val refPath = List(getInitialNs(elements))
    resolve(elements, true)
    if (ids.nonEmpty) {
      inspectionTableDataForDeletion(refPath, nsMap, ids)
    } else if (filterElements.nonEmpty) {
      ids = getIds
      if (ids.nonEmpty)
        inspectionTableDataForDeletion(refPath, nsMap, ids)
      else
        (Nil, Nil)
    } else {
      (Nil, Nil)
    }
  }

  private def inspectionTableDataForDeletion(
    refPath: List[String], nsMap: Map[String, MetaNs], ids: Seq[Long]
  ): Data = {
    val ns = refPath.head

    // Delete join rows matching deleted entities
    val joinTables: List[Table] = nsMap(ns).attrs
      .filter(attr => attr.refNs.nonEmpty && attr.card == CardSet && !attr.options.contains("owner"))
      .map { metaAttr =>
        val refNs     = metaAttr.refNs.get
        val joinTable = ss(ns, metaAttr.attr, refNs)
        val idCol     = ss(ns, if (ns == refNs) "1_id" else "id")
        prepareTable(refPath, joinTable, idCol, ids)
      }.toList

    // Recursively delete owned entities
    val ownedTables  = deleteOwned(refPath, nsMap, Seq(nsMap(ns)), Set.empty[String], Nil, ids)
    val table: Table = prepareTable(refPath, ns, s"$ns.id", ids)
    (joinTables ++ ownedTables ++ List(table), Nil)
  }


  private def getIds: List[Long] = {
    val ns                    = getInitialNs(filterElements)
    val filterElementsWithIds = AttrOneManID(ns, "id", V) +: filterElements
    val query                 = model2SqlQuery(filterElementsWithIds).getSqlQuery(Nil, None, None, None)
    val ps                    = sqlConn.prepareStatement(
      query,
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_READ_ONLY,
      Statement.RETURN_GENERATED_KEYS
    )
    val resultSet             = ps.executeQuery()
    val ids                   = ListBuffer.empty[Long]
    while (resultSet.next()) {
      ids += resultSet.getLong(1)
    }
    ids.toList
  }


  private def prepareTable(
    refPath: List[String], table: String, idColumn: String, ids: Seq[Long]
  ): Table = {
    val ids_       = ids.mkString(", ")
    val stmt       = s"DELETE FROM $table WHERE $idColumn IN ($ids_)"
    val populatePS = (ps: PS, _: IdsMap, _: RowIndex) => {
      ps.addBatch()
    }
    Table(refPath, stmt, populatePS)
  }


  @tailrec
  private def deleteOwned(
    refPath: List[String],
    nsMap: Map[String, MetaNs],
    metaNss: Seq[MetaNs],
    processedNss: Set[String],
    ownedTables: Seq[Table],
    nsIds: Seq[Long],
  ): Seq[Table] = {
    metaNss match {
      case Nil              => ownedTables
      case metaNs :: nsTail =>
        val ns            = metaNs.ns
        val processedNss1 = processedNss + ns
        metaNs.attrs match {
          case Nil => deleteOwned(refPath, nsMap, nsTail, processedNss1, ownedTables, nsIds)

          case metaAttr :: attrTail if metaAttr.options.contains("owner") =>
            val refNs     = metaAttr.refNs.get
            val refAttr   = metaAttr.attr
            val refMetaNs = nsMap(refNs)

            metaAttr.card match {
              case _: CardOne =>
                val refIds       = if (nsIds.isEmpty) Nil else {
                  val stmt =
                    s"""SELECT $refNs.id
                       |FROM $refNs
                       |INNER JOIN $ns AS _ns on _ns.$refAttr = $refNs.id
                       |WHERE _ns.id in (${nsIds.mkString(", ")})
                       |""".stripMargin


                  val resultSet = sqlConn.createStatement().executeQuery(stmt)
                  var refIds    = List.empty[Long]
                  while (resultSet.next()) {
                    refIds = refIds :+ resultSet.getLong(1)
                  }
                  //                  println("------------ 1  " + metaNss.map(_.ns))
                  //                  print(stmt)
                  //                  println(refIds)
                  //                  println("")
                  refIds
                }
                val ownedTables1 = if (refIds.nonEmpty)
                  Seq(prepareTable(refPath, refNs, "id", refIds))
                else
                  Nil


                val x = if (processedNss1.contains(refNs)) {
                  List.empty[MetaNs]
                } else {
                  List.empty[MetaNs]
                }

                val nsToCheck: Seq[MetaNs] = (MetaNs(ns, attrTail) +: nsTail) ++ (
                  if (processedNss1.contains(refNs)) Nil else List(refMetaNs))

                deleteOwned(
                  refPath,
                  nsMap,
                  nsToCheck,
                  processedNss1,
                  ownedTables ++ ownedTables1,
                  nsIds
                )

              case _: CardSet =>
                val joinTable         = ss(ns, metaAttr.attr, refNs)
                val (ns_id, refNs_id) = if (ns == refNs) {
                  (ss(ns, "1_id"), ss(refNs, "2_id"))
                } else {
                  (ss(ns, "id"), ss(refNs, "id"))
                }
                val refIds            = if (nsIds.isEmpty) Nil else {
                  val stmt      =
                    s"""SELECT $joinTable.$refNs_id
                       |FROM $joinTable
                       |INNER JOIN $ns AS _ns on _ns.id = $joinTable.$ns_id
                       |WHERE _ns.id in (${nsIds.mkString(", ")})
                       |""".stripMargin
                  val resultSet = sqlConn.createStatement().executeQuery(stmt)
                  var refIds    = List.empty[Long]
                  while (resultSet.next()) {
                    refIds = refIds :+ resultSet.getLong(1)
                  }
                  //                  println("------------ 2 ------------  " + metaNss.map(_.ns))
                  //                  print(stmt)
                  //                  println(refIds)
                  //                  println("")
                  refIds
                }
                val ownedTables1      = if (refIds.isEmpty) Nil else {
                  Seq(
                    prepareTable(refPath, joinTable, ss(ns, "id"), nsIds),
                    prepareTable(refPath, refNs, "id", refIds)
                  )
                }

                val nsToCheck: Seq[MetaNs] = (MetaNs(ns, attrTail) +: nsTail) ++ (
                  if (processedNss1.contains(refNs)) Nil else List(refMetaNs))

                deleteOwned(
                  refPath,
                  nsMap,
                  nsToCheck,
                  processedNss1,
                  ownedTables ++ ownedTables1,
                  nsIds)

              case _: CardSeq => ???
              case _: CardMap => ???
            }

          case _ :: tail => deleteOwned(
            refPath,
            nsMap,
            List(MetaNs(ns, tail)),
            processedNss1,
            ownedTables,
            nsIds
          )
        }
    }
  }


  override def addIds(ids1: Seq[String]): Unit = {
    ids = ids ++ ids1.map(_.toLong)
  }

  override def addFilterElement(element: Element): Unit = {
    filterElements = filterElements :+ element
  }
}