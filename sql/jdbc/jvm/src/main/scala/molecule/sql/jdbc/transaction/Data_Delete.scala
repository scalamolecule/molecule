package molecule.sql.jdbc.transaction

import java.sql.{PreparedStatement => PS}
import molecule.base.ast.SchemaAST.{CardOne, CardSet, MetaNs}
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveDelete
import molecule.core.transaction.ops.DeleteOps
import scala.annotation.tailrec

trait Data_Delete extends JdbcBase_JVM with DeleteOps with MoleculeLogging { self: ResolveDelete =>

  def getData(elements: List[Element], nsMap: Map[String, MetaNs]): Data = {
    val refPath          = List(getInitialNs(elements))
    val (deleteModel, _) = separateTxElements(elements)

    resolve(deleteModel, true)

    def prepareTable(table: String, idColumn: String, ids: Seq[Long]) = {
      val ids_       = ids.mkString(", ")
      val stmt       = s"DELETE FROM $table WHERE $idColumn IN ($ids_)"
      val ps         = sqlConn.prepareStatement(stmt)
      val populatePS = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
      Table(refPath, stmt, ps, populatePS)
    }

    val baseNs = refPath.head
    val table  = prepareTable(baseNs, s"$baseNs.id", ids)

    // Delete join rows matching deleted entities
    val joinTables = nsMap(baseNs).attrs
      .filter(attr => attr.refNs.nonEmpty && attr.card == CardSet && !attr.options.contains("owner"))
      .map { metaAttr =>
        val refNs     = metaAttr.refNs.get
        val joinTable = s"${baseNs}_${metaAttr.attr}_$refNs"
        val idCol     = baseNs + (if (baseNs == refNs) "_1_id" else "_id")
        prepareTable(joinTable, idCol, ids)
      }.toList

    @tailrec
    def deleteOwned(metaNs: Seq[MetaNs], ownedTables: Seq[Table], nsIds: Seq[Long]): Seq[Table] = {
      metaNs match {
        case Nil              => ownedTables
        case metaNs :: nsTail =>
          val ns = metaNs.ns
          metaNs.attrs match {
            case Nil => deleteOwned(nsTail, ownedTables, nsIds)

            case metaAttr :: attrTail if metaAttr.options.contains("owner") =>
              println(metaNs.attrs.filter(_.options.contains("owner")).mkString("   ----------- " + ns + " -----------\n   ", "\n   ", ""))

              val refNs     = metaAttr.refNs.get
              val refAttr   = metaAttr.attr
              val refMetaNs = nsMap(refNs)

              metaAttr.card match {
                case _: CardOne =>

                  println("A     nsIds  " + nsIds)
                  val refIds       = if (nsIds.isEmpty) Nil else {
                    val stmt =
                      s"""SELECT $refNs.id
                         |FROM $refNs
                         |INNER JOIN $ns on $ns.$refAttr = $refNs.id
                         |WHERE $ns.id in (${nsIds.mkString(", ")})
                         |""".stripMargin

                    println("-----")
                    println(stmt)
                    println("-----")

                    val resultSet = sqlConn.createStatement().executeQuery(stmt)
                    var refIds    = List.empty[Long]
                    while (resultSet.next()) {
                      refIds = refIds :+ resultSet.getLong(1)
                    }
                    println("A     refIds " + refIds)
                    refIds
                  }
                  val ownedTables1 = if (refIds.nonEmpty) Seq(prepareTable(refNs, "id", refIds)) else Nil
                  deleteOwned(MetaNs(ns, attrTail) +: refMetaNs +: nsTail, ownedTables ++ ownedTables1, nsIds)

                case _: CardSet =>
                  val joinTable = s"${ns}_${metaAttr.attr}_$refNs"

                  println("B     nsIds  " + nsIds)
                  val refIds       = if (nsIds.isEmpty) Nil else {
                    val stmt =
                      s"""SELECT $joinTable.${refNs}_id
                         |FROM $joinTable
                         |INNER JOIN $ns on $ns.id = $joinTable.${ns}_id
                         |WHERE $ns.id in (${nsIds.mkString(", ")})
                         |""".stripMargin

                    println("-----")
                    println(stmt)
                    println("-----")

                    val resultSet = sqlConn.createStatement().executeQuery(stmt)
                    var refIds    = List.empty[Long]
                    while (resultSet.next()) {
                      refIds = refIds :+ resultSet.getLong(1)
                    }
                    println("B     refIds " + refIds)
                    refIds
                  }
                  val ownedTables1 = if (refIds.isEmpty) Nil else {
                    Seq(
                      prepareTable(joinTable, ns + "_id", nsIds),
                      prepareTable(refNs, "id", refIds)
                    )
                  }

                  deleteOwned(MetaNs(ns, attrTail) +: refMetaNs +: nsTail, ownedTables ++ ownedTables1, nsIds)
              }

            case _ :: tail => deleteOwned(List(MetaNs(ns, tail)), ownedTables, nsIds)
          }
      }
    }

    // Delete owned entities
    val ownedTables = deleteOwned(Seq(nsMap(baseNs)), Nil, ids)

    println("=====================================================")
    println(table.stmt)
    println("-----")
    joinTables.foreach(t => println(t.stmt))
    println("-----")
    ownedTables.foreach(t => println(t.stmt))

    //    (List(table), Nil)
    //    ((table +: joinTables), Nil)
    ((table +: joinTables) ++ ownedTables, Nil)
  }

  override def addIds[T](ids1: Seq[T]): Unit = {
    ids = ids ++ ids1.asInstanceOf[Seq[Long]]
  }

  override def addFilterElements(elements: Seq[Element]): Unit = {
    filterElements = filterElements ++ elements
  }
}