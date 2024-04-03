package molecule.datalog.datomic.transaction

import java.time._
import java.util.{Set => jSet}
import clojure.lang.Keyword
import datomic.Util.list
import datomic.query.EntityMap
import datomic.{Database, Peer}
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.core.validation.TxModelValidation
import molecule.datalog.core.query.Model2DatomicQuery
import molecule.datalog.datomic.facade.DatomicConn_JVM
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait Update_datomic
  extends DatomicBase_JVM
    with UpdateOps
    with ModelTransformations_
    with MoleculeLogging { self: ResolveUpdate =>

  private val data = new ListBuffer[(String, String, String, Seq[AnyRef], Boolean, String)]

  def getStmts(
    conn: DatomicConn_JVM,
    elements: List[Element],
    isRpcCall: Boolean = false,
    debug: Boolean = true
  ): Data = {
    //    println("-------")
    //    elements.foreach(println)

    val db = conn.peerConn.db()

    if (isRpcCall) {
      // Check against db on jvm if rpc from client

      val getCurSetValues: Attr => Set[Any] = (attr: Attr) => {
        val a = s":${attr.ns}/${attr.attr}"
        try {
          val curValues = Peer.q(s"[:find ?vs :where [_ $a ?vs]]", db)
          if (curValues.isEmpty) {
            throw ExecutionError(s"While checking to avoid removing the last values of mandatory " +
              s"attribute ${attr.ns}.${attr.attr} the current Set of values couldn't be found.")
          }
          val vs = ListBuffer.empty[Any]
          curValues.forEach(row => vs += row.get(0))
          vs.toSet
        } catch {
          case e: MoleculeError => throw e
          case t: Throwable     => throw ExecutionError(
            s"Unexpected error trying to find current values of mandatory attribute ${attr.name}:\n" + t)
        }
      }

      val validationErrors = TxModelValidation(
        conn.proxy.nsMap,
        conn.proxy.attrMap,
        if (isUpsert) "upsert" else "update",
        Some(getCurSetValues)
      ).validate(elements)
      if (validationErrors.nonEmpty) {
        throw ValidationErrors(validationErrors)
      }
    }

    // Resolve the update model
    resolve(elements)

    if (ids.isEmpty && filterElements.nonEmpty) {
      val filterNs        = filterElements.head.asInstanceOf[Attr].ns
      val filterElements1 = AttrOneManID(filterNs, "id", V) +: filterElements
      val (query, inputs) = new Model2DatomicQuery[Any](filterElements1).getIdQueryWithInputs
      val idRows          = Peer.q(query, db +: inputs: _*)
      val addStmts        = id2stmts(db)
      idRows.forEach(idRow => addStmts(idRow.get(0)))

    } else if (isUpsert) {
      val addStmts = id2stmts(db, isUpsert)
      ids.foreach(addStmts)

    } else {
      // Only update entities having all attributes already asserted
      val filterNs        = elements.head.asInstanceOf[Attr].ns
      val cleanElements   = cleanUpdateElements(elements)
      val filterElements1 = AttrOneManID(filterNs, "id", V) +: cleanElements
      val (query, inputs) = new Model2DatomicQuery[Any](filterElements1).getIdQueryWithInputs
      val idRows          = Peer.q(query, db +: inputs: _*)
      val addStmts        = id2stmts(db, isUpsert)
      idRows.forEach(row => addStmts(row.get(0)))
    }

    if (debug) {
      val updateStrs = "UPDATE:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
      logger.debug(updateStrs.mkString("\n").trim)
    }
    stmts
  }

  override def updateOne[T](
    ns: String,
    attr: String,
    owner: Boolean,
    vs: Seq[T],
    transformValue: T => Any,
  ): Unit = {
    if (isUpdate) {
      val dummyFilterAttr = AttrOneTacInt(ns, attr)
      filterElements = filterElements :+ dummyFilterAttr
    }
    vs match {
      case Seq(v) => data += (("add", ns, attr, Seq(transformValue(v).asInstanceOf[AnyRef]), false, ""))
      case Nil    => data += (("retract", ns, attr, Nil, false, ""))
      case vs     => throw ExecutionError(
        s"Can only $update one value for attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }


  override def updateSetEq[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    if (isUpdate) {
      val dummyFilterAttr = AttrOneTacInt(ns, attr)
      filterElements = filterElements :+ dummyFilterAttr
    }
    if (set.nonEmpty) {
      data += (("add", ns, attr, set.map(v => transformValue(v).asInstanceOf[AnyRef]).toSeq, true, ""))
    } else {
      data += (("retract", ns, attr, Nil, true, ""))
    }
  }

  override def updateSetAdd[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    if (set.nonEmpty) {
      data += (("add", ns, attr, set.map(v => transformValue(v).asInstanceOf[AnyRef]).toSeq, false, ""))
    }
  }

  override def updateSetRemove[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit = {
    if (set.nonEmpty) {
      data += (("retract", ns, attr, set.map(v => transformValue(v).asInstanceOf[AnyRef]).toSeq, false, ""))
    }
  }


  override def updateSeqEq[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    if (isUpdate) {
      val dummyFilterAttr = AttrOneTacInt(ns, attr)
      filterElements = filterElements :+ dummyFilterAttr
    }
    if (seq.nonEmpty) {
      data += (("add", ns, attr, seq.map(v => transformValue(v).asInstanceOf[AnyRef]), true, "seq"))
    } else {
      data += (("retract", ns, attr, Nil, true, "seq"))
    }
  }

  override def updateSeqAdd[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    if (seq.nonEmpty) {
      data += (("add", ns, attr, seq.map(v => transformValue(v).asInstanceOf[AnyRef]), false, "seq"))
    }
  }

  override def updateSeqRemove[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit = {
    if (seq.nonEmpty) {
      data += (("retract", ns, attr, seq.map(v => transformValue(v).asInstanceOf[AnyRef]), false, "seq"))
    }
  }

  override def updateByteArray(ns: String, attr: String, byteArray: Array[Byte]): Unit = {
    if (byteArray.nonEmpty) {
      data += (("add", ns, attr, Seq(byteArray), true, ""))
    } else {
      data += (("retract", ns, attr, Nil, true, "seq"))
    }
  }


  override def updateMapEq[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    noValue: Boolean,
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    if (isUpdate) {
      val dummyFilterAttr = AttrOneTacInt(ns, attr)
      filterElements = filterElements :+ dummyFilterAttr
    }
    if (noValue) {
      data += (("retract", ns, attr, Nil, true, "map"))
    } else {
      val pairs = map.map { case (k, v) =>
        (validKey(k), transformValue(v).asInstanceOf[AnyRef])
      }.toSeq
      data += (("add", ns, attr, pairs, true, "map"))
    }
  }

  override def updateMapAdd[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): Unit = {
    if (map.nonEmpty) {
      val pairs = map.map { case (k, v) =>
        (validKey(k), transformValue(v).asInstanceOf[AnyRef])
      }.toSeq
      data += (("add", ns, attr, pairs, false, "map"))
    }
  }

  override def updateMapRemove[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): Unit = {
    if (map.nonEmpty) {
      data += (("retract", ns, attr, map.keys.toSeq, false, "map"))
    }
  }


  override def handleIds(ns: String, ids1: Seq[String]): Unit = {
    if (ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in $update.")
    }
    ids = ids ++ ids1.map(_.toLong).asInstanceOf[Seq[AnyRef]]
  }

  override def handleUniqueFilterAttr(uniqueFilterAttr: AttrOneTac): Unit = {
    if (ids.nonEmpty) {
      throw ModelError(
        s"Can only apply one unique attribute value for $update. Found:\n" + uniqueFilterAttr
      )
    }
    ids = ids ++ uniqueIds(uniqueFilterAttr, uniqueFilterAttr.ns, uniqueFilterAttr.attr)
  }

  override def handleFilterAttr(filterAttr: AttrOneTac): Unit = {
    filterElements = filterElements :+ filterAttr
  }

  override def handleRefNs(ref: Ref): Unit = {
    data += (("ref", ref.ns, ref.refAttr, Nil, false, ""))
  }

  override def handleBackRef(backRef: BackRef): Unit = {
    data += (("backref", backRef.prevNs, "", Nil, false, ""))
  }


  private def id2stmts(db: Database, addNewValues: Boolean = true): AnyRef => Unit = {
    (id0: AnyRef) => {
      var id      : AnyRef          = id0
      var entity  : EntityMap       = db.entity(id).asInstanceOf[EntityMap]
      var entities: List[EntityMap] = List(entity)
      data.foreach {
        case ("add", ns, attr, newValues, retractCur, "") =>
          addOneOrToSet(ns, attr, newValues, retractCur)

        case ("add", ns, attr, newValues, retractCur, "seq") =>
          addToSeq(ns, attr, newValues, retractCur)

        case ("add", ns, attr, newValues, retractCur, "map") =>
          addToMap(ns, attr, newValues, retractCur)

        case ("retract", ns, attr, retractValues, _, kind) =>
          doRetract(ns, attr, retractValues, kind)

        case ("backref", _, _, _, _, _) =>
          entities = entities.init
          entity = entities.last
          id = entity.get(dbId)

        case ("ref", ns, refAttr, _, _, _) =>
          val a = kw(ns, refAttr)
          entity = entities.last.get(a).asInstanceOf[EntityMap]
          entities = entities :+ entity
          id = entity.get(dbId)

        case other => throw ModelError("Unexpected data in update: " + other)
      }

      def addOneOrToSet(ns: String, attr: String, newValues: Seq[AnyRef], retractCur: Boolean) = {
        val a         = kw(ns, attr)
        val id1       = id
        val curValues = ListBuffer.empty[AnyRef]
        Peer.q("[:find ?v :in $ ?e ?a :where [?e ?a ?v]]", db, id1, a).forEach { row =>
          curValues += row.get(0)
        }
        if (retractCur) {
          curValues.foreach { curValue =>
            if (!newValues.contains(curValue)) {
              appendStmt(retract, id1, a, curValue)
            }
          }
        }
        if (addNewValues || entity.get(a) != null) {
          newValues.foreach(newValue => appendStmt(add, id, a, newValue))
        }
      }

      def addToSeq(ns: String, attr: String, newValues: Seq[AnyRef], retractCur: Boolean) = {
        val a         = kw(ns, attr)
        val id1       = id
        var nextIndex = 0
        Peer.q("[:find ?ref :in $ ?e ?a :where [?e ?a ?ref]]", db, id1, a).forEach { row =>
          if (retractCur) {
            addRetractEntityStmt(row.get(0))
          } else {
            nextIndex += 1
          }
        }
        if (addNewValues || entity.get(a) != null) {
          val a_i = kw(s"$ns.$attr", "i_")
          val a_v = kw(s"$ns.$attr", "v_")
          var i   = nextIndex
          newValues.foreach { newValue =>
            val ref = newId
            appendStmt(add, id, a, ref)
            appendStmt(add, ref, a_i, i.asInstanceOf[AnyRef])
            appendStmt(add, ref, a_v, newValue)
            i += 1
          }
        }
      }

      def addToMap(ns: String, attr: String, newValues: Seq[AnyRef], retractCur: Boolean) = {
        val a      = kw(ns, attr)
        val a_k    = kw(s"$ns.$attr", "k_")
        val a_v    = kw(s"$ns.$attr", "v_")
        val id1    = id
        val curMap = mutable.Map.empty[AnyRef, AnyRef] // key -> ref
        Peer.q(
          s"""[:find ?ref ?k
             | :in $$ ?e ?a
             | :where [?e ?a ?ref]
             |        [?ref $a_k ?k]
             | ]""".stripMargin, db, id1, a).forEach { row =>

          if (retractCur) {
            addRetractEntityStmt(row.get(0))
          } else {
            val (ref, key) = (row.get(0), row.get(1))
            curMap(key) = ref
          }
        }
        if (addNewValues || entity.get(a) != null) {
          newValues.asInstanceOf[Seq[(AnyRef, AnyRef)]].foreach { case (k, v) =>
            curMap.get(k).fold {
              val ref = newId
              appendStmt(add, id, a, ref)
              appendStmt(add, ref, a_k, k)
              appendStmt(add, ref, a_v, v)
            } { ref =>
              // Only update value if key is already saved
              appendStmt(add, ref, a_v, v)
            }
          }
        }
      }


      def doRetract(ns: String, attr: String, retractValues: Seq[AnyRef], kind: String) = {
        val a = kw(ns, attr)
        if (retractValues.isEmpty) {
          val cur = entity.get(a)
          if (cur != null) {
            cur match {
              case set: jSet[_]         =>
                set.forEach {
                  case kw: Keyword          => appendStmt(retract, id, a, db.entity(kw).get(":db/id"))
                  case entityMap: EntityMap => appendStmt(retract, id, a, entityMap.get(":db/id"))
                  case v                    => appendStmt(retract, id, a, v.asInstanceOf[AnyRef])
                }
              case kw: Keyword          => appendStmt(retract, id, a, db.entity(kw).get(":db/id"))
              case entityMap: EntityMap => appendStmt(retract, id, a, entityMap.get(":db/id"))
              case v                    => appendStmt(retract, id, a, v)
            }
          }
        } else {
          kind match {
            case "" =>
              retractValues.foreach(retractValue =>
                appendStmt(retract, id, a, retractValue)
              )

            case "seq" =>
              val a_i       = kw(s"$ns.$attr", "i_")
              val a_v       = kw(s"$ns.$attr", "v_")
              val remaining = ListBuffer.empty[(AnyRef, Int, AnyRef)]
              // Get current Array ref entities with index and value
              Peer.q(
                s"""[:find ?ref ?i ?v
                   | :in $$ ?e ?a
                   | :where [?e ?a ?ref]
                   |        [?ref $a_i ?i]
                   |        [?ref $a_v ?v]]""".stripMargin, db, id, a
              ).forEach { row =>
                val (ref, i, v) = (row.get(0), row.get(1).asInstanceOf[Integer].toInt, row.get(2))
                if (retractValues.contains(v)) {
                  // Retract Array ref entity having value to be removed
                  addRetractEntityStmt(ref)
                } else {
                  // Cache remaining value and index for re-ordering
                  remaining += ((ref, i, v))
                }
              }
              // Re-index order for remaining values
              var i = 0
              remaining.sortBy(_._2).foreach { case (ref, _, _) =>
                appendStmt(add, ref, a_i, i.asInstanceOf[AnyRef])
                i += 1
              }

            case "map" =>
              val k_v   = kw(s"$ns.$attr", "k_")
              // Get current Array ref entities with index and value
              val check = Peer.q(
                s"""[:find ?ref ?k
                   | :in $$ ?e ?a
                   | :where [?e ?a ?ref]
                   |        [?ref $k_v ?k]]""".stripMargin, db, id, a
              )
              check.forEach { row =>
                if (retractValues.contains(row.get(1))) {
                  // Retract Array ref entity having value to be removed
                  addRetractEntityStmt(row.get(0))
                }
              }
          }
        }
      }
    }
  }

  private def uniqueIds(
    filterAttr: AttrOneTac,
    ns: String,
    attr: String
  ): Seq[AnyRef] = {
    val at = s":$ns/$attr"
    filterAttr match {
      case a: AttrOneTacID             => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacString         => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacInt            => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacLong           => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacFloat          => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacDouble         => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacBoolean        => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacBigInt         => a.vs.map(v => list(at, v.bigInteger.asInstanceOf[AnyRef]))
      case a: AttrOneTacBigDecimal     => a.vs.map(v => list(at, v.bigDecimal.asInstanceOf[AnyRef]))
      case a: AttrOneTacDate           => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacDuration       => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacInstant        => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacLocalDate      => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacLocalTime      => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacLocalDateTime  => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacOffsetTime     => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacOffsetDateTime => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacZonedDateTime  => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacUUID           => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacURI            => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacByte           => a.vs.map(v => list(at, v.toInt.asInstanceOf[AnyRef]))
      case a: AttrOneTacShort          => a.vs.map(v => list(at, v.toInt.asInstanceOf[AnyRef]))
      case a: AttrOneTacChar           => a.vs.map(v => list(at, v.toString.asInstanceOf[AnyRef]))
    }
  }

  override protected lazy val transformID             = (v: String) => v.toLong
  override protected lazy val transformString         = identity
  override protected lazy val transformInt            = identity
  override protected lazy val transformLong           = identity
  override protected lazy val transformFloat          = identity
  override protected lazy val transformDouble         = identity
  override protected lazy val transformBoolean        = identity
  override protected lazy val transformBigInt         = (v: BigInt) => v.bigInteger
  override protected lazy val transformBigDecimal     = (v: BigDecimal) => v.bigDecimal
  override protected lazy val transformDate           = identity
  override protected lazy val transformDuration       = (v: Duration) => v.toString
  override protected lazy val transformInstant        = (v: Instant) => v.toString
  override protected lazy val transformLocalDate      = (v: LocalDate) => v.toString
  override protected lazy val transformLocalTime      = (v: LocalTime) => v.toString
  override protected lazy val transformLocalDateTime  = (v: LocalDateTime) => v.toString
  override protected lazy val transformOffsetTime     = (v: OffsetTime) => v.toString
  override protected lazy val transformOffsetDateTime = (v: OffsetDateTime) => v.toString
  override protected lazy val transformZonedDateTime  = (v: ZonedDateTime) => v.toString
  override protected lazy val transformUUID           = identity
  override protected lazy val transformURI            = identity
  override protected lazy val transformByte           = (v: Byte) => v.toInt
  override protected lazy val transformShort          = (v: Short) => v.toInt
  override protected lazy val transformChar           = (v: Char) => v.toString
}