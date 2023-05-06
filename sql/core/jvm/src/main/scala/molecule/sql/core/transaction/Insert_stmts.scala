package molecule.sql.core.transaction

import molecule.base.ast.SchemaAST._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ops.InsertOps
import molecule.core.transaction.{InsertExtraction, InsertResolvers_, SaveExtraction}
import molecule.core.util.ModelUtils

trait Insert_stmts
  extends SqlTxBase_JVM
    with InsertOps
    with SqlDataType_JVM
    with ModelUtils
    with MoleculeLogging { self: InsertExtraction with InsertResolvers_ =>

  def getStmts(
    nsMap: Map[String, MetaNs],
    elements: List[Element],
    tpls: Seq[Product],
    eidIndex: Int = 0,
    debug: Boolean = true
  ): Data = {
//    initTxBase(elements, eidIndex)
//    val (mainElements, txMetaElements) = separateTxElements(elements)
//    val row2stmts                      = getResolver(nsMap, mainElements)
//    tpls.foreach { tpl =>
//      e = newId
//      e0 = e
//      // populate `stmts`
//      row2stmts(tpl)
//    }
//    if (txMetaElements.nonEmpty) {
//      val txMetaStmts = (new SaveExtraction(true) with Save_stmts)
//        .getRawStmts(txMetaElements, datomicTx, false)
//      stmts.addAll(txMetaStmts)
//    }
//    if (debug) {
//      val insertStrs = "INSERT:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
//      logger.debug(insertStrs.mkString("\n").trim)
//    }
//    stmts
    ???
  }

  override protected def addComposite(
    nsMap: Map[String, MetaNs],
    tpl: Int,
    tplIndex: Int,
    compositeElements: List[Element]
  ): Product => Unit = {
    hasComposites = true
    val composite2stmts = getResolver(nsMap, compositeElements, tpl)
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
            addRef(ns, refAttr)(nestedTpl)
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
            addRef(ns, refAttr)(nestedTpl)
            e0 = e
            nested2stmts(nestedTpl)
          }
        }
    }
  }

  override protected def addV[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    scala2dbTpe: T => Any,
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      appendStmt(add, e, a,
        scala2dbTpe(tpl.productElement(tplIndex).asInstanceOf[T]).asInstanceOf[AnyRef])
  }

  override protected def addOptV[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    scala2dbTpe: T => Any,
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex) match {
        case Some(value) => appendStmt(add, e, a,
          scala2dbTpe(value.asInstanceOf[T]).asInstanceOf[AnyRef])
        case _           => () // no statement to insert
      }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    scala2dbTpe: T => Any,
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex).asInstanceOf[Set[_]].foreach { value =>
        appendStmt(add, e, a, scala2dbTpe(value.asInstanceOf[T]).asInstanceOf[AnyRef])
      }
  }

  override protected def addOptSet[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    scala2dbTpe: T => Any,
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex) match {
        case Some(set: Set[_]) =>
          set.foreach(value =>
            appendStmt(add, e, a, scala2dbTpe(value.asInstanceOf[T]).asInstanceOf[AnyRef])
          )
        case None              => () // no statement to insert
      }
  }

  override protected def addRef(ns: String, refAttr: String): Product => Unit = {
    val a = kw(ns, refAttr)
    (_: Product) =>
      backRefs = backRefs + (ns -> e)
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


  // Save Int as Long in Datomic since we can't enforce Integers in edn (for JS rpc)

  override protected lazy val valueString     = identity
  override protected lazy val valueInt        = (v: Any) => v.asInstanceOf[Int].toLong
  override protected lazy val valueLong       = identity
  override protected lazy val valueFloat      = identity
  override protected lazy val valueDouble     = identity
  override protected lazy val valueBoolean    = boolean2java
  override protected lazy val valueBigInt     = bigInt2java
  override protected lazy val valueBigDecimal = bigDec2java
  override protected lazy val valueDate       = identity
  override protected lazy val valueUUID       = identity
  override protected lazy val valueURI        = identity
  override protected lazy val valueByte       = byte2java
  override protected lazy val valueShort      = short2java
  override protected lazy val valueChar       = char2java
}