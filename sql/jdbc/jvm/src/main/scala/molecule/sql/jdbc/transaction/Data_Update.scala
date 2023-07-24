package molecule.sql.jdbc.transaction

import java.util.{Set => jSet}
import clojure.lang.Keyword
import datomic.Util.list
import datomic.query.EntityMap
import datomic.{Database, Peer}
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.UpdateExtraction
import molecule.core.transaction.ops.UpdateOps
import molecule.core.validation.ModelValidation
import molecule.sql.core.query.SqlModel2Query
import molecule.sql.jdbc.facade.JdbcConn_jvm
import scala.collection.mutable.ListBuffer

trait Data_Update extends JdbcTxMetaData_JVM with UpdateOps with MoleculeLogging { self: UpdateExtraction =>

  def getStmts(
    conn: JdbcConn_jvm,
    elements: List[Element],
    isRpcCall: Boolean = false,
    debug: Boolean = true
  ): Int = { // todo
//    val db = conn.sqlConn.db()
//
//    if (isRpcCall) {
//      // Check against db on jvm if rpc from client
//
//      val getCurSetValues: Attr => Set[Any] = (attr: Attr) => {
//        val a = s":${attr.ns}/${attr.attr}"
//        try {
//          val curValues = Peer.q(s"[:find ?vs :where [_ $a ?vs]]", db)
//          if (curValues.isEmpty) {
//            throw ExecutionError(s"While checking to avoid removing the last values of mandatory " +
//              s"attribute ${attr.ns}.${attr.attr} the current Set of values couldn't be found.")
//          }
//          val vs = ListBuffer.empty[Any]
//          curValues.forEach(row => vs.addOne(row.get(0)))
//          vs.toSet
//        } catch {
//          case e: MoleculeError => throw e
//          case t: Throwable     => throw ExecutionError(
//            s"Unexpected error trying to find current values of mandatory attribute ${attr.name}:\n" + t)
//        }
//      }
//
//      val validationErrors = ModelValidation(
//        conn.proxy.nsMap,
//        conn.proxy.attrMap,
//        "update",
//        Some(getCurSetValues)
//      ).validate(elements)
//      if (validationErrors.nonEmpty) {
//        throw ValidationErrors(validationErrors)
//      }
//    }
//
//    val (ids, filterElements, data) = resolve(elements, Nil, Nil, Nil)
//    val (filterQuery, inputs)        = if (ids.isEmpty && filterElements.nonEmpty) {
//      val filterElements1 = AttrOneManLong("_Generic", "id", V) +: filterElements
//      val (query, inputs) = new SqlModel2Query[Any](filterElements1).getEidQueryWithInputs
//      (Some(query), inputs)
//    } else {
//      (None, Nil)
//    }
//    filterQuery.fold {
//      val addStmts = id2stmts(data, db, isUpsert)
//      ids.foreach(addStmts)
//    } { query =>
//      val idRows  = Peer.q(query, db +: inputs: _*)
//      val addStmts = id2stmts(data, db)
//      idRows.forEach(idRow => addStmts(idRow.get(0)))
//    }
//    if (debug) {
//      val updateStrs = "UPDATE:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
//      logger.debug(updateStrs.mkString("\n").trim)
//    }
//    stmts
    ???
  }

