package molecule.datomic.transaction

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.{InsertExtraction_, InsertOps, InsertResolvers_, SaveExtraction}
import molecule.core.util.ModelUtils
import molecule.core.validation.ConflictingAttrs
import scala.collection.mutable.ListBuffer

trait Insert_stmts
  extends DatomicTxBase_JVM
    with InsertOps
    with DatomicDataType_JVM
    with ModelUtils
    with MoleculeLogging { self: InsertExtraction_ with InsertResolvers_ =>

  override protected val prevRefs: ListBuffer[AnyRef] = new ListBuffer[AnyRef]

  def getStmts(elements: List[Element], tpls: Seq[Product]): Data = {
    initTxBase(elements)
    val (mainElements, txMetaElements) = splitElements(elements)

    ConflictingAttrs.check(mainElements)

    val tpl2stmts          : Product => Seq[InsertError]  = getResolver(mainElements)
    val indexedInsertErrors: Seq[(Int, Seq[InsertError])] =
      tpls.zipWithIndex.flatMap { case (tpl, rowIndex) =>
        e = newId
        e0 = e
        // populate `stmts` and return insert validation errors for each row
        val rowErrors: Seq[InsertError] = tpl2stmts(tpl)
        if (rowErrors.isEmpty) None else Some((rowIndex, rowErrors))
      }

    if (indexedInsertErrors.nonEmpty) {
      throw InsertValidationErrors(indexedInsertErrors)
    }

    if (txMetaElements.nonEmpty) {
      val txMetaStmts = (new SaveExtraction(true) with Save_stmts)
        .getRawStmts(txMetaElements, datomicTx, false)
      stmts.addAll(txMetaStmts)
    }

    val insertStrs = "INSERT:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
    logger.debug(insertStrs.mkString("\n").trim)

    stmts
  }

  override protected def addComposite(
    tpl: Int,
    tplIndex: Int,
    compositeElements: List[Element]
  ): Product => Seq[InsertError] = {
    hasComposites = true
    val composite2stmts = getResolver(compositeElements, tpl)
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
    tplIndex: Int,
    ns: String,
    refAttr: String,
    nestedElements: List[Element]
  ): Product => Seq[InsertError] = {
    // Recursively resolve nested data
    val nested2stmts = getResolver(nestedElements)
    val fullRefAttr  = ns + "." + refAttr
    countValueAttrs(nestedElements) match {
      case 1 => // Nested arity-1 values
        (tpl: Product) => {
          val nestedValues  = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
          val nestedBaseId  = e
          val indexedErrors = nestedValues.zipWithIndex.flatMap { case (nestedValue, rowIndex) =>
            e = nestedBaseId
            val nestedTpl = Tuple1(nestedValue)
            addRef(ns, refAttr)(nestedTpl)
            e0 = e
            val rowErrors: Seq[InsertError] = nested2stmts(nestedTpl)
            if (rowErrors.isEmpty) None else Some((rowIndex, rowErrors))
          }
          if (indexedErrors.isEmpty) Nil else Seq(InsertError(0, 0, fullRefAttr, Nil, indexedErrors))
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls    = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          val nestedBaseId  = e
          val indexedErrors = nestedTpls.zipWithIndex.flatMap { case (nestedTpl, rowIndex) =>
            e = nestedBaseId
            addRef(ns, refAttr)(nestedTpl)
            e0 = e
            val rowErrors: Seq[InsertError] = nested2stmts(nestedTpl)
            if (rowErrors.isEmpty) None else Some((rowIndex, rowErrors))
          }
          if (indexedErrors.isEmpty) Nil else Seq(InsertError(0, 0, fullRefAttr, Nil, indexedErrors))
        }
    }
  }

  override protected def addV[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    raw2java: T => Any,
    validate: T => Seq[String]
  ): Product => Seq[InsertError] = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
      val rawValue = tpl.productElement(tplIndex).asInstanceOf[T]
      appendStmt(add, e, a, raw2java(rawValue).asInstanceOf[AnyRef])
      val errors = validate(rawValue)
      if (errors.isEmpty) Nil else {
        Seq(InsertError(outerTpl, tplIndex, ns + "." + attr, errors, Nil))
      }
    }
  }

  override protected def addOptV[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    raw2java: T => Any,
    validate: T => Seq[String]
  ): Product => Seq[InsertError] = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
      tpl.productElement(tplIndex) match {
        case Some(rawValue) =>
          val typedValue = rawValue.asInstanceOf[T]
          appendStmt(add, e, a, raw2java(typedValue).asInstanceOf[AnyRef])
          val errors = validate(typedValue)
          if (errors.isEmpty) Nil else {
            Seq(InsertError(outerTpl, tplIndex, ns + "." + attr, errors, Nil))
          }
        case None           => Nil // no statement to insert
      }
    }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    raw2java: T => Any,
    validate: T => Seq[String]
  ): Product => Seq[InsertError] = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
      val errors = tpl.productElement(tplIndex).asInstanceOf[Set[_]].flatMap { rawValue =>
        val typedValue = rawValue.asInstanceOf[T]
        appendStmt(add, e, a, raw2java(typedValue).asInstanceOf[AnyRef])
        validate(typedValue)
      }.toSeq
      if (errors.isEmpty) Nil else {
        Seq(InsertError(outerTpl, tplIndex, ns + "." + attr, errors, Nil))
      }
    }
  }

  override protected def addOptSet[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    raw2java: T => Any,
    validate: T => Seq[String]
  ): Product => Seq[InsertError] = {
    val a = kw(ns, attr)
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
      tpl.productElement(tplIndex) match {
        case Some(set: Set[_]) =>
          val errors = set.toSeq.flatMap { rawValue =>
            val typedValue = rawValue.asInstanceOf[T]
            appendStmt(add, e, a, raw2java(typedValue).asInstanceOf[AnyRef])
            validate(typedValue)
          }
          if (errors.isEmpty) Nil else {
            Seq(InsertError(outerTpl, tplIndex, ns + "." + attr, errors, Nil))
          }
        case None              => Nil // no statement to insert
      }
    }
  }

  //  override protected def addTxV[T](
  //    ns: String,
  //    attr: String,
  //    outerTpl: Int,
  //    tplIndex: Int,
  //    value: T => Any
  //  ): Product => Seq[InsertError] = {
  //    val a = kw(ns, attr)
  //    (tpl: Product) => {
  //      e = datomicTx
  //      backRefs = backRefs + (ns -> e)
  //      appendStmt(add, e, a, value(tpl.productElement(tplIndex).asInstanceOf[T]).asInstanceOf[AnyRef])
  //      Nil
  //    }
  //  }

  override protected def addRef(ns: String, refAttr: String): Product => Seq[InsertError] = {
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
      Nil
  }

  override protected def addBackRef(backRefNs: String): Product => Seq[InsertError] = {
    (_: Product) =>
      e = backRefs(backRefNs)
      Nil
  }


  override protected lazy val valueString     = identity
  // Save Int as Long in Datomic since we can't enforce Integers in edn (for JS rpc)
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