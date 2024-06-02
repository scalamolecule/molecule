package molecule.datalog.datomic.transaction

import java.lang.{Long => jLong}
import java.time._
import java.util.{List => jList, Map => jMap, Set => jSet}
import clojure.lang.Keyword
import datomic.query.EntityMap
import datomic.{Database, Peer}
import molecule.base.ast.CardOne
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.core.util.JavaConversions
import molecule.core.validation.TxModelValidation
import molecule.datalog.core.query.Model2DatomicQuery
import molecule.datalog.datomic.facade.DatomicConn_JVM
import molecule.datalog.datomic.query.DatomicQueryResolveOffset
import scala.collection.mutable.ListBuffer

trait Update_datomic
  extends DatomicBase_JVM
    with UpdateOps
    with UpdateFilters
    with ModelTransformations_
    with MoleculeLogging
    with JavaConversions { self: ResolveUpdate =>

  // Use current data rows for comparison
  private val rowResolvers = ListBuffer.empty[jList[AnyRef] => Unit]
  private var rowSize      = 0
  private var attrIndex    = 0

  private var db: Database = null

  // Cache entities for each namespace to be able to go back with BackRef and use previous namespace entities
  private var idLists = List.empty[List[AnyRef]] // Long or String (#db/id[db.part/user -1])

  private var requiredNsPaths = List(List.empty[String])
  private var currentNsPath   = List.empty[String]


  def getStmts(
    conn: DatomicConn_JVM,
    elements: List[Element],
    isRpcCall: Boolean = false,
    debug: Boolean = true
  ): Data = {
    db = conn.peerConn.db()
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

    //    println("------ elements --------")
    //    elements.foreach(println)

    val (filterElements1, requiredNsPaths1) = if (isUpsert)
      getUpsertFilters(elements.reverse)
    else
      getUpdateFilters(elements.reverse)

    requiredNsPaths = requiredNsPaths1

    val filters = AttrOneManID(getInitialNs(elements), "id", V) :: filterElements1

    //    println("------ filters --------")
    //    filters.foreach(println)
    //    println("------ requiredNsPaths: " + requiredNsPaths)

    val filterMatchRows = new DatomicQueryResolveOffset[Any](
      filters, None, None, None, new Model2DatomicQuery[Any](filters)
    ).getRawData(conn, validate = false)

    //    println("--------- filterMatchRows")
    //    filterMatchRows.forEach(row => println(row))

    if (!filterMatchRows.isEmpty) {
      val it = filterMatchRows.iterator()
      if (it.hasNext) {
        val firstRow = it.next()
        // First namespace entity id of first row
        ids = List(firstRow.get(0))
        idLists = List(ids)
        rowSize = firstRow.size
      }
    }

    // Resolve update model
    resolve(elements)

    // Resolve row updates
    filterMatchRows.forEach { row =>
      ids = List(row.get(0))
      attrIndex = 1
      // Resolve each row
      rowResolvers.foreach(_(row))
    }

    if (debug) {
      val action     = if (isUpsert) "UPSERT" else "UPDATE"
      val updateStrs = s"$action:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
      logger.debug(updateStrs.mkString("\n").trim)
    }

    // Datomic statements created from row resolutions
    stmts
  }

  override protected def updateOne[T](
    ns: String,
    attr: String,
    owner: Boolean,
    vs: Seq[T],
    transformValue: T => Any,
  ): Unit = {
    val a = kw(ns, attr)
    vs match {
      case Seq(v) =>
        rowResolvers += { _ =>
          ids.foreach(e => appendStmt(add, e, a, transformValue(v).asInstanceOf[AnyRef]))
          attrIndex += 1
        }
      case Nil    =>
        rowResolvers += {
          case row: jList[AnyRef] if isUpsert =>
            val currentValue = row.get(attrIndex).asInstanceOf[jMap[_, _]].get(a) match {
              case map: jMap[_, _] => map.get(dbId)
              case v               => v
            }
            ids.foreach(e => appendStmt(retract, e, a, currentValue.asInstanceOf[AnyRef]))
            attrIndex += 1

          case row: jList[AnyRef] =>
            val currentValue = row.get(attrIndex) match {
              case map: jMap[_, _] => map.get(dbId)
              case v               => v
            }
            ids.foreach(e => appendStmt(retract, e, a, currentValue.asInstanceOf[AnyRef]))
            attrIndex += 1
        }
      case vs     => throw ExecutionError(
        s"Can only update one value for attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }


  override protected def updateSetEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    val a = kw(ns, attr)
    if (set.nonEmpty) {
      val newSet = set.map(transformValue(_).asInstanceOf[AnyRef])
      rowResolvers += { (row: jList[AnyRef]) =>
        ids.foreach {
          case tempId: String => newSet.foreach(newValues => appendStmt(add, tempId, a, newValues))
          case e              =>
            // Resolved entity id
            if (attrIndex < rowSize) {
              // Cached values with known ref
              val oldSet = cachedValues(row, optRefNs).toSet
              oldSet.diff(newSet).foreach(oldValues => appendStmt(retract, e, a, oldValues))
              newSet.diff(oldSet).foreach(newValues => appendStmt(add, e, a, newValues))
            } else {
              // Unknown ref, traverse to value with Datomic entity api
              db.entity(e).asInstanceOf[EntityMap].get(a) match {
                case set: jSet[_] =>
                  val oldSet = set.toArray().toSet
                  oldSet.diff(newSet).foreach(oldValues => appendStmt(retract, e, a, oldValues))
                  newSet.diff(oldSet).foreach(newValues => appendStmt(add, e, a, newValues))
                case _            =>
                  newSet.foreach(newValues => appendStmt(add, e, a, newValues))
              }
            }
        }
        attrIndex += 1
      }
    } else {
      // Retract current Set
      rowResolvers += { (row: jList[AnyRef]) =>
        cachedValues(row, optRefNs).foreach(oldValue =>
          ids.foreach(e => appendStmt(retract, e, a, oldValue))
        )
        attrIndex += 1
      }
    }
  }

  override protected def updateSetAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    if (set.nonEmpty) {
      val a         = kw(ns, attr)
      val addValues = set.map(transformValue(_).asInstanceOf[AnyRef])
      rowResolvers += { _ =>
        addValues.foreach(addValue =>
          ids.foreach(e => appendStmt(add, e, a, addValue)))
        attrIndex += 1
      }
    }
  }

  override protected def updateSetRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit = {
    if (set.nonEmpty) {
      val a            = kw(ns, attr)
      val removeValues = set.map(transformValue(_).asInstanceOf[AnyRef])
      rowResolvers += { _ =>
        removeValues.foreach(removeValue =>
          ids.foreach(e => appendStmt(retract, e, a, removeValue)))
        attrIndex += 1
      }
    }
  }

  override protected def updateSeqEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    val a = kw(ns, attr)
    if (seq.nonEmpty) {
      val a_i    = kw(s"$ns.$attr", "i_")
      val a_v    = kw(s"$ns.$attr", "v_")
      val newSeq = seq.map(v => transformValue(v).asInstanceOf[AnyRef])
      rowResolvers += { (row: jList[AnyRef]) =>
        retractCurrentSeqValues(row, a, optRefNs)

        // Assert new values
        var i = 0
        ids.foreach { e =>
          newSeq.foreach { newValue =>
            val ref = newId
            appendStmt(add, e, a, ref)
            appendStmt(add, ref, a_i, i.asInstanceOf[AnyRef])
            appendStmt(add, ref, a_v, newValue)
            i += 1
          }
        }
        attrIndex += 1
      }
    } else {
      rowResolvers += { (row: jList[AnyRef]) =>
        retractCurrentSeqValues(row, a, optRefNs)
        attrIndex += 1
      }
    }
  }

  private def retractCurrentSeqValues(
    row: jList[AnyRef], a: Keyword, optRefNs: Option[String]
  ): Unit = {
    if (attrIndex < rowSize) {
      // Cached values with known ref
      Peer.q(
        "[:find ?ref :in $ [?e ...] ?a [?v ...] :where [?e ?a ?ref][?ref ?v_ ?v]]",
        db, ids.asJava, a, cachedValues(row, optRefNs).asJava
      ).forEach(row =>
        addRetractEntityStmt(row.get(0))
      )
    } else {
      // Unknown ref, traverse to synthetic entity with Datomic entity api
      retractSyntheticEntitiesByLookup(a)
    }
  }


  override protected def updateSeqAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    if (seq.nonEmpty) {
      val a         = kw(ns, attr)
      val a_i       = kw(s"$ns.$attr", "i_")
      val a_v       = kw(s"$ns.$attr", "v_")
      val addValues = seq.map(transformValue(_).asInstanceOf[AnyRef])
      rowResolvers += { _ =>
        ids.foreach { e =>
          val count = e match {
            case tempId: String => 0
            case e              =>
              val it = Peer.q("[:find (count ?ref) :in $ ?e ?a :where [?e ?a ?ref]]", db, e, a).iterator()
              if (it.hasNext) it.next().get(0).asInstanceOf[Integer].toInt else 0
          }
          var i     = count
          addValues.foreach { newValue =>
            val ref = newId
            appendStmt(add, e, a, ref)
            appendStmt(add, ref, a_i, i.asInstanceOf[AnyRef])
            appendStmt(add, ref, a_v, newValue)
            i += 1
          }
        }
        attrIndex += 1
      }
    }
  }

  override protected def updateSeqRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit = {
    if (seq.nonEmpty) {
      rowResolvers += { (row: jList[AnyRef]) =>
        //        println("ROW: " + row)

        retractSeqValues(row, ns, attr, seq, transformValue)
        attrIndex += 1
      }
    }
  }

  private def retractSeqValues[T](
    row: jList[AnyRef], ns: String, attr: String, seq: Seq[T], transformValue: T => Any
  ): Unit = {
    val a            = kw(ns, attr)
    val i_           = kw(s"$ns.$attr", "i_")
    val v_           = kw(s"$ns.$attr", "v_")
    val removeValues = seq.map(transformValue(_).asInstanceOf[AnyRef])
    val remaining    = new ListBuffer[(AnyRef, AnyRef)]()

    def reIndex(): Unit = {
      var i = 0
      remaining.sortBy(_._2.toString.toInt).foreach { case (ref, _) =>
        appendStmt(add, ref, i_, i.asInstanceOf[AnyRef])
        i += 1
      }
      remaining.clear()
    }

    if (attrIndex < rowSize) {
      Peer.q(
        "[:find ?ref ?i ?v :in $ [?e ...] ?a ?i_ ?v_ :where [?e ?a ?ref][?ref ?i_ ?i][?ref ?v_ ?v]]",
        db, ids.asJava, a, i_, v_
      ).forEach { row =>
        val (ref, i, v) = (row.get(0), row.get(1), row.get(2))
        if (removeValues.contains(v)) {
          addRetractEntityStmt(ref)
        } else {
          remaining += ((ref, i))
        }
      }
      reIndex()
    } else {
      // Unknown ref, traverse to synthetic entity with Datomic entity api
      ids.foreach {
        case tempId: String => ()
        case e              =>
          // Unknown ref, traverse to value with Datomic entity api
          db.entity(e) match {
            case entity: EntityMap =>
              entity.get(a) match {
                case syntheticEntities: jSet[_] =>
                  syntheticEntities.forEach { case syntheticEntity: EntityMap =>
                    val ref = syntheticEntity.get(dbId)
                    if (removeValues.contains(syntheticEntity.get(v_))) {
                      addRetractEntityStmt(ref)
                    } else {
                      remaining += ((ref, syntheticEntity.get(i_)))
                    }
                  }
                  reIndex()
                case _                          => ()
              }
            case _                 => ()
          }
      }
    }
  }

  override def updateByteArray(ns: String, attr: String, newByteArray: Array[Byte]): Unit = {
    val a = kw(ns, attr)
    if (newByteArray.nonEmpty) {
      val newValues = newByteArray
      rowResolvers += { (row: jList[AnyRef]) =>
        val oldByteArray = if (isUpsert)
          row.get(attrIndex).asInstanceOf[jMap[_, _]].values.iterator().next()
        else
          row.get(attrIndex).asInstanceOf[Array[_]]
        ids.foreach { e =>
          appendStmt(retract, e, a, oldByteArray.asInstanceOf[AnyRef])
          appendStmt(add, e, a, newValues.asInstanceOf[AnyRef])
        }
        attrIndex += 1
      }
    } else {
      rowResolvers += { (row: jList[AnyRef]) =>
        val oldByteArray = if (isUpsert)
          row.get(attrIndex).asInstanceOf[jMap[_, _]].values.iterator().next()
        else
          row.get(attrIndex).asInstanceOf[Array[_]]
        ids.foreach(e => appendStmt(retract, e, a, oldByteArray.asInstanceOf[AnyRef]))
        attrIndex += 1
      }
    }
  }


  override protected def updateMapEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    noValue: Boolean,
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    val a  = kw(ns, attr)
    val k_ = kw(s"$ns.$attr", "k_")
    val v_ = kw(s"$ns.$attr", "v_")
    if (noValue) {
      rowResolvers += { (row: jList[AnyRef]) =>
        retractCurrentMapPairs(row, a, k_, optRefNs)
        attrIndex += 1
      }
    } else {
      val newPairs = map.map {
        case (k, v) => (validKey(k), transformValue(v).asInstanceOf[AnyRef])
      }
      rowResolvers += { (row: jList[AnyRef]) =>
        retractCurrentMapPairs(row, a, k_, optRefNs)

        // Assert new pairs
        var i = 0
        ids.foreach { e =>
          newPairs.foreach { case (key, newValue) =>
            val ref = newId
            appendStmt(add, e, a, ref)
            appendStmt(add, ref, k_, key)
            appendStmt(add, ref, v_, newValue)
            i += 1
          }
        }
        attrIndex += 1
      }
    }
  }

  private def retractCurrentMapPairs(row: jList[AnyRef], a: Keyword, a_k: Keyword, optRefNs: Option[String]): Unit = {
    if (attrIndex < rowSize) {
      // Cached values with known ref
      val cached = cachedValues(row, optRefNs)
      val keys   = if (isUpsert) {
        cached.map(_.asInstanceOf[jMap[_, _]].get(a_k)).asJava
      } else {
        cached.map(_.asInstanceOf[jList[_]].get(0)).asJava
      }
      Peer.q(
        "[:find ?ref :in $ [?e ...] ?a ?a_k [?k ...] :where [?e ?a ?ref][?ref ?a_k ?k]]",
        db, ids.asJava, a, a_k, keys
      ).forEach(pairEntity =>
        addRetractEntityStmt(pairEntity.get(0))
      )
    } else {
      // Unknown ref, traverse to value with Datomic entity api
      retractSyntheticEntitiesByLookup(a)
    }
  }


  private def retractSyntheticEntitiesByLookup(a: Keyword): Unit = {
    ids.foreach {
      case tempId: String => ()
      case e              =>
        // Unknown ref, traverse to value with Datomic entity api
        db.entity(e) match {
          case entity: EntityMap => entity.get(a) match {
            case syntheticEntities: jSet[_] =>
              syntheticEntities.forEach { case syntheticEntity: EntityMap =>
                addRetractEntityStmt(syntheticEntity.get(dbId))
              }
            case _                          => ()
          }
          case _                 => ()
        }
    }
  }


  private def cachedValues(row: jList[AnyRef], optRefNs: Option[String]): List[AnyRef] = {
    (if (optRefNs.isEmpty) {
      row.get(attrIndex) match {
        case vs: jSet[_]  => if (isUpsert)
          vs.iterator().next().asInstanceOf[jList[_]].toArray()
        else
          vs.toArray()
        case vs: jList[_] => vs.toArray()
        case vs: Array[_] => vs.asInstanceOf[Array[AnyRef]]
      }
    } else {
      if (isUpsert)
        row.get(attrIndex)
          .asInstanceOf[jSet[_]].iterator().next()
          .asInstanceOf[jList[_]].toArray().map {
            case map: jMap[_, _] => map.get(dbId).asInstanceOf[AnyRef]
          }
      else
        row.get(attrIndex).asInstanceOf[jSet[_]].toArray
    }).toList
  }


  override protected def updateMapAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): Unit = {
    if (map.nonEmpty) {
      val a        = kw(ns, attr)
      val k_       = kw(s"$ns.$attr", "k_")
      val v_       = kw(s"$ns.$attr", "v_")
      val newPairs = map.map {
        case (k, v) => (validKey(k), transformValue(v).asInstanceOf[AnyRef])
      }
      rowResolvers += { (row: jList[AnyRef]) =>
        // Retract old pairs with same keys as new pairs (if any)
        retractPairs(map, row, a, k_)

        // Add new pairs
        ids.foreach { e =>
          var i = 0
          newPairs.foreach { case (key, newValue) =>
            val ref = newId
            appendStmt(add, e, a, ref)
            appendStmt(add, ref, k_, key)
            appendStmt(add, ref, v_, newValue)
            i += 1
          }
        }
        attrIndex += 1
      }
    }
  }

  private def retractPairs[T](map: Map[String, T], row: jList[AnyRef], a: Keyword, a_k: Keyword): Unit = {
    val keys = map.keys.toList
    if (attrIndex < rowSize) {
      // Retract synthetic entities where key match new keys
      Peer.q(
        "[:find ?ref :in $ [?e ...] ?a ?a_k [?k ...] :where [?e ?a ?ref][?ref ?a_k ?k]]",
        db, ids.asJava, a, a_k, keys.asJava
      ).forEach(pairEntity =>
        addRetractEntityStmt(pairEntity.get(0))
      )
    } else {
      ids.foreach {
        case tempId: String => ()
        case e              =>
          // Unknown ref, traverse to matching pairs with Datomic entity api
          db.entity(e) match {
            case em: EntityMap => em.get(a) match {
              case syntheticEntities: jSet[_] =>
                syntheticEntities.forEach { case syntheticEntity: EntityMap =>
                  if (keys.contains(syntheticEntity.get(a_k).toString)) {
                    addRetractEntityStmt(syntheticEntity.get(dbId))
                  }
                }
              case _                          => ()
            }
            case _             => ()
          }
      }
    }
  }

  override protected def updateMapRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): Unit = {
    if (map.nonEmpty) {
      val a  = kw(ns, attr)
      val k_ = kw(s"$ns.$attr", "k_")
      rowResolvers += { (row: jList[AnyRef]) =>
        // Retract old pairs with same keys as new pairs (if any)
        retractPairs(map, row, a, k_)
        attrIndex += 1
      }
    }
  }


  override def handleIds(ns: String, ids1: Seq[String]): Unit = ()
  override def handleFilterAttr[T <: Attr with Tacit](filterAttr: T): Unit = ()

  override def handleRefNs(ref: Ref): Unit = {
    val Ref(ns, refAttr0, refNs, card, _, _) = ref

    currentNsPath = currentNsPath match {
      case Nil => List(ns, refAttr0, refNs)
      case cur => cur ++ List(refAttr0, refNs)
    }
    //    println("------------")
    //    println(requiredNsPaths)
    //    println(currentNsPath)
    val refAttr = kw(ns, refAttr0)
    rowResolvers += {
      case row: jList[AnyRef] if isUpsert =>
        ids = if (attrIndex < rowSize) {
          //          println("---------")
          //          println(row)
          //          println(attrIndex)
          row.get(attrIndex) match {
            case null =>
              // Don't add ref if it's not required (when removing only)
              if (requiredNsPaths.contains(currentNsPath)) {
                // Add ref to next namespace
                val ref = newId
                appendStmt(add, ids.head, refAttr, ref)
                List(ref)
              } else Nil

            case e: jLong => List(e)

            case map: jMap[_, _] =>
              if (card == CardOne) {
                // Current ref
                List(map.get(refAttr).asInstanceOf[jMap[_, _]].get(dbId).asInstanceOf[AnyRef])
              } else {
                // Extract entity ids from vector of current card-many refs
                map.get(refAttr).asInstanceOf[jList[_]].asScala.toList.map(entityMap =>
                  entityMap.asInstanceOf[jMap[_, _]].get(dbId).asInstanceOf[AnyRef]
                )
              }
          }
        } else {
          // Get currently unknown ref from Datomic database entity lookup
          idLists.last.map { e =>
            db.entity(e).asInstanceOf[EntityMap].get(refAttr) match {
              case set: jSet[_]  => set.iterator.next.asInstanceOf[EntityMap].get(dbId)
              case em: EntityMap => em.get(dbId)
            }
          }
        }
        attrIndex += 1
        idLists = idLists :+ ids

      case row: jList[AnyRef] =>
        if (attrIndex < rowSize)
          ids = List(row.get(attrIndex).asInstanceOf[jLong])
        attrIndex += 1
        idLists = idLists :+ ids
    }
  }

  override def handleBackRef(backRef: BackRef): Unit = {
    currentNsPath = currentNsPath.init
    rowResolvers += { _ =>
      idLists = idLists.init
      ids = idLists.last
    }
  }


  // Helpers -------------------------------------------------------------------

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