  private def id2stmts(
    data: Seq[(String, String, String, Seq[AnyRef], Boolean)],
    db: Database,
    addNewValues: Boolean = true
  ): AnyRef => Unit = {
    (id0: AnyRef) => {
      var id : AnyRef  = id0
      var txId: AnyRef  = null
      var isTx: Boolean = false
      var entity        = db.entity(id)
      data.foreach {
        case ("add", ns, attr, newValues, retractCur) =>
          val a = kw(ns, attr)
          if (retractCur) {
            val id1      = if (txId != null) txId else id
            // todo: optimize with one query for all ids
            val curValues = Peer.q("[:find ?v :in $ ?e ?a :where [?e ?a ?v]]", db, id1, a)
            curValues.forEach { row =>
              val curValue = row.get(0)
              if (!newValues.contains(curValue))
                appendStmt(retract, id1, a, curValue)
            }
          }
          if (addNewValues || entity.get(a) != null || isTx) {
            newValues.foreach(newValue =>
              appendStmt(add, id, a, newValue)
            )
          }

        case ("retract", ns, attr, retractValues, _) =>
          val a = kw(ns, attr)
          if (retractValues.isEmpty) {
            val cur = entity.get(a)
            if (cur != null) {
              cur match {
                case set: jSet[_] =>
                  set.forEach {
                    case kw: Keyword          => appendStmt(retract, id, a, db.entity(kw).get(":db/id"))
                    case entityMap: EntityMap => appendStmt(retract, id, a, entityMap.get(":db/id"))
                    case v                    => appendStmt(retract, id, a, v.asInstanceOf[AnyRef])
                  }
                case v            =>
                  appendStmt(retract, id, a, v)
              }
            }
          } else {
            retractValues.foreach(retractValue =>
              appendStmt(retract, id, a, retractValue)
            )
          }

        case ("ref", ns, refAttr, _, _) =>
          val a = kw(ns, refAttr)
          entity = entity.get(a).asInstanceOf[EntityMap]
          id = entity.get(dbId)

        case ("tx", _, _, _, _) =>
          // Get transaction entity id
          txId = Peer.q("[:find ?tx :in $ ?e :where [?e _ _ ?tx]]", db, id).iterator.next.get(0)
          entity = db.entity(txId)
          isTx = true
          id = datomicTx

        case other => throw ModelError("Unexpected data in update: " + other)
      }
    }
  }


  override protected def uniqueIds(
    filterAttr: AttrOneTac,
    ns: String,
    attr: String
  ): Seq[AnyRef] = {
    val at = s":$ns/$attr"
    filterAttr match {
      case AttrOneTacString(_, _, _, vs, _, _, _, _, _, _)     => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacInt(_, _, _, vs, _, _, _, _, _, _)        => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacLong(_, _, _, vs, _, _, _, _, _, _)       => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacFloat(_, _, _, vs, _, _, _, _, _, _)      => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacDouble(_, _, _, vs, _, _, _, _, _, _)     => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacBoolean(_, _, _, vs, _, _, _, _, _, _)    => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacBigInt(_, _, _, vs, _, _, _, _, _, _)     => vs.map(v => list(at, v.bigInteger.asInstanceOf[AnyRef]))
      case AttrOneTacBigDecimal(_, _, _, vs, _, _, _, _, _, _) => vs.map(v => list(at, v.bigDecimal.asInstanceOf[AnyRef]))
      case AttrOneTacDate(_, _, _, vs, _, _, _, _, _, _)       => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacUUID(_, _, _, vs, _, _, _, _, _, _)       => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacURI(_, _, _, vs, _, _, _, _, _, _)        => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacByte(_, _, _, vs, _, _, _, _, _, _)       => vs.map(v => list(at, v.toInt.asInstanceOf[AnyRef]))
      case AttrOneTacShort(_, _, _, vs, _, _, _, _, _, _)      => vs.map(v => list(at, v.toInt.asInstanceOf[AnyRef]))
      case AttrOneTacChar(_, _, _, vs, _, _, _, _, _, _)       => vs.map(v => list(at, v.toString.asInstanceOf[AnyRef]))
    }
  }

  override protected lazy val valueString     = identity
  override protected lazy val valueInt        = identity
  override protected lazy val valueLong       = identity
  override protected lazy val valueFloat      = identity
  override protected lazy val valueDouble     = identity
  override protected lazy val valueBoolean    = identity
  override protected lazy val valueBigInt     = (v: BigInt) => v.bigInteger
  override protected lazy val valueBigDecimal = (v: BigDecimal) => v.bigDecimal
  override protected lazy val valueDate       = identity
  override protected lazy val valueUUID       = identity
  override protected lazy val valueURI        = identity
  override protected lazy val valueByte       = (v: Byte) => v.toInt
  override protected lazy val valueShort      = (v: Short) => v.toInt
  override protected lazy val valueChar       = (v: Char) => v.toString
}