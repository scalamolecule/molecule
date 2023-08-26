package molecule.sql.jdbc.transaction

import java.sql.{ResultSet, PreparedStatement => PS}
import molecule.base.ast.SchemaAST.{CardOne, CardSet, MetaNs}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveDelete
import molecule.core.transaction.ops.DeleteOps
import molecule.sql.core.query.SqlModel2Query
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

trait Data_Delete
  extends JdbcBase_JVM
    with DeleteOps
    with MoleculeLogging { self: ResolveDelete =>


  def getData(elements: List[Element], nsMap: Map[String, MetaNs]): Data = {
    val refPath          = List(getInitialNs(elements))
    val (deleteModel, _) = separateTxElements(elements)
    resolve(deleteModel, true)

    if (ids.isEmpty && filterElements.nonEmpty) {
      val (ordinality, filterIdss) = getIds
      if (ordinality == 1) {
        deleteTableData(refPath, nsMap, filterIdss.head)
      } else {
        val tables = if (ordinality == filterElements.size) {
          filterIdss.zip(filterElements).map {
            case (filterIds, Composite(es)) =>
              deleteTableData(List(getInitialNs(es)), nsMap, filterIds)
          }.flatMap { case (tables, _) => tables }
        } else {
          throw ModelError(s"Expected $ordinality filter Elements. Found:\n" + filterElements.mkString("\n"))
        }
        (tables, Nil) // no join tables
      }
    } else {
      deleteTableData(refPath, nsMap, ids)
    }
  }


  private def deleteTableData(refPath: List[String], nsMap: Map[String, MetaNs], ids: Seq[Long]): Data = {
    val ns           = refPath.head
    val table: Table = prepareTable(refPath, ns, s"$ns.id", ids)

    // Delete join rows matching deleted entities
    val joinTables: List[Table] = nsMap(ns).attrs
      .filter(attr => attr.refNs.nonEmpty && attr.card == CardSet && !attr.options.contains("owner"))
      .map { metaAttr =>
        val refNs     = metaAttr.refNs.get
        val joinTable = s"${ns}_${metaAttr.attr}_$refNs"
        val idCol     = ns + (if (ns == refNs) "_1_id" else "_id")
        prepareTable(refPath, joinTable, idCol, ids)
      }.toList

    // Recursively delete owned entities
    val ownedTables = deleteOwned(refPath, nsMap, Seq(nsMap(ns)), Nil, ids)

    ((table +: joinTables) ++ ownedTables, Nil)
  }


  private def getIds: (RowIndex, List[List[Long]]) = {
    val ns                    = getInitialNs(filterElements)
    var ordinality            = 1
    val filterElementsWithIds = filterElements.head match {
      case Composite(es) =>
        // Find id of first namespace in each composite group
        Composite(AttrOneManLong(ns, "id", V) +: es) +: filterElements.tail.map {
          case Composite(es) =>
            ordinality += 1
            val compositeNs = getInitialNs(es)
            Composite(AttrOneManLong(compositeNs, "id", V) +: es)
          case other         => other
        }

      case _ => AttrOneManLong(ns, "id", V) +: filterElements
    }

    val stmt = new SqlModel2Query[Any](filterElementsWithIds).getSqlQuery(Nil)
    val ps   = sqlConn.prepareStatement(stmt, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
    (ordinality, extractIds(ordinality, ps.executeQuery()))
  }


  private def prepareTable(refPath: List[String], table: String, idColumn: String, ids: Seq[Long]): Table = {
    val ids_       = ids.mkString(", ")
    val stmt       = s"DELETE FROM $table WHERE $idColumn IN ($ids_)"
    val ps         = sqlConn.prepareStatement(stmt)
    val populatePS = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
    Table(refPath, stmt, ps, populatePS)
  }


  @tailrec
  private def deleteOwned(
    refPath: List[String],
    nsMap: Map[String, MetaNs],
    metaNs: Seq[MetaNs],
    ownedTables: Seq[Table],
    nsIds: Seq[Long],
  ): Seq[Table] = {
    metaNs match {
      case Nil              => ownedTables
      case metaNs :: nsTail =>
        val ns = metaNs.ns
        metaNs.attrs match {
          case Nil => deleteOwned(refPath, nsMap, nsTail, ownedTables, nsIds)

          case metaAttr :: attrTail if metaAttr.options.contains("owner") =>
            val refNs     = metaAttr.refNs.get
            val refAttr   = metaAttr.attr
            val refMetaNs = nsMap(refNs)

            metaAttr.card match {
              case _: CardOne =>
                val refIds       = if (nsIds.isEmpty) Nil else {
                  val stmt      =
                    s"""SELECT $refNs.id
                       |FROM $refNs
                       |INNER JOIN $ns on $ns.$refAttr = $refNs.id
                       |WHERE $ns.id in (${nsIds.mkString(", ")})
                       |""".stripMargin
                  val resultSet = sqlConn.createStatement().executeQuery(stmt)
                  var refIds    = List.empty[Long]
                  while (resultSet.next()) {
                    refIds = refIds :+ resultSet.getLong(1)
                  }
                  refIds
                }
                val ownedTables1 = if (refIds.nonEmpty) Seq(prepareTable(refPath, refNs, "id", refIds)) else Nil
                deleteOwned(
                  refPath,
                  nsMap,
                  MetaNs(ns, attrTail) +: refMetaNs +: nsTail,
                  ownedTables ++ ownedTables1,
                  nsIds
                )

              case _: CardSet =>
                val joinTable    = s"${ns}_${metaAttr.attr}_$refNs"
                val refIds       = if (nsIds.isEmpty) Nil else {
                  val stmt      =
                    s"""SELECT $joinTable.${refNs}_id
                       |FROM $joinTable
                       |INNER JOIN $ns on $ns.id = $joinTable.${ns}_id
                       |WHERE $ns.id in (${nsIds.mkString(", ")})
                       |""".stripMargin
                  val resultSet = sqlConn.createStatement().executeQuery(stmt)
                  var refIds    = List.empty[Long]
                  while (resultSet.next()) {
                    refIds = refIds :+ resultSet.getLong(1)
                  }
                  refIds
                }
                val ownedTables1 = if (refIds.isEmpty) Nil else {
                  Seq(
                    prepareTable(refPath, joinTable, ns + "_id", nsIds),
                    prepareTable(refPath, refNs, "id", refIds)
                  )
                }
                deleteOwned(
                  refPath,
                  nsMap,
                  MetaNs(ns, attrTail) +: refMetaNs +: nsTail,
                  ownedTables ++ ownedTables1,
                  nsIds)
            }

          case _ :: tail => deleteOwned(refPath, nsMap, List(MetaNs(ns, tail)), ownedTables, nsIds)
        }
    }
  }


  override def addIds[T](ids1: Seq[T]): Unit = {
    ids = ids ++ ids1.asInstanceOf[Seq[Long]]
  }

  override def addFilterElement(element: Element): Unit = {
    filterElements = filterElements :+ element
  }


  private def extractIds(ordinality: Int, resultSet: ResultSet): List[List[Long]] = {
    val ids1 = ListBuffer.empty[Long]
    lazy val ids2  = ListBuffer.empty[Long]
    lazy val ids3  = ListBuffer.empty[Long]
    lazy val ids4  = ListBuffer.empty[Long]
    lazy val ids5  = ListBuffer.empty[Long]
    lazy val ids6  = ListBuffer.empty[Long]
    lazy val ids7  = ListBuffer.empty[Long]
    lazy val ids8  = ListBuffer.empty[Long]
    lazy val ids9  = ListBuffer.empty[Long]
    lazy val ids10 = ListBuffer.empty[Long]
    lazy val ids11 = ListBuffer.empty[Long]
    lazy val ids12 = ListBuffer.empty[Long]
    lazy val ids13 = ListBuffer.empty[Long]
    lazy val ids14 = ListBuffer.empty[Long]
    lazy val ids15 = ListBuffer.empty[Long]
    lazy val ids16 = ListBuffer.empty[Long]
    lazy val ids17 = ListBuffer.empty[Long]
    lazy val ids18 = ListBuffer.empty[Long]
    lazy val ids19 = ListBuffer.empty[Long]
    lazy val ids20 = ListBuffer.empty[Long]
    lazy val ids21 = ListBuffer.empty[Long]
    lazy val ids22 = ListBuffer.empty[Long]

    val extractIds = ordinality match {
      case 1 => (rs: ResultSet) =>
        ids1 += rs.getLong(1)

      case 2 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
      }

      case 3 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
      }

      case 4 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
      }

      case 5 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
      }

      case 6 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
      }

      case 7 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
      }

      case 8 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
      }

      case 9 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
      }

      case 10 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
        ids10 += rs.getLong(10)
      }

      case 11 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
        ids10 += rs.getLong(10)
        ids11 += rs.getLong(11)
      }

      case 12 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
        ids10 += rs.getLong(10)
        ids11 += rs.getLong(11)
        ids12 += rs.getLong(12)
      }

      case 13 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
        ids10 += rs.getLong(10)
        ids11 += rs.getLong(11)
        ids12 += rs.getLong(12)
        ids13 += rs.getLong(13)
      }

      case 14 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
        ids10 += rs.getLong(10)
        ids11 += rs.getLong(11)
        ids12 += rs.getLong(12)
        ids13 += rs.getLong(13)
        ids14 += rs.getLong(14)
      }

      case 15 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
        ids10 += rs.getLong(10)
        ids11 += rs.getLong(11)
        ids12 += rs.getLong(12)
        ids13 += rs.getLong(13)
        ids14 += rs.getLong(14)
        ids15 += rs.getLong(15)
      }

      case 16 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
        ids10 += rs.getLong(10)
        ids11 += rs.getLong(11)
        ids12 += rs.getLong(12)
        ids13 += rs.getLong(13)
        ids14 += rs.getLong(14)
        ids15 += rs.getLong(15)
        ids16 += rs.getLong(16)
      }

      case 17 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
        ids10 += rs.getLong(10)
        ids11 += rs.getLong(11)
        ids12 += rs.getLong(12)
        ids13 += rs.getLong(13)
        ids14 += rs.getLong(14)
        ids15 += rs.getLong(15)
        ids16 += rs.getLong(16)
        ids17 += rs.getLong(17)
      }

      case 18 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
        ids10 += rs.getLong(10)
        ids11 += rs.getLong(11)
        ids12 += rs.getLong(12)
        ids13 += rs.getLong(13)
        ids14 += rs.getLong(14)
        ids15 += rs.getLong(15)
        ids16 += rs.getLong(16)
        ids17 += rs.getLong(17)
        ids18 += rs.getLong(18)
      }

      case 19 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
        ids10 += rs.getLong(10)
        ids11 += rs.getLong(11)
        ids12 += rs.getLong(12)
        ids13 += rs.getLong(13)
        ids14 += rs.getLong(14)
        ids15 += rs.getLong(15)
        ids16 += rs.getLong(16)
        ids17 += rs.getLong(17)
        ids18 += rs.getLong(18)
        ids19 += rs.getLong(19)
      }

      case 20 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
        ids10 += rs.getLong(10)
        ids11 += rs.getLong(11)
        ids12 += rs.getLong(12)
        ids13 += rs.getLong(13)
        ids14 += rs.getLong(14)
        ids15 += rs.getLong(15)
        ids16 += rs.getLong(16)
        ids17 += rs.getLong(17)
        ids18 += rs.getLong(18)
        ids19 += rs.getLong(19)
        ids20 += rs.getLong(20)
      }

      case 21 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
        ids10 += rs.getLong(10)
        ids11 += rs.getLong(11)
        ids12 += rs.getLong(12)
        ids13 += rs.getLong(13)
        ids14 += rs.getLong(14)
        ids15 += rs.getLong(15)
        ids16 += rs.getLong(16)
        ids17 += rs.getLong(17)
        ids18 += rs.getLong(18)
        ids19 += rs.getLong(19)
        ids20 += rs.getLong(20)
        ids21 += rs.getLong(21)
      }

      case 22 => (rs: ResultSet) => {
        ids1 += rs.getLong(1)
        ids2 += rs.getLong(2)
        ids3 += rs.getLong(3)
        ids4 += rs.getLong(4)
        ids5 += rs.getLong(5)
        ids6 += rs.getLong(6)
        ids7 += rs.getLong(7)
        ids8 += rs.getLong(8)
        ids9 += rs.getLong(9)
        ids10 += rs.getLong(10)
        ids11 += rs.getLong(11)
        ids12 += rs.getLong(12)
        ids13 += rs.getLong(13)
        ids14 += rs.getLong(14)
        ids15 += rs.getLong(15)
        ids16 += rs.getLong(16)
        ids17 += rs.getLong(17)
        ids18 += rs.getLong(18)
        ids19 += rs.getLong(19)
        ids20 += rs.getLong(20)
        ids21 += rs.getLong(21)
        ids22 += rs.getLong(22)
      }
    }

    while (resultSet.next()) {
      extractIds(resultSet)
    }

    ordinality match {
      case 1 => List(
        ids1.toList
      )

      case 2 => List(
        ids1.toList,
        ids2.toList
      )

      case 3 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
      )

      case 4 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
      )

      case 5 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
      )

      case 6 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
      )

      case 7 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
      )

      case 8 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
      )

      case 9 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
      )

      case 10 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
        ids10.toList,
      )

      case 11 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
        ids10.toList,
        ids11.toList,
      )

      case 12 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
        ids10.toList,
        ids11.toList,
        ids12.toList,
      )

      case 13 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
        ids10.toList,
        ids11.toList,
        ids12.toList,
        ids13.toList,
      )

      case 14 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
        ids10.toList,
        ids11.toList,
        ids12.toList,
        ids13.toList,
        ids14.toList,
      )

      case 15 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
        ids10.toList,
        ids11.toList,
        ids12.toList,
        ids13.toList,
        ids14.toList,
        ids15.toList,
      )

      case 16 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
        ids10.toList,
        ids11.toList,
        ids12.toList,
        ids13.toList,
        ids14.toList,
        ids15.toList,
        ids16.toList,
      )

      case 17 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
        ids10.toList,
        ids11.toList,
        ids12.toList,
        ids13.toList,
        ids14.toList,
        ids15.toList,
        ids16.toList,
        ids17.toList,
      )

      case 18 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
        ids10.toList,
        ids11.toList,
        ids12.toList,
        ids13.toList,
        ids14.toList,
        ids15.toList,
        ids16.toList,
        ids17.toList,
        ids18.toList,
      )

      case 19 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
        ids10.toList,
        ids11.toList,
        ids12.toList,
        ids13.toList,
        ids14.toList,
        ids15.toList,
        ids16.toList,
        ids17.toList,
        ids18.toList,
        ids19.toList,
      )

      case 20 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
        ids10.toList,
        ids11.toList,
        ids12.toList,
        ids13.toList,
        ids14.toList,
        ids15.toList,
        ids16.toList,
        ids17.toList,
        ids18.toList,
        ids19.toList,
        ids20.toList,
      )

      case 21 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
        ids10.toList,
        ids11.toList,
        ids12.toList,
        ids13.toList,
        ids14.toList,
        ids15.toList,
        ids16.toList,
        ids17.toList,
        ids18.toList,
        ids19.toList,
        ids20.toList,
        ids21.toList,
      )

      case 22 => List(
        ids1.toList,
        ids2.toList,
        ids3.toList,
        ids4.toList,
        ids5.toList,
        ids6.toList,
        ids7.toList,
        ids8.toList,
        ids9.toList,
        ids10.toList,
        ids11.toList,
        ids12.toList,
        ids13.toList,
        ids14.toList,
        ids15.toList,
        ids16.toList,
        ids17.toList,
        ids18.toList,
        ids19.toList,
        ids20.toList,
        ids21.toList,
        ids22.toList,
      )
    }
  }
}