package molecule.datalog.datomic.transaction

import java.time._
import java.util.{ArrayList => jArrayList, List => jList}
import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ops.InsertOps
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.core.util.ModelUtils

trait Insert_datomic
  extends DatomicBase_JVM
    with InsertOps
    with DatomicDataType_JVM
    with ModelUtils
    with MoleculeLogging { self: ResolveInsert with InsertResolvers_ =>

  def getStmts(
    nsMap: Map[String, MetaNs],
    elements: List[Element],
    tpls: Seq[Product],
    idIndex: Int = 0,
    debug: Boolean = true
  ): Data = {
    initTxBase(elements, idIndex)
    val row2stmts = getResolver(nsMap, elements)
    tpls.foreach { tpl =>
      e = newId
      e0 = e
      // populate stmts
      row2stmts(tpl)
    }

    // Remove orphan ref ids
    val stmts1 = if (unusedRefIds.isEmpty) stmts else {
      val stmts1 = new jArrayList[jList[AnyRef]](stmts.size())
      stmts.forEach { stmt =>
        if (!unusedRefIds.contains(stmt.get(3))) {
          stmts1.add(stmt)
        }
      }
      stmts1
    }

    if (debug) {
      val insertStrs = "INSERT:" +: elements :+ "" :+ stmts1.toArray().mkString("\n")
      logger.debug(insertStrs.mkString("\n").trim)
    }

    val lastIndex = stmts1.size - 1
    val stmts2    = if (lastIndex != -1
      && stmts1.get(lastIndex).get(3).toString.startsWith("#db/id")) {
      // remove orphan ref datom - can we include this check it in the algorithm above?
      stmts1.remove(lastIndex)
      stmts1
    } else stmts1

    stmts2
  }

  override protected def addOne[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      unusedRefIds -= e
      usedRefIds += e
      appendStmt(add, e, a,
        transformValue(tpl.productElement(tplIndex).asInstanceOf[T]).asInstanceOf[AnyRef]
      )
  }

  override protected def addOneOpt[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex) match {
        case Some(value) =>
          unusedRefIds -= e
          usedRefIds += e
          appendStmt(add, e, a, transformValue(value.asInstanceOf[T]).asInstanceOf[AnyRef])
        case None        =>
          if (!usedRefIds.contains(e)) {
            unusedRefIds += e
          }
          () // no statement to insert
      }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex).asInstanceOf[Set[_]] match {
        case set if set.isEmpty => ()
        case set                =>
          unusedRefIds -= e
          usedRefIds += e
          set.foreach { value =>
            appendStmt(add, e, a, transformValue(value.asInstanceOf[T]).asInstanceOf[AnyRef])
          }
      }
  }

  override protected def addSetOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex) match {
        case Some(set: Set[_]) =>
          unusedRefIds -= e
          usedRefIds += e
          set.foreach(value =>
            appendStmt(add, e, a, transformValue(value.asInstanceOf[T]).asInstanceOf[AnyRef])
          )
        case None              =>
          if (!usedRefIds.contains(e)) {
            unusedRefIds += e
          }
          () // no statement to insert
      }
  }

  override protected def addSeq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val a   = kw(ns, attr)
    val a_i = kw(s"$ns.$attr", "i_")
    val a_v = kw(s"$ns.$attr", "v_")
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex).asInstanceOf[Seq[_]] match {
        case seq if seq.nonEmpty =>
          unusedRefIds -= e
          usedRefIds += e
          var i = 0
          seq.foreach { value =>
            val ref = newId
            appendStmt(add, e, a, ref)
            appendStmt(add, ref, a_i, i.asInstanceOf[AnyRef])
            appendStmt(add, ref, a_v, transformValue(value.asInstanceOf[T]).asInstanceOf[AnyRef])
            i += 1
          }

        case _ => () // no statement to insert
      }
  }

  override protected def addSeqOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val a   = kw(ns, attr)
    val a_i = kw(s"$ns.$attr", "i_")
    val a_v = kw(s"$ns.$attr", "v_")
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex) match {
        case Some(seq: Seq[_]) if seq.nonEmpty =>
          unusedRefIds -= e
          usedRefIds += e
          var i = 0
          seq.foreach { value =>
            val ref = newId
            appendStmt(add, e, a, ref)
            appendStmt(add, ref, a_i, i.asInstanceOf[AnyRef])
            appendStmt(add, ref, a_v, transformValue(value.asInstanceOf[T]).asInstanceOf[AnyRef])
            i += 1
          }

        case _ => () // no statement to insert
      }
  }

  override protected def addByteArray(
    ns: String,
    attr: String,
    tplIndex: Int,
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex) match {
        case array: Array[_] if array.nonEmpty => appendStmt(add, e, a, array.asInstanceOf[AnyRef])
        case Some(array: Array[_])             => appendStmt(add, e, a, array.asInstanceOf[AnyRef])
        case _                                 => () // no statement to insert
      }
  }


  override protected def addMap[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val a   = kw(ns, attr)
    val a_k = kw(s"$ns.$attr", "k_")
    val a_v = kw(s"$ns.$attr", "v_")
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex).asInstanceOf[Map[String, _]] match {
        case map if map.nonEmpty =>
          unusedRefIds -= e
          usedRefIds += e
          map.foreach { case (k, v) =>
            val ref = newId
            appendStmt(add, e, a, ref)
            appendStmt(add, ref, a_k, validKey(k))
            appendStmt(add, ref, a_v, transformValue(v.asInstanceOf[T]).asInstanceOf[AnyRef])
          }

        case _ => () // no statement to insert
      }
  }

  override protected def addMapOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val a   = kw(ns, attr)
    val a_k = kw(s"$ns.$attr", "k_")
    val a_v = kw(s"$ns.$attr", "v_")
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex) match {
        case Some(map: Map[_, _]) if map.nonEmpty =>
          unusedRefIds -= e
          usedRefIds += e
          map.foreach { case (k, v) =>
            val ref = newId
            appendStmt(add, e, a, ref)
            appendStmt(add, ref, a_k, validKey(k.asInstanceOf[String]))
            appendStmt(add, ref, a_v, transformValue(v.asInstanceOf[T]).asInstanceOf[AnyRef])
          }

        case _ => () // no statement to insert
      }
  }

  override protected def addRef(
    ns: String,
    refAttr: String,
    refNs: String,
    card: Card,
  ): Product => Unit = {
    val a = kw(ns, refAttr)
    (_: Product) =>
      backRefs = backRefs + (ns -> e)
      unusedRefIds -= e
      usedRefIds += e
      stmt = stmtList
      stmt.add(add)
      stmt.add(e)
      stmt.add(a)
      e = newId
      unusedRefIds += e
      stmt.add(e)
      stmts.add(stmt)
  }

  override protected def addBackRef(backRefNs: String): Product => Unit = {
    (_: Product) =>
      e = backRefs(backRefNs)
  }

  override protected def addOptRef(
    nsMap: Map[String, MetaNs],
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    elements: List[Element]
  ): Product => Unit = ???

  override protected def addNested(
    nsMap: Map[String, MetaNs],
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    nestedElements: List[Element]
  ): Product => Unit = {
    // Recursively resolve nested data
    val nested2stmts = getResolver(nsMap, nestedElements)
    val lastIsSet    = nestedElements.last.isInstanceOf[AttrSet]
    countValueAttrs(nestedElements) match {
      case 1 => // Nested arity-1 values
        (tpl: Product) => {
          val values       = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
          val nestedBaseId = e
          values.foreach { value =>
            e = nestedBaseId
            val nestedTpl = Tuple1(value)
            addRef(ns, refAttr, refNs, CardOne)(nestedTpl)
            unusedRefIds -= e
            e0 = e
            nested2stmts(nestedTpl)
          }
        }

      // Exclude tuples with empty last Set
      // (to avoid orphan relationships)
      case _ if lastIsSet =>
        val lastTplIndex = nestedElements.collect { case _: Attr => 1 }.sum - 1
        (tpl: Product) => {
          val multiple     = tpl.productArity > 1
          val nestedTpls   = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          val nestedBaseId = e
          nestedTpls.foreach { nestedTpl =>
            def process(): Unit = {
              e = nestedBaseId
              addRef(ns, refAttr, refNs, CardOne)(nestedTpl)
              unusedRefIds -= e
              e0 = e
              nested2stmts(nestedTpl)
            }
            nestedTpl.productElement(lastTplIndex) match {
              case set: Set[_] if set.nonEmpty || multiple => process()
              case _: Option[_]                            => process()
              case _                                       => ()
            }
          }
        }

      case _ =>
        (tpl: Product) => {
          val nestedTpls   = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          val nestedBaseId = e
          nestedTpls.foreach { nestedTpl =>
            e = nestedBaseId
            addRef(ns, refAttr, refNs, CardOne)(nestedTpl)
            unusedRefIds -= e
            e0 = e
            nested2stmts(nestedTpl)
          }
        }
    }
  }

  override protected lazy val transformID             = identity
  override protected lazy val transformString         = identity
  override protected lazy val transformInt            = (v: Any) => v.asInstanceOf[Int].toLong
  override protected lazy val transformLong           = identity
  override protected lazy val transformFloat          = identity
  override protected lazy val transformDouble         = identity
  override protected lazy val transformBoolean        = boolean2java
  override protected lazy val transformBigInt         = bigInt2java
  override protected lazy val transformBigDecimal     = bigDec2java
  override protected lazy val transformDate           = identity
  override protected lazy val transformDuration       = (v: Any) => v.asInstanceOf[Duration].toString
  override protected lazy val transformInstant        = (v: Any) => v.asInstanceOf[Instant].toString
  override protected lazy val transformLocalDate      = (v: Any) => v.asInstanceOf[LocalDate].toString
  override protected lazy val transformLocalTime      = (v: Any) => v.asInstanceOf[LocalTime].toString
  override protected lazy val transformLocalDateTime  = (v: Any) => v.asInstanceOf[LocalDateTime].toString
  override protected lazy val transformOffsetTime     = (v: Any) => v.asInstanceOf[OffsetTime].toString
  override protected lazy val transformOffsetDateTime = (v: Any) => v.asInstanceOf[OffsetDateTime].toString
  override protected lazy val transformZonedDateTime  = (v: Any) => v.asInstanceOf[ZonedDateTime].toString
  override protected lazy val transformUUID           = identity
  override protected lazy val transformURI            = identity
  override protected lazy val transformByte           = byte2java
  override protected lazy val transformShort          = short2java
  override protected lazy val transformChar           = char2java
}