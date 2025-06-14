package molecule.db.datalog.datomic.transaction

import datomic.Peer
import molecule.base.error.ExecutionError
import molecule.core.dataModel.*
import molecule.core.util.{JavaConversions, MoleculeLogging}
import molecule.db.core.transaction.ResolveDelete
import molecule.db.core.transaction.ops.DeleteOps
import molecule.db.core.util.MetaModelUtils
import molecule.db.datalog.core.query.Model2DatomicQuery
import molecule.db.datalog.datomic.facade.DatomicConn_JVM
import scala.collection.mutable

trait Delete_datomic
  extends DatomicBase_JVM
    with DeleteOps
    with MetaModelUtils
    with MoleculeLogging
    with JavaConversions { self: ResolveDelete =>

  def getData(
    conn: DatomicConn_JVM,
    dataModel: DataModel,
    idIndex: Int = 0,
    debug: Boolean = true
  ): Data = {
    val elements = dataModel.elements
    initTxBase(elements, idIndex)

    // Resolve the delete model
    resolve(elements, true)

    val (filterQuery, inputs) = if (ids.isEmpty && filterElements.nonEmpty) {
      val filterElements1 = AttrOneManID("DummyEntity", "id", V) +: filterElements
      val (query, inputs) = new Model2DatomicQuery[Any](DataModel(filterElements1)).getIdQueryWithInputs
      (Some(query), inputs)
    } else {
      (None, Nil)
    }

    lazy val db = conn.peerConn.db()
    val ids1 = filterQuery.fold(ids)(query =>
      Peer.q(query, db +: inputs *).asScala.toList.map(_.get(0))
    )

    // Add retract stmts
    ids1.foreach(addRetractEntityStmt)

    // Prevent deleting mandatory referenced entities
    if (getHasMandatoryRefs(conn.proxy.entityMap)) {
      val referrers = Peer.q(
        s"""[:find  ?ns ?attr ?refs
           | :in    $$ [?ids ...]
           | :where [?refs ?a ?ids]
           |        [?a :db/ident ?attrIdent]
           |        [(namespace ?attrIdent) ?ns]
           |        [(name ?attrIdent) ?attr]
           |]""".stripMargin, db, ids1.asJava
      )
      if (referrers.size() != 0) {
        val refIds = mutable.Map.empty[String, List[Long]]
        referrers.forEach { row =>
          val refAttr = s"${row.get(0)}.${row.get(1)}"
          refIds.get(refAttr).fold[Unit](
            refIds += refAttr -> List(row.get(2).asInstanceOf[Long])
          )(curRefIds =>
            refIds(refAttr) = curRefIds :+ row.get(2).asInstanceOf[Long]
          )
        }
        if (refIds.nonEmpty) {
          val refAttrsStr = refIds.toList.sortBy(_._1).map {
            case (refAttr, ids) => s"$refAttr: List(${ids.mkString(", ")})"
          }
          throw ExecutionError(
            s"""Can't delete entities referenced by mandatory ref attributes of other entities:
               |  ${refAttrsStr.mkString("\n  ")}
               |""".stripMargin
          )
        }
      }
    }
    if (debug) {
      val deleteStrs = "DELETE:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
      logger.debug(deleteStrs.mkString("\n").trim)
    }
    stmts
  }

  override def addIds(ids1: Seq[Long]): Unit = {
    ids = ids ++ ids1.asInstanceOf[Seq[AnyRef]]
  }

  override def addFilterElement(element: Element): Unit = {
    filterElements = filterElements :+ element
  }
}