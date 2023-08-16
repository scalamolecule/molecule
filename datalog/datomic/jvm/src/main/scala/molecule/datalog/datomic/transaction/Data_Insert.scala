package molecule.datalog.datomic.transaction

import java.util.{ArrayList => jArrayList, List => jList}
import molecule.base.ast.SchemaAST._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ops.InsertOps
import molecule.core.transaction.{ResolveInsert, InsertResolvers_, ResolveSave}
import molecule.core.util.ModelUtils

trait Data_Insert
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
    val (mainElements, txElements) = separateTxElements(elements)

    //    println("----------")
    //    mainElements.foreach(println)
    //    println("----------")
    //    txElements.foreach(println)

    val row2stmts = getResolver(nsMap, mainElements)
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


    if (txElements.nonEmpty) {
      val txStmts = (new ResolveSave(true) with Data_Save)
        .getRawStmts(txElements, datomicTx, false)
      stmts1.addAll(txStmts)
    }
    if (debug) {
      val insertStrs = "INSERT:" +: elements :+ "" :+ stmts1.toArray().mkString("\n")
      logger.debug(insertStrs.mkString("\n").trim)
    }
    stmts1
  }

  override protected def addOne[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    handleValue: T => Any
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      unusedRefIds -= e
      usedRefIds += e
      appendStmt(add, e, a,
        transformValue(tpl.productElement(tplIndex).asInstanceOf[T]).asInstanceOf[AnyRef])
  }

  override protected def addOneOpt[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    handleValue: T => Any
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
    //    set2array: Set[T] => Array[AnyRef],
    set2array: Set[Any] => Array[AnyRef],
    refNsOpt: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      unusedRefIds -= e
      usedRefIds += e
      tpl.productElement(tplIndex).asInstanceOf[Set[_]].foreach { value =>
        appendStmt(add, e, a, transformValue(value.asInstanceOf[T]).asInstanceOf[AnyRef])
      }
  }

  override protected def addSetOpt[T](
    ns: String,
    attr: String,
    //    set2array: Set[T] => Array[AnyRef],
    set2array: Set[Any] => Array[AnyRef],
    refNsOpt: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
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

  override protected def addRef(
    ns: String,
    refAttr: String,
    refNs: String,
    card: Card
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
      stmt.add(e)
      stmts.add(stmt)
  }

  override protected def addBackRef(backRefNs: String): Product => Unit = {
    (_: Product) =>
      e = backRefs(backRefNs)
  }

  override protected def addComposite(
    nsMap: Map[String, MetaNs],
    outerTpl: Int,
    tplIndex: Int,
    compositeElements: List[Element]
  ): Product => Unit = {
    hasComposites = true
    val composite2stmts = getResolver(nsMap, compositeElements, outerTpl)
    // Start from initial entity id for each composite sub group
    countValueAttrs(compositeElements) match {
      case 1 => (tpl: Product) =>
        e = e0
        composite2stmts(Tuple1(tpl.productElement(tplIndex)))
      case _ => (tpl: Product) =>
        e = e0
        composite2stmts(tpl.productElement(tplIndex).asInstanceOf[Product])
    }
  }

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
    countValueAttrs(nestedElements) match {
      case 1 => // Nested arity-1 values
        (tpl: Product) => {
          val values       = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
          val nestedBaseId = e
          values.foreach { value =>
            e = nestedBaseId
            val nestedTpl = Tuple1(value)
            addRef(ns, refAttr, refNs, CardOne)(nestedTpl)
            e0 = e
            nested2stmts(nestedTpl)
          }
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls   = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          val nestedBaseId = e
          nestedTpls.foreach { nestedTpl =>
            e = nestedBaseId
            addRef(ns, refAttr, refNs, CardOne)(nestedTpl)
            e0 = e
            nested2stmts(nestedTpl)
          }
        }
    }
  }

  // Save Int as Long in Datomic
  override protected lazy val transformString     = identity
  override protected lazy val transformInt        = (v: Any) => v.asInstanceOf[Int].toLong
  override protected lazy val transformLong       = identity
  override protected lazy val transformFloat      = identity
  override protected lazy val transformDouble     = identity
  override protected lazy val transformBoolean    = boolean2java
  override protected lazy val transformBigInt     = bigInt2java
  override protected lazy val transformBigDecimal = bigDec2java
  override protected lazy val transformDate       = identity
  override protected lazy val transformUUID       = identity
  override protected lazy val transformURI        = identity
  override protected lazy val transformByte       = byte2java
  override protected lazy val transformShort      = short2java
  override protected lazy val transformChar       = char2java
}